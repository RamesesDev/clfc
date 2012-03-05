package loan.ui.asset;

import com.rameses.rcp.ui.annotations.Template;
import templates.PopupTemplate;
/*
 * AssetInfoPopupPage.java
 *
 * Created on April 7, 2011, 11:48 AM
 * @author jaycverg
 */

@Template(PopupTemplate.class)
public class AssetInfoPopupPage extends javax.swing.JPanel {
    
    public AssetInfoPopupPage() {
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
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XEditorPane xEditorPane1;
    // End of variables declaration//GEN-END:variables
    
}
