package loan.ui.ledger;

import com.rameses.rcp.ui.annotations.Template;
import templates.FormTemplate;

/*
 * LedgerPage.java
 *
 * Created on April 12, 2011, 1:22 PM
 * @author jaycverg
 */

@Template(FormTemplate.class)
public class LedgerPage extends javax.swing.JPanel {
    
    public LedgerPage() {
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
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XEditorPane xEditorPane1;
    // End of variables declaration//GEN-END:variables
    
}
