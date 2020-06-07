/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocion;

import datos.Ddisciplina;
import datos.Dhorario;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Torrez
 */
public class Ndisciplina {

    private Ddisciplina Datodisciplina;
    private ArrayList<Dhorario> DatoHorarios;

    public Ndisciplina() {
        Datodisciplina = new Ddisciplina();
        DatoHorarios = new ArrayList<Dhorario>();
    }
    public Ndisciplina(int id,String nombre) {
        Datodisciplina = new Ddisciplina();
        Datodisciplina.setId(id);
        Datodisciplina.setNombre(nombre);
    }
    
    

    public void setDisciplinaR(String nombre, String descripcion, int id_instructor, int id_sala) throws Exception {
        Datodisciplina.setNombre(nombre);
        Datodisciplina.setDescripcion(descripcion);
        Datodisciplina.setId_instructor(id_instructor);
        Datodisciplina.setId_sala(id_sala);
        Datodisciplina.insertDisciplina();
    }

    public void setHorariosR() throws Exception {

        for (Dhorario horario : DatoHorarios) {
            //System.out.println("Registrando: " + horario.toString());
            //System.out.println("id: " + getId());
            horario.setId_disciplina(getId());
            horario.insertHorario();
            
        }
    }

    public void setHorariosE() throws Exception {
        Dhorario horario = new Dhorario();
        horario.setId(getId());
        horario.deleteHorario();
    }

    public void addHorarioDisciplina(String horarioAdd) throws Exception {
        Dhorario horario = new Dhorario();
        horario.setHorario(horarioAdd, this.getId());
        this.DatoHorarios.add(horario);
    }

    public void setDisciplinaM(int id, String nombre, String descripcion, int id_instructor, int id_sala) throws Exception {
        Datodisciplina.setId(id);
        Datodisciplina.setNombre(nombre);
        Datodisciplina.setDescripcion(descripcion);
        Datodisciplina.setId_instructor(id_instructor);
        Datodisciplina.setId_sala(id_sala);
        Datodisciplina.updateDisciplina();
    }

    public void setDisciplinaE(int id) throws Exception {
        Datodisciplina.setId(id);
        Datodisciplina.deleteDisciplina();
    }

    public DefaultTableModel getTablaDisciplinas(int type) {
        return Datodisciplina.getTabla(type);
    }

    public Map<String, String> getDisciplina(int id) {
        Datodisciplina.setId(id);
        return Datodisciplina.getRow();
    }

    public int getMaxId() {
        return Datodisciplina.getMaxId();
    }
    
    

    public void setId(int id) {
        Datodisciplina.setId(id);
    }

    public int getId() {
        return Datodisciplina.getId();
    }

    public String getNombre() {
        return Datodisciplina.getNombre();
    }

    public ArrayList<String> getHorarios() {
        Dhorario horario = new Dhorario();
        horario.setId_disciplina(getId()); 
        ArrayList<String> horariosL = new ArrayList<>();
        ArrayList<Dhorario> horarios = horario.getTablaL();
        for( Dhorario dHorario: horarios){
            horariosL.add(dHorario.toString());
        }
        return horariosL;
    }

    @Override
    public String toString() {
        return this.Datodisciplina.toString();
    }

}
