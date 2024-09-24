
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vistas.jfrPiloto;


public class mdlPiloto {

    private String UUID_Piloto;
    private String Nombre_piloto;
    private int Edad_Piloto;
    private int Peso_Piloto;
    private String Correo_Piloto;
    
   
    public String getUUID_Piloto() {
        return UUID_Piloto;
    }

    public void setUUID_Piloto(String UUID_Piloto) {
       this.UUID_Piloto = UUID_Piloto;
    }
    
    public String getNombre_piloto() {
        return Nombre_piloto;
    }

    public void setNombre_piloto(String Nombre_piloto) {
        this.Nombre_piloto = Nombre_piloto;
    }

    public int getEdad_Piloto() {
        return Edad_Piloto;
    }

    public void setEdad_Piloto(int Edad_Piloto) {
        this.Edad_Piloto = Edad_Piloto;
    }

    public int getPeso_Piloto() {
        return Peso_Piloto;
    }

    public void setPeso_Piloto(int Peso_Piloto) {
        this.Peso_Piloto = Peso_Piloto;
    }

    public String getCorreo_Piloto() {
        return Correo_Piloto;
    }

    public void setCorreo_Piloto(String Correo_Piloto) {
        this.Correo_Piloto = Correo_Piloto;
    }
    
public void Guardar() {

        Connection conexion = ClaseConexion.getConexion();
        try {

            String sql = "INSERT INTO tbpiloto(UUID_Piloto, Nombre_Piloto, edad_Piloto, Peso_Piloto, Correo_Piloto) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conexion.prepareStatement(sql);

            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre_piloto());
            pstmt.setInt(3, getEdad_Piloto());
            pstmt.setInt(4, getPeso_Piloto());
            pstmt.setString(5, getCorreo_Piloto());
            

            pstmt.executeUpdate(); 

            
            
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }

public void Mostrar(JTable tabla) {

        Connection conexion = ClaseConexion.getConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Edad", "Peso", "Correo"});
        try {

            String query = "SELECT * FROM  tbpiloto";

            Statement statement = conexion.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                modelo.addRow(new Object[]{rs.getString("UUID_Piloto"), 
                    rs.getString("Nombre_Piloto"), 
                    rs.getInt("edad_Piloto"), 
                    rs.getString("Peso_Piloto"),
                    rs.getString("Correo_Piloto")});
            }

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

public void Eliminar(JTable tabla) {

        Connection conexion = ClaseConexion.getConexion();


        int filaSeleccionada = tabla.getSelectedRow();


        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();

        try {
            String sql = "delete from tbpiloto where UUID_Piloto = ?";
            PreparedStatement deletePiloto = conexion.prepareStatement(sql);
            deletePiloto.setString(1, miId);
            deletePiloto.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }

public void Actualizar(JTable tabla) {
    
        Connection conexion = ClaseConexion.getConexion();

 
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {

            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {

                String sql = "update tbpiloto set Nombre_Piloto= ?, edad_Piloto = ?, Peso_Piloto = ?, Correo_Piloto = ? where UUID_Piloto = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre_piloto());
                updateUser.setInt(2, getEdad_Piloto());
                updateUser.setDouble(3, getPeso_Piloto());
                updateUser.setString(4, getCorreo_Piloto());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

public void limpiar(jfrPiloto vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

public void cargarDatosTabla(jfrPiloto vista) {

        int filaSeleccionada = vista.tbPiloto.getSelectedRow();

        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.tbPiloto.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.tbPiloto.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.tbPiloto.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.tbPiloto.getValueAt(filaSeleccionada, 3).toString();
            String Correotb = vista.tbPiloto.getValueAt(filaSeleccionada, 3).toString();


            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreo.setText(Correotb);
        }
    }
}



    

    
    
    