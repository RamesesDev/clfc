package loan.ui.asset;

/*
 * AssetInfoPopupPage.java
 *
 * Created on April 7, 2011, 11:48 AM
 * @author jaycverg
 */

public class AssetInfoPage extends javax.swing.JPanel {
    
    public AssetInfoPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        xEditorPane1 = new com.rameses.rcp.control.XEditorPane();

        xEditorPane1.setName("info");
        jScrollPane1.setViewportView(xEditorPane1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XEditorPane xEditorPane1;
    // End of variables declaration//GEN-END:variables
    
}
