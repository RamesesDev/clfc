/*
 * LoanCollection.java
 *
 * Created on April 10, 2011, 1:13 PM
 */

package loantreasury.collection.ui;
import com.rameses.rcp.ui.annotations.StyleSheet;

/**
 *
 * @author  rameses
 */
@StyleSheet
public class LoanCollection extends javax.swing.JPanel {
    
    /** Creates new form LoanCollection */
    public LoanCollection() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xActionBar2 = new com.rameses.rcp.control.XActionBar();
        jPanel1 = new javax.swing.JPanel();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xActionTextField1 = new com.rameses.rcp.control.XActionTextField();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();
        xNumberField5 = new com.rameses.rcp.control.XNumberField();
        xNumberField2 = new com.rameses.rcp.control.XNumberField();
        xNumberField3 = new com.rameses.rcp.control.XNumberField();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();

        xActionBar2.setName("formActions");

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Loan Payment");
        jPanel1.setBorder(xTitledBorder1);
        jPanel1.setToolTipText("Loan Payment");
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 0));

        formPanel1.setCaptionWidth(90);
        formPanel1.setPreferredSize(new java.awt.Dimension(250, 92));
        xActionTextField1.setActionName("lookup");
        xActionTextField1.setCaption("Payee");
        xActionTextField1.setCaptionWidth(105);
        xActionTextField1.setFont(new java.awt.Font("Arial", 0, 12));
        xActionTextField1.setName("entity.borrowername");
        xActionTextField1.setPreferredSize(new java.awt.Dimension(150, 21));
        xActionTextField1.setRequired(true);
        xActionTextField1.setToolTipText("Input Borrower's Name");
        formPanel1.add(xActionTextField1);

        xNumberField1.setText("Amount Due");
        xNumberField1.setCaption("Amount Due");
        xNumberField1.setCaptionWidth(105);
        xNumberField1.setFieldType(java.math.BigDecimal.class);
        xNumberField1.setFont(new java.awt.Font("Arial", 1, 16));
        xNumberField1.setName("entity.amountDue");
        xNumberField1.setPattern("#,##0.00");
        xNumberField1.setPreferredSize(new java.awt.Dimension(150, 23));
        xNumberField1.setReadonly(true);
        xNumberField1.setRequired(true);
        xNumberField1.setToolTipText("Amount Due");
        formPanel1.add(xNumberField1);

        xNumberField5.setText("Amount");
        xNumberField5.setCaption("Amount To Pay");
        xNumberField5.setCaptionWidth(105);
        xNumberField5.setFieldType(java.math.BigDecimal.class);
        xNumberField5.setFont(new java.awt.Font("Arial", 1, 16));
        xNumberField5.setForeground(new java.awt.Color(51, 51, 255));
        xNumberField5.setName("entity.amount");
        xNumberField5.setPattern("#,##0.00");
        xNumberField5.setPreferredSize(new java.awt.Dimension(150, 23));
        xNumberField5.setRequired(true);
        xNumberField5.setToolTipText("Amount Paid");
        formPanel1.add(xNumberField5);

        xNumberField2.setText("Amount Tendered");
        xNumberField2.setCaption("Amount Tendered");
        xNumberField2.setCaptionWidth(105);
        xNumberField2.setFieldType(java.math.BigDecimal.class);
        xNumberField2.setFont(new java.awt.Font("Arial", 1, 16));
        xNumberField2.setName("entity.amountTendered");
        xNumberField2.setPattern("#,##0.00");
        xNumberField2.setPreferredSize(new java.awt.Dimension(150, 23));
        xNumberField2.setReadonly(true);
        xNumberField2.setRequired(true);
        xNumberField2.setToolTipText("Amount Paid");
        formPanel1.add(xNumberField2);

        xNumberField3.setText("Change");
        xNumberField3.setCaption("Change");
        xNumberField3.setCaptionWidth(105);
        xNumberField3.setFieldType(java.math.BigDecimal.class);
        xNumberField3.setFont(new java.awt.Font("Arial", 1, 16));
        xNumberField3.setForeground(new java.awt.Color(255, 51, 51));
        xNumberField3.setName("entity.change");
        xNumberField3.setPattern("#,##0.00");
        xNumberField3.setPreferredSize(new java.awt.Dimension(150, 23));
        xNumberField3.setReadonly(true);
        xNumberField3.setRequired(true);
        xNumberField3.setToolTipText("Amount Change");
        formPanel1.add(xNumberField3);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        xActionBar1.setName("paymentOptions");
        xActionBar1.setUseToolBar(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(xActionBar2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 348, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(xActionBar2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 189, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XActionBar xActionBar2;
    private com.rameses.rcp.control.XActionTextField xActionTextField1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    private com.rameses.rcp.control.XNumberField xNumberField2;
    private com.rameses.rcp.control.XNumberField xNumberField3;
    private com.rameses.rcp.control.XNumberField xNumberField5;
    // End of variables declaration//GEN-END:variables
    
}
