
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.mdlPiloto;
import vistas.jfrPiloto;

public class ctrlPiloto implements MouseListener, KeyListener{
    
    private mdlPiloto modelo;
    private jfrPiloto vista;
    
    public ctrlPiloto(mdlPiloto modelo, jfrPiloto vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnGuardar.addMouseListener(this);
        vista.btnBorrar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.tbPiloto.addMouseListener(this);

        modelo.Mostrar(vista.tbPiloto);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setNombre_piloto(vista.txtNombre.getText());
                    modelo.setEdad_Piloto(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Piloto(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setCorreo_Piloto(vista.txtCorreo.getText());
                    //Ejecutar el metodo 
                    modelo.Guardar();
                    modelo.Mostrar(vista.tbPiloto);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
         }
         
          if (e.getSource() == vista.btnBorrar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbPiloto);
                modelo.Mostrar(vista.tbPiloto);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombre_piloto(vista.txtNombre.getText());
                    modelo.setEdad_Piloto(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setPeso_Piloto(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setCorreo_Piloto(vista.txtCorreo.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.tbPiloto);
                    modelo.Mostrar(vista.tbPiloto);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbPiloto) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
         
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
         
    }

    @Override
    public void keyTyped(KeyEvent e) {
         
    }

    @Override
    public void keyPressed(KeyEvent e) {
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }
    
}
