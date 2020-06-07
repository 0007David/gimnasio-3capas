/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocion;

import datos.DdetalleInscripcion;
import datos.Dinscripcion;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Torrez
 */
public class Ninscripcion {

    private final Dinscripcion DatoInscripcion;
    private ArrayList<DdetalleInscripcion> DatoDetalleInscripcion;
    

    public Ninscripcion() {
        DatoInscripcion = new Dinscripcion();
        DatoDetalleInscripcion = new ArrayList<DdetalleInscripcion>();
        
    }

    public void setInscripcionR(String fecha, float monto, int idCliente) throws Exception {
        DatoInscripcion.setFecha(fecha);
        DatoInscripcion.setMonto(monto);
        DatoInscripcion.setIdCliente(idCliente);
        DatoInscripcion.insertBoletaInscripcion();
    }

    public void setInscripcionE() throws Exception {
        DatoInscripcion.setId(getId());
        DatoInscripcion.deleteBoletaInscripcion();
    }

    public void setInscripcionM(int id, String fecha, float monto) throws Exception {
        DatoInscripcion.setId(id);
        DatoInscripcion.setFecha(fecha);
        DatoInscripcion.setMonto(monto);
        DatoInscripcion.updateBoletaInscripcion();
    }

    public void setDetalleInscripcionR() throws Exception {
        
        for (DdetalleInscripcion detalleInsc : DatoDetalleInscripcion) {
            detalleInsc.setIdBoletaInscripcion(this.DatoInscripcion.getId());
            //System.out.println("Registrar: "+ detalleInsc);
            detalleInsc.insertDetalleInscripcion();
        }
    }

    public void addDetalleInscripcion(int idDisciplina, String fechaInicio, String fechaFin, float monto) {
        DdetalleInscripcion detalle = new DdetalleInscripcion();
        detalle.setFechaInicio(fechaInicio);
        detalle.setFechaFin(fechaFin);
        detalle.setMonto(monto);
        detalle.setIdDisciplina(idDisciplina);
        this.DatoDetalleInscripcion.add(detalle);
    }

    // 1   
    public void setDetalleInscripcionE() throws Exception {
        
        for (DdetalleInscripcion detalle : DatoDetalleInscripcion) {
            detalle.setIdBoletaInscripcion(this.DatoInscripcion.getId());
            detalle.deleteDetalleInscrion();
        }
        
    }

    public ArrayList getDetalleInscripcion() {
        DdetalleInscripcion detalle = new DdetalleInscripcion();
        detalle.setIdBoletaInscripcion(getId());
        return detalle.getRows();
    }

    public DefaultTableModel getTablaInscripciones() {
        return this.DatoInscripcion.getTabla();
    }

    public int getMaxId() {
        return this.DatoInscripcion.getMaxId();
    }

    public int getId() {
        return this.DatoInscripcion.getId();
    }

    public void setId(int id) {
        this.DatoInscripcion.setId(id);
    }

    public void vaciarDetalleInscripcion() {
        this.DatoDetalleInscripcion = new ArrayList<>();
    }

}
