/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;

/**
 *
 * @author David Torrez
 */
public class Dconexion {

    public Connection conexion = null;
    private String nameDatabase = "db_gimnasio";
    private String user = "postgres";
    private String password = "1234";
    private static String driver = "org.postgresql.Driver";

    public Connection openConexion() {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + nameDatabase, user, password);
//            System.out.println("conexion realizada con exito");
        } catch (Exception ee) {
            System.out.println(ee.toString() + " conexion fallida");
        }
        return conexion;
    }
    
    public Connection closeConexion() {
        try {
            conexion.close();
//            System.out.println("Cerrrando Conexion");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return conexion = null;
    }


//    public void querySQL(String query) throws Exception {
//        Statement consulta;
//        try {
//            openConexion();
//            consulta = (Statement) conexion.createStatement();
//            consulta.execute(query);
//            consulta.close();
////            System.out.println("Guardado con Exito");
//            closeConexion();
//
//        } catch (SQLException e) {
//            System.out.println(e.toString() + " -> error sql");
//            throw new Exception();
//
//        } catch (Exception e) {
//
//            System.out.println("Error al guardar");
//            throw new Exception();
//        }
//    }

//    
//    public Map<String, String> getData(String query, int cantidadAtributos) {
//        Map<String, String> map = new HashMap<>();
//        Statement Consulta;
//        ResultSet resultado = null;
//        try {
//            openConexion();
//            Consulta = (Statement) conexion.createStatement();
//            resultado = Consulta.executeQuery(query);
//            ResultSetMetaData rsmd = resultado.getMetaData();
//            if (!resultado.next()) {
//                map.put("estado", "vacio");
//            }
//            for (int i = 0; i < cantidadAtributos; i++) {
//                map.put(rsmd.getColumnName(i + 1).trim(), resultado.getString(i + 1).trim());
//            }
//            Consulta.close();
//            closeConexion();
//
//        } catch (SQLException e) {
//            System.out.println("no se pudo listar los datos" + e.getMessage());
//        }
//        return map;
//    }
//
//    public DefaultComboBoxModel getComboBoxData(String query, String model, int cantidadAtributos) {
//        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
//        Statement Consulta;
//        ResultSet resultado = null;
//        String[] datos = new String[cantidadAtributos];
//        try {
//            openConexion();
//            Consulta = (Statement) conexion.createStatement();
//            resultado = Consulta.executeQuery(query);
//            switch (model) {
//                case "Ninstructor":
//                    comboBox.addElement(new Ninstructor("0", "Eliga un Instructor"));
//                    break;
//                case "Nsala":
//                    comboBox.addElement(new Nsala(0, "Eliga una Sala"));
//                    break;
//                case "Ncliente":
//                    comboBox.addElement(new Ncliente("0", "Seleccione un Cliente"));
//                    break;
//            }
//            while (resultado.next()) {
//                for (int i = 0; i < cantidadAtributos; i++) {
////                    System.out.println(resultado.getString(i + 1).trim());
//                    datos[i] = resultado.getString(i + 1).trim();
//                }
//                switch (model) {
//                    case "Ninstructor":
//                        comboBox.addElement(new Ninstructor(datos[0], datos[1]));
//                        break;
//                    case "Nsala":
//                        comboBox.addElement(new Nsala(Integer.parseInt(datos[0]), datos[1]));
//                        break;
//                    case "Ncliente":
//                        comboBox.addElement(new Ncliente(datos[0], datos[1]));
//                        break;
//                }
//            }
//            Consulta.close();
//            closeConexion();
//
//        } catch (SQLException e) {
//            System.out.println("no se pudo listar los datos" + e.getMessage());
//        }
//        return comboBox;
//    }
//
//    public DefaultTableModel getTabla(String query, DefaultTableModel model, int cantidadAtributos) {
//        Statement Consulta;
//        ResultSet resultado = null;
//        String[] datos = new String[cantidadAtributos];
//        try {
//            openConexion();
//            Consulta = (Statement) conexion.createStatement();
//            resultado = Consulta.executeQuery(query);
//            while (resultado.next()) {
//                for (int i = 0; i < cantidadAtributos; i++) {
//                    datos[i] = resultado.getString(i + 1);
//                }
//                model.addRow(datos);
//            }
//            Consulta.close();
//            closeConexion();
//        } catch (SQLException e) {
//            System.out.println("no se pudo listar los datos" + e.getMessage());
//        }
//        return model;
//    }
//
//    public ArrayList getTablaL(String query, ArrayList<Nhorario> model, int cantidadAtributos) {
//        Statement Consulta;
//        ResultSet resultado = null;
//        String[] datos = new String[cantidadAtributos];
//        try {
//            openConexion();
//            Consulta = (Statement) conexion.createStatement();
//            resultado = Consulta.executeQuery(query);
//            while (resultado.next()) {
//                for (int i = 0; i < cantidadAtributos; i++) {
//                    datos[i] = resultado.getString(i + 1).trim();
//                }
//                model.add(new Nhorario(Integer.parseInt(datos[0]), datos[1],datos[2],datos[3]));
//            }
//            Consulta.close();
//            closeConexion();
//        } catch (SQLException e) {
//            System.out.println("no se pudo listar los datos" + e.getMessage());
//        }
//        return model;
//    }
}
