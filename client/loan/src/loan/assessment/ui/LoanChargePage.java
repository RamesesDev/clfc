package loan.assessment.ui;

import com.rameses.rcp.ui.annotations.Template;
import templates.PopupTemplate;
/*
 * LoanChargePage.java
 *
 * Created on May 3, 2011, 11:34 AM
 * @author jaycverg
 */

@Template(PopupTemplate.class)
public class LoanChargePage extends javax.swing.JPanel {
    
    public LoanChargePage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xActionTextField1 = new com.rameses.rcp.control.XActionTextField();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();

        xActionTextField1.setEditable(false);
        xActionTextField1.setActionName("lookupAccount");
        xActionTextField1.setCaption("Account");
        xActionTextField1.setName("account");
        xActionTextField1.setPreferredSize(new java.awt.Dimension(200, 19));
        xActionTextField1.setRequired(true);
        formPanel1.add(xActionTextField1);

        xNumberField1.setCaption("Amount");
        xNumberField1.setFieldType(java.math.BigDecimal.class);
        xNumberField1.setName("entity.amount");
        xNumberField1.setPreferredSize(new java.awt.Dimension(100, 19));
        xNumberField1.setRequired(true);
        formPanel1.add(xNumberField1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.control.XActionTextField xActionTextField1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    // End of variables declaration//GEN-END:variables
    
}
