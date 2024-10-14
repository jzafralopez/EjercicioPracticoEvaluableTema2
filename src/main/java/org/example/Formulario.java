package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Formulario extends JFrame {

    private JPanel ventana;
    private JTextField correoField;
    private ComboBoxModel<String> pais;
    private JComboBox<String> comboBox;
    private JCheckBox esAdminCheckBox;
    private JButton confirmarYGuardarUsuarioButton;
    private JButton volverAtrasButton;
    private DefaultTableModel tablaModelo;
    private JTable tablaUsuario;


    /**
     * Constructor de la clase Formulario.
     */
    public Formulario() {
        this.setContentPane(ventana);
        this.setTitle("Formulario");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 500);

        pais = new DefaultComboBoxModel<>(new String[]{"España", "Italia", "Alemania", "Francia"});
        comboBox.setModel(pais);

        String[] columnas = {"Correo", "País", "Admin"};
        tablaModelo = new DefaultTableModel(columnas, 0);
        tablaUsuario.setModel(tablaModelo);

        /**
         * El evento de botón de confirmar y guardar usuario.
         * Valida que los campos no estén vacíos, luego añade una fila con los datos
         * ingresados a la tabla con mensaje de "ok", y si no se meten todos los datos, da un mensaje de error.
         */
        confirmarYGuardarUsuarioButton.addActionListener(e -> {
            String correo = correoField.getText();
            String paisSeleccionado = (String) comboBox.getSelectedItem();
            boolean esAdmin = esAdminCheckBox.isSelected();

            if (correo.isEmpty() || paisSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos para registrarse...", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tablaModelo.addRow(new Object[]{correo, paisSeleccionado, esAdmin ? "Sí" : "No"});
                JOptionPane.showMessageDialog(this, "Usuario " + correo + " [Administrador] " + " almacenado correctamente.", "Ok", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }
        });

        cerrar();
    }

    /**
     * Método cerrar. que cierra la ventana del formulario.
     */
    private void cerrar() {
        volverAtrasButton.addActionListener(e -> {
            this.dispose();
        });
    }

    /**
     * Método limpiar.
     * Limpia todos los campos del formulario, los resetea a sus valores iniciales.
     */
    private void limpiar() {
        correoField.setText("");
        comboBox.setSelectedIndex(0);
        esAdminCheckBox.setSelected(false);
    }
}
