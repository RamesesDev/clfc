package clfc.home;

public class HomePage extends javax.swing.JPanel {
    
    public HomePage() {
        initComponents();
    }

    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xActionBar1 = new com.rameses.rcp.control.XActionBar();
        jPanel1 = new javax.swing.JPanel();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xLabel2 = new com.rameses.rcp.control.XLabel();

        xActionBar1.setButtonBorderPainted(false);
        xActionBar1.setButtonCaptionOrientation(javax.swing.SwingConstants.BOTTOM);
        xActionBar1.setButtonContentAreaFilled(false);
        xActionBar1.setName("homeMenu");
        xActionBar1.setOrientation(com.rameses.rcp.constant.UIConstants.FLOW);
        xActionBar1.setUseToolBar(false);

        jPanel1.setLayout(null);

        jPanel1.setBorder(new com.rameses.rcp.control.border.XUnderlineBorder());
        xLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        xLabel1.setExpression("Welcome #{name}");
        xLabel1.setFont(new java.awt.Font("Arial", 1, 18));
        jPanel1.add(xLabel1);
        xLabel1.setBounds(8, 14, 440, 24);

        xLabel2.setForeground(new java.awt.Color(221, 219, 219));
        xLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        xLabel2.setExpression("Welcome #{name}");
        xLabel2.setFont(new java.awt.Font("Arial", 1, 18));
        jPanel1.add(xLabel2);
        xLabel2.setBounds(4, 16, 440, 24);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xActionBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel2;
    // End of variables declaration//GEN-END:variables
    
}
