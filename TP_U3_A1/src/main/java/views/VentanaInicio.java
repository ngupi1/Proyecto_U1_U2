package views;

import controller.AlumnoController;
import java.util.List;
import model.Alumno;

public class VentanaInicio extends javax.swing.JFrame {

    private AlumnoController controllerInicio;

    public VentanaInicio(AlumnoController controller) {
        initComponents();
//------------------------------------------------------------------------------
        controllerInicio = controller;
        setTitle("Inicio");
        setSize(800, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        cargarDashboard();
        configurarMenuPestañas();
    }

    private void cargarDashboard() {
        try {
            // Usamos el método que ya tienes en tu controlador
            List<Alumno> listaAlumnos = controllerInicio.consultaAlumnos();

            // Contamos cuántos alumnos hay en la lista
            int total = listaAlumnos.size();

            // Actualizamos el número grande en la vista
            lblTotalAlumnos.setText(String.valueOf(total));

        } catch (Exception e) {
            // Por si no hay conexión a la base de datos
            lblTotalAlumnos.setText("0");
            System.out.println("Error al cargar dashboard: " + e.getMessage());
        }
    }

    private void configurarMenuPestañas() {
        jButtonInicio.setOpaque(true);
        jButtonInicio.setBackground(new java.awt.Color(70, 100, 170));
        jButtonInicio.setForeground(java.awt.Color.WHITE);
        jButtonInicio.setBorderPainted(false);
        jButtonInicio.setFocusPainted(false);
        estiloBotonInactivo(jButtonAlta);
        estiloBotonInactivo(jButtonConsulta);
    }

    private void estiloBotonInactivo(javax.swing.JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setForeground(new java.awt.Color(230, 230, 230));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButtonInicio = new javax.swing.JButton();
        jButtonAlta = new javax.swing.JButton();
        jButtonConsulta = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonAlta1 = new javax.swing.JButton();
        jButtonConsulta1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelDashBoard = new javax.swing.JLabel();
        lblAluRes = new javax.swing.JLabel();
        lblTotalAlumnos = new javax.swing.JLabel();

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonInicio)
                            .addComponent(jButtonAlta)
                            .addComponent(jButtonConsulta))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Administre el registro y control de alumnos de manera");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("Bienvenido al");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setText("Sistema de Control Escolar");

        jButtonAlta1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButtonAlta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButtonAlta1.setText("Registrar alumnos");
        jButtonAlta1.addActionListener(this::jButtonAlta1ActionPerformed);

        jButtonConsulta1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButtonConsulta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButtonConsulta1.setText("Consultar alumnos");
        jButtonConsulta1.addActionListener(this::jButtonConsulta1ActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("rápida y sencilla.");

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelDashBoard.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelDashBoard.setText("Dashboard");

        lblAluRes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblAluRes.setText("Alumnos registrados");

        lblTotalAlumnos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTotalAlumnos.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAluRes)
                            .addComponent(jLabelDashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(lblTotalAlumnos)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblTotalAlumnos)
                .addGap(18, 18, 18)
                .addComponent(lblAluRes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 210, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonConsulta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAlta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButtonAlta1)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConsulta1)
                        .addContainerGap(129, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonInicioActionPerformed
    {//GEN-HEADEREND:event_jButtonInicioActionPerformed
        VentanaInicio ventana = new VentanaInicio(controllerInicio);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAltaActionPerformed
    {//GEN-HEADEREND:event_jButtonAltaActionPerformed
        VentanaAlta ventana = new VentanaAlta(controllerInicio);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAltaActionPerformed

    private void jButtonConsultaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonConsultaActionPerformed
    {//GEN-HEADEREND:event_jButtonConsultaActionPerformed
        VentanaConsulta ventana = new VentanaConsulta(controllerInicio);
        ventana.setSize(this.getSize());
        ventana.setLocation(this.getLocation());
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonConsultaActionPerformed

    private void jButtonAlta1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAlta1ActionPerformed
    {//GEN-HEADEREND:event_jButtonAlta1ActionPerformed
        VentanaAlta ventana = new VentanaAlta(controllerInicio);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAlta1ActionPerformed

    private void jButtonConsulta1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonConsulta1ActionPerformed
    {//GEN-HEADEREND:event_jButtonConsulta1ActionPerformed
        VentanaConsulta ventana = new VentanaConsulta(controllerInicio);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonConsulta1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonAlta1;
    private javax.swing.JButton jButtonConsulta;
    private javax.swing.JButton jButtonConsulta1;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDashBoard;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAluRes;
    private javax.swing.JLabel lblTotalAlumnos;
    // End of variables declaration//GEN-END:variables
}
