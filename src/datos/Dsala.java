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
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import negocion.Ncliente;
import negocion.Nsala;

/**
 *
 * @author David Torrez
 */
public class Dsala {

    private int id;
    private String nombre;
    private int capacidad;

    private Dconexion conexion;

    public Dsala() {
        conexion = new Dconexion();
    }

    public Dsala(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void insertSala() throws Exception {
        String query = "INSERT INTO sala (nombre, capacidad) VALUES ('"
                + this.nombre + "','" + this.capacidad + "');";
        Statement consulta;
        try {
            conexion.openConexion();
            consulta = (Statement) conexion.conexion.createStatement();
            consulta.execute(query);
            consulta.close();
            conexion.closeConexion();
        } catch (SQLException e) {
            System.out.println(e.toString() + " -> error sql");
            System.out.println("Error al guardar");
            throw new Exception();
        }
    }

    public DefaultTableModel getTabla() {
        String[] columnNames = {"Nro", "Nombre", "Capacidad"};
        DefaultTableModel listar = new DefaultTableModel(columnNames, 0);
        String query = "SELECT * FROM sala ORDER BY id";
        //-----------------------------
        Statement Consulta;
        ResultSet resultado = null;
        String[] datos = new String[columnNames.length];
        try {
            conexion.openConexion();
            Consulta = (Statement) conexion.conexion.createStatement();
            resultado = Consulta.executeQuery(query);
            while (resultado.next()) {
                for (int i = 0; i < columnNames.length; i++) {
                    datos[i] = resultado.getString(i + 1);
                }
                listar.addRow(datos);
            }
            Consulta.close();
            conexion.closeConexion();
        } catch (SQLException e) {
            System.out.println("no se pudo listar los datos" + e.getMessage());
        }
        return listar;
    }

    public void updateSala() throws Exception {
        String query = "UPDATE sala set "
                + "nombre= '" + this.nombre + "', capacidad= '" + this.capacidad
                + "' WHERE id= '" + this.id + "';";
        // System.out.println(query);
        Statement consulta;
        try {
            conexion.openConexion();
            consulta = (Statement) conexion.conexion.createStatement();
            consulta.execute(query);
            consulta.close();
            conexion.closeConexion();
        } catch (SQLException e) {
            System.out.println(e.toString() + " -> error sql");
            System.out.println("Error al update");
            throw new Exception();
        }
    }

    public void deleteSala() throws Exception {
        String query = "DELETE FROM sala WHERE id = '" + this.id + "';";
        // System.out.println(query);
        Statement consulta;
        try {
            conexion.openConexion();
            consulta = (Statement) conexion.conexion.createStatement();
            consulta.execute(query);
            consulta.close();
            conexion.closeConexion();
        } catch (SQLException e) {
            System.out.println(e.toString() + " -> error sql");
            System.out.println("Error al guardar");
            throw new Exception();
        }
    }

    public Map<String, String> getRow() {
        Map<String, String> sala = new HashMap<>();
//        map.put("name", "demo");
        String query = "SELECT * FROM sala WHERE id= '" + this.id + "';";
        // System.out.println(query);
        //----------------------------

        Statement Consulta;
        ResultSet resultado = null;
        try {
            conexion.openConexion();
            Consulta = (Statement) conexion.conexion.createStatement();
            resultado = Consulta.executeQuery(query);
            ResultSetMetaData rsmd = resultado.getMetaData();
            if (!resultado.next()) {
                sala.put("estado", "vacio");
            }
            for (int i = 0; i < 3; i++) {
                sala.put(rsmd.getColumnName(i + 1).trim(), resultado.getString(i + 1).trim());
            }
            Consulta.close();
            conexion.closeConexion();
        } catch (SQLException e) {
            System.out.println("no se pudo listar los datos" + e.getMessage());
        }
        return sala;
    }

    public DefaultComboBoxModel getComboBox() {
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        String query = "SELECT id, nombre FROM sala;";
        //System.out.println(query);
        //---------------------------------------
        Statement Consulta;
        ResultSet resultado = null;
        String[] datos = new String[2];
        try {
            this.conexion.openConexion();
            Consulta = (Statement) conexion.conexion.createStatement();
            resultado = Consulta.executeQuery(query);
            comboBox.addElement(new Ncliente("0", "Seleccione"));
            while (resultado.next()) {
                for (int i = 0; i < 2; i++) {
                    datos[i] = resultado.getString(i + 1).trim();
                }
                comboBox.addElement(new Nsala(Integer.parseInt(datos[0]), datos[1]));
            }
            Consulta.close();
            this.conexion.closeConexion();

        } catch (SQLException e) {
            System.out.println("no se pudo listar los datos" + e.getMessage());
        }
        return comboBox;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setId(int identidicador) {
        this.id = identidicador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
