package loan.assessment.ui;
/*
 * LoanAssessmentPage.java
 *
 * Created on March 15, 2011, 3:00 PM
 * @author jaycverg
 */


public class LoanAssessmentPage extends javax.swing.JPanel {
    
    public LoanAssessmentPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xNumberField5 = new com.rameses.rcp.control.XNumberField();
        xNumberField3 = new com.rameses.rcp.control.XNumberField();
        formPanel2 = new com.rameses.rcp.util.FormPanel();
        xNumberField4 = new com.rameses.rcp.control.XNumberField();
        xLabel2 = new com.rameses.rcp.control.XLabel();
        xLabel3 = new com.rameses.rcp.control.XLabel();
        xDataTable2 = new com.rameses.rcp.control.XDataTable();
        xButton1 = new com.rameses.rcp.control.XButton();
        xButton2 = new com.rameses.rcp.control.XButton();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Loan Assessment");
        jPanel1.setBorder(xTitledBorder1);
        xLabel1.setBorder(new com.rameses.rcp.control.border.XUnderlineBorder());
        xLabel1.setExpression("<html><b>Borrower Name: #{application.borrowername}</b></html>");

        xDataTable1.setHandler("chargeHandler");
        xDataTable1.setImmediate(true);

        formPanel1.setCaptionFont(new java.awt.Font("Arial", 1, 14));
        formPanel1.setCaptionVAlignment(com.rameses.rcp.constant.UIConstants.CENTER);
        formPanel1.setCaptionWidth(100);
        formPanel1.setPadding(new java.awt.Insets(0, 0, 0, 0));
        xNumberField5.setBorder(new com.rameses.rcp.control.border.XUnderlineBorder());
        xNumberField5.setCaption("Total Charges");
        xNumberField5.setCaptionFont(new java.awt.Font("Arial", 1, 11));
        xNumberField5.setFieldType(java.math.BigDecimal.class);
        xNumberField5.setFont(new java.awt.Font("Courier New", 1, 14));
        xNumberField5.setForeground(new java.awt.Color(204, 0, 0));
        xNumberField5.setName("totalCharges");
        xNumberField5.setOpaque(false);
        xNumberField5.setPattern("#,##0.00;(#,##0.00)");
        xNumberField5.setPreferredSize(new java.awt.Dimension(0, 20));
        xNumberField5.setReadonly(true);
        formPanel1.add(xNumberField5);

        xNumberField3.setBorder(new com.rameses.rcp.control.border.XUnderlineBorder());
        xNumberField3.setCaption("Net Amount");
        xNumberField3.setFieldType(java.math.BigDecimal.class);
        xNumberField3.setFont(new java.awt.Font("Courier New", 1, 18));
        xNumberField3.setForeground(new java.awt.Color(204, 0, 0));
        xNumberField3.setName("assessment.netAmount");
        xNumberField3.setOpaque(false);
        xNumberField3.setPattern("#,##0.00");
        xNumberField3.setPreferredSize(new java.awt.Dimension(0, 30));
        xNumberField3.setReadonly(true);
        formPanel1.add(xNumberField3);

        formPanel2.setCaptionFont(new java.awt.Font("Arial", 1, 12));
        formPanel2.setCaptionWidth(100);
        formPanel2.setPadding(new java.awt.Insets(0, 0, 0, 0));
        xNumberField4.setBorder(new com.rameses.rcp.control.border.XUnderlineBorder());
        xNumberField4.setCaption("Loan Granted");
        xNumberField4.setFieldType(java.math.BigDecimal.class);
        xNumberField4.setFont(new java.awt.Font("Courier New", 1, 18));
        xNumberField4.setForeground(new java.awt.Color(204, 0, 0));
        xNumberField4.setName("application.loaninfo.amountapproved");
        xNumberField4.setOpaque(false);
        xNumberField4.setPattern("#,##0.00");
        xNumberField4.setPreferredSize(new java.awt.Dimension(0, 20));
        xNumberField4.setReadonly(true);
        formPanel2.add(xNumberField4);

        xLabel2.setText("<html><b>Finance Charges</b></html>");

        xLabel3.setText("<html><b>Other Charges</b></html>");

        xDataTable2.setHandler("otherChargeHandler");
        xDataTable2.setImmediate(true);
        xDataTable2.setName("selectedOtherCharge");

        xButton1.setMnemonic('a');
        xButton1.setText("Add");
        xButton1.setName("addCharge");

        xButton2.setMnemonic('r');
        xButton2.setText("Remove");
        xButton2.setName("removeCharge");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 273, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xDataTable2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xDataTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .add(formPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 275, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(xLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(xLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(formPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xDataTable2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        xActionBar1.setName("formActions");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(xActionBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.util.FormPanel formPanel2;
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XDataTable xDataTable2;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLabel xLabel3;
    private com.rameses.rcp.control.XNumberField xNumberField3;
    private com.rameses.rcp.control.XNumberField xNumberField4;
    private com.rameses.rcp.control.XNumberField xNumberField5;
    // End of variables declaration//GEN-END:variables
    
}
