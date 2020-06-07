/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David Torrez
 */
public class DdetalleInscripcion {

    private int idBoletaInscripcion;
    private int idDisciplina;
    private String fechaInicio;
    private String fechaFin;
    private float monto;

    private final Dconexion conexion;

    public DdetalleInscripcion() {
        conexion = new Dconexion();
    }

    public void insertDetalleInscripcion() throws Exception {
        String query = "INSERT INTO inscribe (id_boleta_inscripcion, id_disciplina, fecha_inicio,fecha_fin,monto) VALUES ('"
                + this.idBoletaInscripcion + "','" + this.idDisciplina + "','"
                + this.fechaInicio + "','" + this.fechaFin + "','" + this.monto + "');";
        Statement consulta;
        try {
            conexion.openConexion();
            consulta = (Statement) conexion.conexion.createStatement();
            consulta.execute(query);
            consulta.close();
//            System.out.println("Guardado con Exito");
            conexion.closeConexion();

        } catch (SQLException e) {
            System.out.println(e.toString() + " -> error sql");
            System.out.println("Error al guardar");
            throw new Exception();

        }
    }
    
    public void deleteDetalleInscrion() throws Exception{
        
        String query = "DELETE FROM inscribe WHERE id_boleta_inscripcion='"+ this.idBoletaInscripcion+"'  AND id_disciplina= '" + this.idDisciplina + "';";
        // System.out.println(query);
        Statement consulta;
        try {
            conexion.openConexion();
            consulta = (Statement) conexion.conexion.createStatement();
            consulta.execute(query);
            consulta.close();
//            System.out.println("Guardado con Exito");
            conexion.closeConexion();

        } catch (SQLException e) {
            System.out.println(e.toString() + " -> error sql");
            System.out.println("Error al guardar");
            throw new Exception();

        }
    }

    public ArrayList<Dhorario> getTablaL() {
        ArrayList<Dhorario> list = new ArrayList<>();
        String query = "SELECT id, dias, hora_inicio, hora_fin FROM horario WHERE id_disciplina= '" + this.idDisciplina + "';";
        //---------
        // System.out.println(query);
        Statement Consulta;
        ResultSet resultado = null;
        String[] datos = new String[4];
        try {
            this.conexion.openConexion();
            Consulta = (Statement) this.conexion.conexion.createStatement();
            resultado = Consulta.executeQuery(query);
            while (resultado.next()) {
                for (int i = 0; i < 4; i++) {
                    datos[i] = resultado.getString(i + 1).trim();
                }
                list.add(new Dhorario(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3]));
            }
            Consulta.close();
            this.conexion.closeConexion();
        } catch (SQLException e) {
            System.out.println("no se pudo listar los datos" + e.getMessage());
        }
        return list;
    }

    public ArrayList<Map<String, String>> getRows() {
        ArrayList<Map<String, String>> listar = new ArrayList<>();
        Map<String, String> horario;
        String query = "SELECT bol.id as id_boleta, cli.nombre as cliente_nombre, dis.nombre as disciplina_nombre, sa.nombre as sala_nombre, bol.monto, ins.fecha_inicio, ins.fecha_fin,bol.fecha,ins.monto as sub_monto, dis.id as id_disciplina\n"
                + "FROM inscribe ins\n"
                + "INNER JOIN disciplina dis ON dis.id = ins.id_disciplina\n"
                + "INNER JOIN sala sa ON sa.id = dis.id_sala\n"
                + "INNER JOIN boleta_inscripcion bol ON bol.id = ins.id_boleta_inscripcion\n"
                + "INNER JOIN cliente cli ON cli.id = bol.id_cliente\n"
                + "WHERE bol.id = '" + this.idBoletaInscripcion + "';";
        Statement Consulta;
        ResultSet resultado = null;
        try {
            conexion.openConexion();
            Consulta = (Statement) conexion.conexion.createStatement();
            resultado = Consulta.executeQuery(query);
            ResultSetMetaData rsmd = resultado.getMetaData();
            while (resultado.next()) {
                horario = new HashMap<>();
                for (int i = 0; i < 10; i++) {
                    horario.put(rsmd.getColumnName(i + 1).trim(), resultado.getString(i + 1).trim());
                }
                listar.add(horario);
            }
            Consulta.close();
            conexion.closeConexion();
        } catch (SQLException e) {
            System.out.println("no se pudo listar los datos" + e.getMessage());
        }
        return listar;
    }

    public void setIdBoletaInscripcion(int idBoletaInscripcion) {
        this.idBoletaInscripcion = idBoletaInscripcion;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "DdetalleInscripcion{" + "idBoletaInscripcion=" + idBoletaInscripcion + ", idDisciplina=" + idDisciplina + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", monto=" + monto + '}';
    }
    

}
