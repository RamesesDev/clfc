/*
 * OtherLendingPage.java
 *
 * Created on August 11, 2011, 10:56 PM
 */

package loan.ui;

/**
 *
 * @author  Amancio Family
 */
public class OtherLendingPage extends javax.swing.JPanel {
    
    /** Creates new form OtherLendingPage */
    public OtherLendingPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel12 = new javax.swing.JPanel();
        formPanel6 = new com.rameses.rcp.util.FormPanel();
        xTextField17 = new com.rameses.rcp.control.XTextField();
        xTextField16 = new com.rameses.rcp.control.XTextField();
        xTextField18 = new com.rameses.rcp.control.XTextField();
        xNumberField2 = new com.rameses.rcp.control.XNumberField();
        xDateField2 = new com.rameses.rcp.control.XDateField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xNumberField28 = new com.rameses.rcp.control.XNumberField();
        xNumberField3 = new com.rameses.rcp.control.XNumberField();
        xComboBox4 = new com.rameses.rcp.control.XComboBox();
        xNumberField8 = new com.rameses.rcp.control.XNumberField();
        xTextArea4 = new com.rameses.rcp.control.XTextArea();
        xSeparator1 = new com.rameses.rcp.control.XSeparator();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        xTextArea3 = new com.rameses.rcp.control.XTextArea();
        xLabel7 = new com.rameses.rcp.control.XLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        xTextArea6 = new com.rameses.rcp.control.XTextArea();
        xLabel8 = new com.rameses.rcp.control.XLabel();

        jPanel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        formPanel6.setCaptionWidth(130);
        formPanel6.setPadding(new java.awt.Insets(5, 5, 5, 10));
        xTextField17.setCaption("Kind of Loan");
        xTextField17.setName("entity.kindofLoan");
        xTextField17.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField17.setRequired(true);
        formPanel6.add(xTextField17);

        xTextField16.setText("entity.company");
        xTextField16.setCaption("Name of Lending Inst.");
        xTextField16.setName("entity.company");
        xTextField16.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField16.setRequired(true);
        formPanel6.add(xTextField16);

        xTextField18.setCaption("Address");
        xTextField18.setName("entity.address");
        xTextField18.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField18.setRequired(true);
        formPanel6.add(xTextField18);

        xNumberField2.setCaption("Amount Loaned");
        xNumberField2.setFieldType(java.math.BigDecimal.class);
        xNumberField2.setName("entity.loanAmount");
        xNumberField2.setPattern("#,##0.00");
        xNumberField2.setPreferredSize(new java.awt.Dimension(100, 19));
        xNumberField2.setRequired(true);
        formPanel6.add(xNumberField2);

        xDateField2.setCaption("Date Granted");
        xDateField2.setName("entity.dateGranted");
        xDateField2.setPreferredSize(new java.awt.Dimension(100, 19));
        xDateField2.setRequired(true);
        xDateField2.setUseDatePickerModel(true);
        formPanel6.add(xDateField2);

        xDateField1.setCaption("Maturity Date");
        xDateField1.setName("entity.maturityDate");
        xDateField1.setPreferredSize(new java.awt.Dimension(100, 19));
        xDateField1.setRequired(true);
        xDateField1.setUseDatePickerModel(true);
        formPanel6.add(xDateField1);

        xNumberField28.setCaption("Term");
        xNumberField28.setDepends(new String[] {"entity.loaninfo.producttype"});
        xNumberField28.setFieldType(Integer.class);
        xNumberField28.setName("entity.term");
        xNumberField28.setPreferredSize(new java.awt.Dimension(100, 19));
        xNumberField28.setRequired(true);
        formPanel6.add(xNumberField28);

        xNumberField3.setCaption("Interest Rate");
        xNumberField3.setFieldType(java.math.BigDecimal.class);
        xNumberField3.setName("entity.interestRate");
        xNumberField3.setPattern("#,##0.00");
        xNumberField3.setPreferredSize(new java.awt.Dimension(100, 19));
        xNumberField3.setRequired(true);
        formPanel6.add(xNumberField3);

        xComboBox4.setCaption("Mode of Payment ");
        xComboBox4.setDynamic(true);
        xComboBox4.setEmptyText("Select Type");
        xComboBox4.setImmediate(true);
        xComboBox4.setItems("otherPaymentList");
        xComboBox4.setName("entity.modeofPayment");
        xComboBox4.setPreferredSize(new java.awt.Dimension(100, 22));
        xComboBox4.setRequired(true);
        formPanel6.add(xComboBox4);

        xNumberField8.setCaption("Loan Payment");
        xNumberField8.setFieldType(java.math.BigDecimal.class);
        xNumberField8.setName("entity.lendingPayment");
        xNumberField8.setPattern("#,##0.00");
        xNumberField8.setPreferredSize(new java.awt.Dimension(100, 19));
        xNumberField8.setRequired(true);
        formPanel6.add(xNumberField8);

        xTextArea4.setColumns(20);
        xTextArea4.setRows(5);
        xTextArea4.setCaption("Collateral Offered");
        xTextArea4.setHint("List of collaterals");
        xTextArea4.setName("entity.collateralOffered");
        xTextArea4.setRequired(true);
        formPanel6.add(xTextArea4);

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(formPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 314, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(formPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        org.jdesktop.layout.GroupLayout xSeparator1Layout = new org.jdesktop.layout.GroupLayout(xSeparator1);
        xSeparator1.setLayout(xSeparator1Layout);
        xSeparator1Layout.setHorizontalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 707, Short.MAX_VALUE)
        );
        xSeparator1Layout.setVerticalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 9, Short.MAX_VALUE)
        );

        xActionBar1.setName("formActions");
        xActionBar1.setUseToolBar(false);

        jPanel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        xTextArea3.setColumns(20);
        xTextArea3.setRows(5);
        xTextArea3.setHint("Specify your remarks here.");
        xTextArea3.setName("entity.remarks");
        jScrollPane8.setViewportView(xTextArea3);

        xLabel7.setText("Remarks :");

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .add(xLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(xLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(113, 113, 113))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        xTextArea6.setColumns(20);
        xTextArea6.setRows(5);
        xTextArea6.setHint("Specify your specifications here.");
        xTextArea6.setName("entity.specs");
        jScrollPane9.setViewportView(xTextArea6);

        xLabel8.setText("Others Specification(s) :");

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(xLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(xLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(22, 22, 22))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, xSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)))
            .add(layout.createSequentialGroup()
                .add(590, 590, 590)
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel6;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XComboBox xComboBox4;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XDateField xDateField2;
    private com.rameses.rcp.control.XLabel xLabel7;
    private com.rameses.rcp.control.XLabel xLabel8;
    private com.rameses.rcp.control.XNumberField xNumberField2;
    private com.rameses.rcp.control.XNumberField xNumberField28;
    private com.rameses.rcp.control.XNumberField xNumberField3;
    private com.rameses.rcp.control.XNumberField xNumberField8;
    private com.rameses.rcp.control.XSeparator xSeparator1;
    private com.rameses.rcp.control.XTextArea xTextArea3;
    private com.rameses.rcp.control.XTextArea xTextArea4;
    private com.rameses.rcp.control.XTextArea xTextArea6;
    private com.rameses.rcp.control.XTextField xTextField16;
    private com.rameses.rcp.control.XTextField xTextField17;
    private com.rameses.rcp.control.XTextField xTextField18;
    // End of variables declaration//GEN-END:variables
    
}
