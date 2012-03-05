package loan.ui.ledger;

import com.rameses.rcp.ui.annotations.Template;
import templates.PopupTemplate;
/*
 * LedgerCapturePaymentPage.java
 *
 * Created on May 9, 2011, 10:17 AM
 * @author jaycverg
 */

@Template(PopupTemplate.class)
public class LedgerAddInterestPage extends javax.swing.JPanel {
    
    public LedgerAddInterestPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();

        formPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xNumberField1.setCaption("No. of Days");
        xNumberField1.setFieldType(java.math.BigDecimal.class);
        xNumberField1.setFont(new java.awt.Font("Courier New", 1, 14));
        xNumberField1.setName("entity.interest");
        xNumberField1.setPreferredSize(new java.awt.Dimension(150, 23));
        xNumberField1.setRequired(true);
        formPanel1.add(xNumberField1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    // End of variables declaration//GEN-END:variables
    
}
