package views;

import controller.AlumnoController;
import controller.CatalogoController;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Alumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validaciones.ValidadorAlumno;
import javax.swing.table.DefaultTableCellRenderer;

public class VentanaConsulta extends javax.swing.JFrame {

    private AlumnoController controllerConsulta;
    private static final Logger log = LoggerFactory.getLogger(VentanaConsulta.class);

    public VentanaConsulta(AlumnoController controller) {
        initComponents();
        setTitle("Consulta de alumnos");
        setSize(800, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        controllerConsulta = controller;
        cargarCatalogo();
        cargarTabla(null, null, null);
        permiteClickEnTabla();
        configurarIconosTabla();
        configurarMenuPestañas();
    }

    private void configurarMenuPestañas() {
        jButtonConsulta.setOpaque(true);
        jButtonConsulta.setBackground(new java.awt.Color(70, 100, 170));
        jButtonConsulta.setForeground(java.awt.Color.WHITE);
        jButtonConsulta.setBorderPainted(false);
        jButtonConsulta.setFocusPainted(false);
        estiloBotonInactivo(jButtonInicio);
        estiloBotonInactivo(jButtonAlta);
    }

    private void estiloBotonInactivo(javax.swing.JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setForeground(new java.awt.Color(230, 230, 230));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

//------------------------------------------------------------------------------
    private void permiteClickEnTabla() {
        jTableAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = jTableAlumnos.rowAtPoint(evt.getPoint());
                int columna = jTableAlumnos.columnAtPoint(evt.getPoint());
                if (fila == -1 || columna == -1) {
                    return;
                }
                // Ignorar filas que contienen mensajes
                Object valor = jTableAlumnos.getValueAt(fila, 0);
                if (valor == null || !valor.toString().matches("\\d+")) {
                    return;
                }
                // Columna 5: Más informacion
                if (columna == 5) {
                    mostrarInfo(fila);
                } // Columna 6: Editar
                else if (columna == 6) {
                    editarAlumno(fila);
                } // Columa 7: Eliminar
                else if (columna == 7) {
                    eliminarAlumno(fila);
                }
            }
        });
    }
//------------------------------------------------------------------------------

    private void cargarCatalogo() {
        CatalogoController catalogo = new CatalogoController();
        List<String> carreras = new ArrayList<>(catalogo.obtenerCarrera());
        List<String> semestres = new ArrayList<>(catalogo.obtenerSemestres());
        carreras.set(0, "Todas las carreras");
        semestres.add(0, "Todos los semestres");
        jCbCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(carreras.toArray(new String[0])));
        jCbSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(semestres.toArray(new String[0])));
    }

//------------------------------------------------------------------------------
    public void cargarTabla(Alumno alumno, String filtroCarrera, String filtroSemestre)
    {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTableAlumnos.getModel();
        modelo.setRowCount(0); 

        // 1. Mensaje inicial simple
        if (alumno == null && filtroCarrera == null && filtroSemestre == null)
        {
            modelo.addRow(new Object[] {  "Busque un alumno", "", "", "", "", "", "", "" });
            return;
        }

        // 2. No se encontró
        if (alumno == null && "INEXISTENTE".equals(filtroCarrera) && "INEXISTENTE".equals(filtroSemestre))
        {
            modelo.addRow(new Object[] { "No encontrado", "", "", "", "", "", "", "" });
            return;
        }

        // 3. Alumno encontrado
        if (alumno != null)
        {
            agregarFila(modelo, alumno);
            return;
        }

        // 4. Filtros
        List<Alumno> lista = controllerConsulta.consultaAlumnos();
        for (Alumno a : lista)
        {
            boolean coincideCarrera = "Todas las carreras".equals(filtroCarrera) || a.getCarrera().equals(filtroCarrera);
            boolean coincideSemestre = "Todos los semestres".equals(filtroSemestre) || a.getSemestre().equals(filtroSemestre);
            if (coincideCarrera && coincideSemestre)
            {
                agregarFila(modelo, a);
            }
        }

        // 5. Sin resultados por filtro
        if (modelo.getRowCount() == 0)
        {
            modelo.addRow(new Object[] { "Sin resultados", "", "", "", "", "", "", "" });
        }
    }

//------------------------------------------------------------------------------
    private void agregarFila(javax.swing.table.DefaultTableModel modelo, Alumno alumno) {
        modelo.addRow(new Object[]{
            alumno.getNoControl(),
            alumno.getNombre(),
            alumno.getCarrera(),
            alumno.getSemestre(),
            alumno.getPromedio(),});
    }

//------------------------------------------------------------------------------
    private void mostrarInfo(int fila) {
        String noControl = jTableAlumnos.getValueAt(fila, 0).toString();
        Alumno alumno = controllerConsulta.consultaPorNumControlONombre(noControl);
        if (alumno == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró al alumno.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        String materiasTexto = "";
        if (alumno.getMateriasSeleccionadas() != null) {
            String limpia = alumno.getMateriasSeleccionadas().toString()
                    .replace("[", "")
                    .replace("]", "")
                    .trim();
            if (!limpia.isEmpty()) {
                materiasTexto = "\n- " + limpia.replace(", ", "\n- ").replace(",", "\n- ");
            }
        }
        String fechaTexto = (alumno.getFechaNacimiento() != null)
                ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(alumno.getFechaNacimiento())
                : "";
        Object materias = alumno.getMateriasSeleccionadas();
        JOptionPane.showMessageDialog(this,
                "Información adicional de " + alumno.getNombre() + "\n\n"
                + "Correo: " + alumno.getCorreo() + "\n"
                + "Fecha de nacimiento: " + fechaTexto + "\n"
                + "Materias: " + materiasTexto,
                "Información adicional",
                JOptionPane.INFORMATION_MESSAGE);
    }

//------------------------------------------------------------------------------
    private void editarAlumno(int fila) {
        String noControl = jTableAlumnos.getValueAt(fila, 0).toString();
        Alumno alumno = controllerConsulta.consultaPorNumControlONombre(noControl);
        if (alumno == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró al alumno.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        VentanaEdicion ventana = new VentanaEdicion(controllerConsulta, alumno);
        ventana.setVisible(true);
        this.dispose();
    }

//------------------------------------------------------------------------------
    private void eliminarAlumno(int fila) {
        //----------------------------------------------------------------------
        // CONFIRMACIÓN ANTES DE ELIMINAR AL ESTUDIANTE
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea dar de baja al alumno?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        //----------------------------------------------------------------------
        // CANCELAR BAJA
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        //----------------------------------------------------------------------
        // BAJA DEL ALUMNO
        String noControl = jTableAlumnos.getValueAt(fila, 0).toString();
        Alumno alumno = controllerConsulta.consultaPorNumControlONombre(noControl);
        boolean eliminarEstudiante = controllerConsulta.eliminaAlumno(alumno);
        if (!eliminarEstudiante) {
            JOptionPane.showMessageDialog(this,
                    "Error en la eliminación",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
                "ALUMNO ELIMINADO CORRECTAMENTE",
                "Alumno eliminado",
                JOptionPane.INFORMATION_MESSAGE);
        cargarTabla(null, null, null);
        log.warn("ATENCIÓN: El alumno con Control: {} fue dado de baja y eliminado del sistema.", noControl);
    }

    private void configurarIconosTabla() {
        ImageIcon iconoInfo = new ImageIcon(getClass().getResource("/images/info32px.png"));
        ImageIcon iconoEditar = new ImageIcon(getClass().getResource("/images/edit32px.png"));
        ImageIcon iconoEliminar = new ImageIcon(getClass().getResource("/images/delete32px.png"));
        // Redimensionar las imágenes
        Image imagenInfo = iconoInfo.getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imagenEditar = iconoEditar.getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imagenEliminar = iconoEliminar.getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconoInfo = new ImageIcon(imagenInfo);
        iconoEditar = new ImageIcon(imagenEditar);
        iconoEliminar = new ImageIcon(imagenEliminar);
        // Columna 5: Editar
        jTableAlumnos.getColumnModel()
                .getColumn(5)
                .setCellRenderer(new RenderIcono(iconoInfo));
        // Columna 6: Editar
        jTableAlumnos.getColumnModel()
                .getColumn(6)
                .setCellRenderer(new RenderIcono(iconoEditar));
        // Columna 7: Eliminar
        jTableAlumnos.getColumnModel()
                .getColumn(7)
                .setCellRenderer(new RenderIcono(iconoEliminar));
    }

//------------------------------------------------------------------------------
    private class RenderIcono extends DefaultTableCellRenderer {

        private final ImageIcon icono;

        public RenderIcono(ImageIcon icono) {
            this.icono = icono;
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(javax.swing.JTable tabla, Object valor, boolean seleccionado, boolean tieneFoco, int fila, int columna) {
            super.getTableCellRendererComponent(tabla, valor, seleccionado, tieneFoco, fila, columna);
            setText("");

            // Verificar si la fila actual es un alumno real (comprobando la columna 0)
            Object idVal = tabla.getValueAt(fila, 0);
            if (idVal == null || idVal.toString().trim().isEmpty() || !idVal.toString().matches("\\d+")) {
                setIcon(null); // Oculta el ícono si es un mensaje
            } else {
                setIcon(icono); // Muestra el ícono si es un alumno
            }

            return this;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel2 = new javax.swing.JPanel();
        jButtonInicio = new javax.swing.JButton();
        jButtonAlta = new javax.swing.JButton();
        jButtonConsulta = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTxtBusqueda = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButtonBuscar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonMostrar = new javax.swing.JButton();
        jCbCarrera = new javax.swing.JComboBox<>();
        jCbSemestre = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlumnos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jButtonInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonInicio.setText("Inicio");
        jButtonInicio.addActionListener(this::jButtonInicioActionPerformed);

        jButtonAlta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonAlta.setText("Registrar alumnos");
        jButtonAlta.addActionListener(this::jButtonAltaActionPerformed);

        jButtonConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonConsulta.setText("Consultar alumnos");
        jButtonConsulta.addActionListener(this::jButtonConsultaActionPerformed);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/s3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jButtonInicio)
                .addGap(18, 18, 18)
                .addComponent(jButtonAlta)
                .addGap(18, 18, 18)
                .addComponent(jButtonConsulta)
                .addContainerGap(647, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonInicio)
                        .addComponent(jButtonAlta)
                        .addComponent(jButtonConsulta))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Consulta de alumnos");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Vea todos los alumnos, filtre por carrera, semestre o busque un alumno en específico");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nombre o número de control");

        jTxtBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search2.png"))); // NOI18N

        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(this::jButtonBuscarActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Filtrar por Carrera");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Filtrar por Semestre");

        jButtonMostrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonMostrar.setText("Mostrar");
        jButtonMostrar.addActionListener(this::jButtonMostrarActionPerformed);

        jCbCarrera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCbCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jCbSemestre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "No. de Control", "Nombre", "Carrera", "Semestre", "Promedio", "Más info.", "Editar", "Eliminar"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jTableAlumnos.setRowHeight(30);
        jScrollPane1.setViewportView(jTableAlumnos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(197, 197, 197)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(504, 504, 504))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTxtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jCbSemestre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonMostrar))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButtonBuscar)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMostrar)
                    .addComponent(jCbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonInicioActionPerformed
    {//GEN-HEADEREND:event_jButtonInicioActionPerformed
        VentanaInicio ventana = new VentanaInicio(controllerConsulta);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaActionPerformed
    {//GEN-HEADEREND:event_jButtonAltaActionPerformed
        VentanaAlta ventana = new VentanaAlta(controllerConsulta);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAltaActionPerformed

    private void jButtonConsultaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonConsultaActionPerformed
    {//GEN-HEADEREND:event_jButtonConsultaActionPerformed
        VentanaConsulta ventana = new VentanaConsulta(controllerConsulta);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonConsultaActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBuscarActionPerformed
    {//GEN-HEADEREND:event_jButtonBuscarActionPerformed
        String busqueda = jTxtBusqueda.getText().trim();
        //----------------------------------------------------------------------
        // VALIDA CAMPO VACIO
        if (ValidadorAlumno.esVacio(busqueda)) {
            JOptionPane.showMessageDialog(this,
                    "Inserte un alumno a buscar.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------
        // LIMPIA CAMPOS
        jCbCarrera.setSelectedIndex(0);
        jCbSemestre.setSelectedIndex(0);
        // COPIA ALUMNO
        Alumno alumno = controllerConsulta.consultaPorNumControlONombre(busqueda);
        if (alumno == null) {
            cargarTabla(alumno, "INEXISTENTE", "INEXISTENTE");
            return;
        }
        //----------------------------------------------------------------------
        // MUESTRA LA TABLA UNICAMENTE CON EL ALUMNO ENCONTRADO (O nulo)
        cargarTabla(alumno, null, null);
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonMostrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonMostrarActionPerformed
    {//GEN-HEADEREND:event_jButtonMostrarActionPerformed
        String filtroCarrera = jCbCarrera.getSelectedItem().toString();
        String filtroSemestre = jCbSemestre.getSelectedItem().toString();
        //----------------------------------------------------------------------
        // MUESTRA LA TABLA CON LOS ALUMNOS FILTRADOS
        cargarTabla(null, filtroCarrera, filtroSemestre);
        //----------------------------------------------------------------------
        // LIMPIA CAMPOS
        jTxtBusqueda.setText("");
    }//GEN-LAST:event_jButtonMostrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonConsulta;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JButton jButtonMostrar;
    private javax.swing.JComboBox<String> jCbCarrera;
    private javax.swing.JComboBox<String> jCbSemestre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAlumnos;
    private javax.swing.JTextField jTxtBusqueda;
    // End of variables declaration//GEN-END:variables
}
