package views;

import controller.AlumnoController;
import controller.CatalogoController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Alumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validaciones.ValidadorAlumno;

public class VentanaEdicion extends javax.swing.JFrame
{

    private AlumnoController controllerEdicion;
    private Alumno alumno;
    private static final Logger log = LoggerFactory.getLogger(VentanaEdicion.class);

    public VentanaEdicion(AlumnoController controller, Alumno alumnoEncontrado)
    {
        initComponents();
        setTitle("Edición de alumno");
        setSize(800, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        controllerEdicion = controller;
        alumno = alumnoEncontrado;
        cargarCatalogo();
        cargarDatosAlumno();
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
    private void cargarCatalogo()
    {
        CatalogoController catalogo = new CatalogoController();
        jCbCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(catalogo.obtenerCarrera().toArray(new String[0])));
        jCbSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(catalogo.obtenerSemestres().toArray(new String[0])));
        jListMaterias.setListData(catalogo.obtenerMaterias().toArray(new String[0]));
    }

    private void cargarDatosAlumno()
    {
        jTxtNoControl.setText(alumno.getNoControl());
        jTxtNombre.setText(alumno.getNombre());
        jTxtCorreo.setText(alumno.getCorreo());
        jCbCarrera.setSelectedItem(alumno.getCarrera());
        jCbSemestre.setSelectedItem(alumno.getSemestre());
        jTxtPromedio.setText(String.valueOf(alumno.getPromedio()));
        jDCFechaNac.setDate(alumno.getFechaNacimiento());
        List<String> materiasAlumno = alumno.getMateriasSeleccionadas();
        List<Integer> indices = new ArrayList<>();
 
	for (String materia : materiasAlumno) {
		int posicion = jListMaterias.getModel().getSize(); //Te da acceso al modelo de datos
		for (int i = 0; i < jListMaterias.getModel().getSize(); i++) {
			if (jListMaterias.getModel().getElementAt(i).equals(materia)) {
				indices.add(i);
				break;
			}
			}
		}
        int[] indicesArray = new int[indices.size()];
	for (int i = 0; i < indices.size(); i++) {
		indicesArray[i] = indices.get(i);
	}
 
	jListMaterias.setSelectedIndices(indicesArray);        
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
        jButtonGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTxtNoControl = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jCbCarrera = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMaterias = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        jDCFechaNac = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jTxtPromedio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jCbSemestre = new javax.swing.JComboBox<>();
        jButtonEliminar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

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

        jButtonGuardar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(this::jButtonGuardarActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Edición de alumno");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Modifique la información del estudiante.");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("No. de Control");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nombre");

        jTxtNoControl.setEditable(false);
        jTxtNoControl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTxtNoControl.setFocusable(false);

        jTxtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Correo Electrónico");

        jTxtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Carrera");

        jCbCarrera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCbCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Semestre");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Promedio");

        jListMaterias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jListMaterias);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Fecha de Nacimiento");

        jDCFechaNac.setDateFormatString("dd/MM/yyyy");
        jDCFechaNac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Materias");

        jTxtPromedio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N

        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("(Rango de 0 a 10)");

        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("(Seleccione al menos una materia)");

        jCbSemestre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(this::jButtonEliminarActionPerformed);

        jButtonCancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(this::jButtonCancelarActionPerformed);

        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("(No editable)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxtNoControl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTxtCorreo)
                                    .addGap(57, 57, 57))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(245, 245, 245)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jButtonEliminar)
                                            .addGap(18, 18, 18)))
                                    .addComponent(jButtonGuardar)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonCancelar))
                                .addComponent(jTxtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(206, 206, 206)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEliminar)
                        .addComponent(jButtonCancelar)
                        .addComponent(jButtonGuardar))
                    .addComponent(jLabel14))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//------------------------------------------------------------------------------
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonGuardarActionPerformed
    {//GEN-HEADEREND:event_jButtonGuardarActionPerformed
        String noControl = jTxtNoControl.getText().trim();
        String nombre = jTxtNombre.getText().trim();
        String correo = jTxtCorreo.getText().trim();
        String carrera = jCbCarrera.getSelectedItem().toString();
        String semestre = jCbSemestre.getSelectedItem().toString();
        String promedioTexto = jTxtPromedio.getText().trim();
        Date fechaNacimiento = jDCFechaNac.getDate();
        List<String> materiasSeleccionadas = jListMaterias.getSelectedValuesList();
        //----------------------------------------------------------------------
        // VALIDA NUMERO DE CONTROL
        if (ValidadorAlumno.esVacio(noControl))
        {
            JOptionPane.showMessageDialog(this,
                    "El número de control es obligatorio.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------   
        // VALIDA NOMBRE
        if (ValidadorAlumno.esVacio(nombre))
        {
            JOptionPane.showMessageDialog(this,
                    "El nombre es obligatorio.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // VALIDA QUE EL NOMBRE NO TENGA NUMEROS
        if (!ValidadorAlumno.nombreValido(nombre))
        {
            JOptionPane.showMessageDialog(this,
                    "El nombre no puede contener números.",
                    "Nombre incorrecto",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------
        // VALIDA CORREO
        if (ValidadorAlumno.esVacio(correo))
        {
            JOptionPane.showMessageDialog(this,
                    "El correo es obligatorio.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // VALIDA FORMATO DEL CORREO
        if (!ValidadorAlumno.correoValido(correo))
        {
            JOptionPane.showMessageDialog(this,
                    "El correo no tiene un formato válido.",
                    "Correo incorrecto",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------
        // VALIDA CARRERA
        if (!ValidadorAlumno.comboBoxValido(jCbCarrera.getSelectedItem()))
        {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una carrera válida.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------
        // VALIDA SEMESTRE
        if (!ValidadorAlumno.comboBoxValido(jCbSemestre.getSelectedItem()))
        {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un semestre válido.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------   
        // VALIDA PROMEDIO
        if (ValidadorAlumno.esVacio(promedioTexto))
        {
            JOptionPane.showMessageDialog(this,
                    "El promedio es obligatorio.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        double promedio = ValidadorAlumno.promedioValido(promedioTexto);
        if (promedio < 0)
        {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un promedio numérico entre 0 y 10.",
                    "Promedio incorrecto",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------  
        //VALIDA FECHA DE NACIMIENTO
        String fechaTexto = jDCFechaNac.getDateEditor()
                .getUiComponent() instanceof javax.swing.JTextField
                        ? ((javax.swing.JTextField) jDCFechaNac.getDateEditor()
                                .getUiComponent()).getText().trim()
                        : "";
        // 1. VALIDAR FECHA OBLIGATORIA
        if (fechaTexto.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "La fecha es obligatoria.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

// 2. VALIDAR FECHA INVÁLIDA
        if (fechaNacimiento == null)
        {
            JOptionPane.showMessageDialog(this,
                    "Ingrese una fecha válida.\nFormato: dd/MM/yyyy",
                    "Fecha incorrecta",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

// 3. VALIDAR FECHA FUTURA
        if (!ValidadorAlumno.fechaValida(fechaNacimiento))
        {
            JOptionPane.showMessageDialog(this,
                    "La fecha de nacimiento no puede ser futura.",
                    "Fecha incorrecta",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------    
        // VALIDA MATERIAS
        if (!ValidadorAlumno.listaValida(materiasSeleccionadas))
        {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar al menos una materia.",
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //----------------------------------------------------------------------    
        // CREA ALUMNO
        Alumno nuevoAlumno = new Alumno(
                noControl,
                nombre,
                correo,
                carrera,
                semestre,
                promedio,
                fechaNacimiento,
                materiasSeleccionadas
        );
        //----------------------------------------------------------------------    
        // GUARDA ALUMNO
        boolean edicionEstudiante = controllerEdicion.actualizaAlumno(nuevoAlumno);
        JOptionPane.showMessageDialog(this,
                "Alumno actualizado correctamente.",
                "Alumno actualizado ",
                JOptionPane.INFORMATION_MESSAGE);
        VentanaConsulta ventana = new VentanaConsulta(controllerEdicion);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonGuardarActionPerformed


    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonInicioActionPerformed
    {//GEN-HEADEREND:event_jButtonInicioActionPerformed
        VentanaInicio ventana = new VentanaInicio(controllerEdicion);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaActionPerformed
    {//GEN-HEADEREND:event_jButtonAltaActionPerformed
        VentanaAlta ventana = new VentanaAlta(controllerEdicion);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAltaActionPerformed

    private void jButtonConsultaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonConsultaActionPerformed
    {//GEN-HEADEREND:event_jButtonConsultaActionPerformed
        VentanaConsulta ventana = new VentanaConsulta(controllerEdicion);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonConsultaActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEliminarActionPerformed
    {//GEN-HEADEREND:event_jButtonEliminarActionPerformed
        //----------------------------------------------------------------------
        // CONFIRMACIÓN ANTES DE ELIMINAR AL ESTUDIANTE
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea dar de baja al alumno?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        //----------------------------------------------------------------------
        // CANCELAR BAJA
        if (respuesta != JOptionPane.YES_OPTION)
        {
            return;
        }
        //----------------------------------------------------------------------
        // BAJA DEL ALUMNO
        boolean eliminarEstudiante = controllerEdicion.eliminaAlumno(alumno);
        if (!eliminarEstudiante)
        {
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
        VentanaConsulta ventana = new VentanaConsulta(controllerEdicion);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelarActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelarActionPerformed
        //----------------------------------------------------------------------
        // CONFIRMACIÓN ANTES DE CANCELAR EDICIÓN
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea cancelar la edición del alumno?",
                "Cancelar edición",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        //----------------------------------------------------------------------
        // CANCELAR 
        if (respuesta != JOptionPane.YES_OPTION)
        {
            return;
        }
        //----------------------------------------------------------------------
        // CONFIRMAR 
        VentanaConsulta ventana = new VentanaConsulta(controllerEdicion);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConsulta;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JComboBox<String> jCbCarrera;
    private javax.swing.JComboBox<String> jCbSemestre;
    private com.toedter.calendar.JDateChooser jDCFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListMaterias;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTxtCorreo;
    private javax.swing.JTextField jTxtNoControl;
    private javax.swing.JTextField jTxtNombre;
    private javax.swing.JTextField jTxtPromedio;
    // End of variables declaration//GEN-END:variables
}
