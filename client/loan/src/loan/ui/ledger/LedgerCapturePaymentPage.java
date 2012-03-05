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
public class LedgerCapturePaymentPage extends javax.swing.JPanel {
    
    public LedgerCapturePaymentPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();

        formPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        xDateField1.setCaption("Date");
        xDateField1.setName("entity.date");
        xDateField1.setPreferredSize(new java.awt.Dimension(120, 19));
        xDateField1.setRequired(true);
        xDateField1.setUseDatePickerModel(true);
        formPanel1.add(xDateField1);

        xTextField1.setCaption("Ref. No.");
        xTextField1.setName("entity.billno");
        xTextField1.setPreferredSize(new java.awt.Dimension(150, 19));
        xTextField1.setRequired(true);
        formPanel1.add(xTextField1);

        xNumberField1.setCaption("Amount");
        xNumberField1.setFieldType(java.math.BigDecimal.class);
        xNumberField1.setFont(new java.awt.Font("Courier New", 1, 14));
        xNumberField1.setName("entity.amount");
        xNumberField1.setPattern("#,##0.00");
        xNumberField1.setPreferredSize(new java.awt.Dimension(150, 23));
        xNumberField1.setRequired(true);
        formPanel1.add(xNumberField1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    private com.rameses.rcp.control.XTextField xTextField1;
    // End of variables declaration//GEN-END:variables
    
}
