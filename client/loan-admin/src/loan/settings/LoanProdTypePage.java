package loan.settings;

import com.rameses.rcp.ui.annotations.StyleSheet;

/*
 * LoanSettingsPage.java
 *
 * Created on March 17, 2011, 1:11 PM
 * @author jaycverg
 */

@StyleSheet
public class LoanProdTypePage extends javax.swing.JPanel {
    
    public LoanProdTypePage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        xList1 = new com.rameses.rcp.control.XList();
        jPanel2 = new javax.swing.JPanel();
        formPanel2 = new com.rameses.rcp.util.FormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xNumberField4 = new com.rameses.rcp.control.XNumberField();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();
        xNumberField2 = new com.rameses.rcp.control.XNumberField();
        xNumberField5 = new com.rameses.rcp.control.XNumberField();
        xNumberField3 = new com.rameses.rcp.control.XNumberField();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();
        jPanel3 = new javax.swing.JPanel();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xNumberField6 = new com.rameses.rcp.control.XNumberField();
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Product Types");
        jPanel1.setBorder(xTitledBorder1);
        xList1.setDynamic(true);
        xList1.setExpression("#{code}");
        xList1.setItems("prodTypeList");
        xList1.setName("entity");
        jScrollPane1.setViewportView(xList1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Product Type Information");
        jPanel2.setBorder(xTitledBorder2);

        formPanel2.setCaptionWidth(150);
        xTextField1.setCaption("Code");
        xTextField1.setDepends(new String[] {"entity"});
        xTextField1.setName("entity.code");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 18));
        xTextField1.setRequired(true);
        formPanel2.add(xTextField1);

        xNumberField4.setCaption("Term/Days");
        xNumberField4.setDepends(new String[] {"entity"});
        xNumberField4.setFieldType(int.class);
        xNumberField4.setName("entity.term");
        xNumberField4.setPattern("#,##0");
        xNumberField4.setPreferredSize(new java.awt.Dimension(50, 18));
        xNumberField4.setRequired(true);
        formPanel2.add(xNumberField4);

        xNumberField1.setCaption("Interest (%)");
        xNumberField1.setDepends(new String[] {"entity"});
        xNumberField1.setFieldType(java.math.BigDecimal.class);
        xNumberField1.setName("entity.interestrate");
        xNumberField1.setPattern("#,##0.00");
        xNumberField1.setPreferredSize(new java.awt.Dimension(50, 18));
        xNumberField1.setRequired(true);
        formPanel2.add(xNumberField1);

        xNumberField2.setCaption("Absent Penalty (%)");
        xNumberField2.setDepends(new String[] {"entity"});
        xNumberField2.setFieldType(java.math.BigDecimal.class);
        xNumberField2.setName("entity.absentpenalty");
        xNumberField2.setPattern("#,##0.00");
        xNumberField2.setPreferredSize(new java.awt.Dimension(50, 18));
        xNumberField2.setRequired(true);
        formPanel2.add(xNumberField2);

        xNumberField5.setCaption("Underpayment Penalty (%)");
        xNumberField5.setDepends(new String[] {"entity"});
        xNumberField5.setFieldType(java.math.BigDecimal.class);
        xNumberField5.setName("entity.underpaymentpenalty");
        xNumberField5.setPattern("#,##0.00");
        xNumberField5.setPreferredSize(new java.awt.Dimension(50, 18));
        xNumberField5.setRequired(true);
        formPanel2.add(xNumberField5);

        xNumberField3.setCaption("Past Due (%)");
        xNumberField3.setDepends(new String[] {"entity"});
        xNumberField3.setFieldType(java.math.BigDecimal.class);
        xNumberField3.setName("entity.pastduerate");
        xNumberField3.setPattern("#,##0.00");
        xNumberField3.setPreferredSize(new java.awt.Dimension(50, 18));
        xNumberField3.setRequired(true);
        formPanel2.add(xNumberField3);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(formPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        );

        xActionBar1.setDepends(new String[] {"entity"});
        xActionBar1.setName("formActions");
        xActionBar1.setOpaque(false);
        xActionBar1.setUseToolBar(false);

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder3 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder3.setTitle("Overpayment Settings");
        jPanel3.setBorder(xTitledBorder3);

        formPanel1.setCaptionWidth(150);
        xNumberField6.setCaption("Maximum Failure Count");
        xNumberField6.setFieldType(Integer.class);
        xNumberField6.setName("entity.overpayment_maxfailure");
        xNumberField6.setPattern("#,##0");
        xNumberField6.setPreferredSize(new java.awt.Dimension(150, 18));
        formPanel1.add(xNumberField6);

        xCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        xCheckBox1.setCaption("Consecutive Failure");
        xCheckBox1.setCheckValue(true);
        xCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        xCheckBox1.setName("entity.overpayment_consecutive_failure");
        xCheckBox1.setUncheckValue(false);
        formPanel1.add(xCheckBox1);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xActionBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.util.FormPanel formPanel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XList xList1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    private com.rameses.rcp.control.XNumberField xNumberField2;
    private com.rameses.rcp.control.XNumberField xNumberField3;
    private com.rameses.rcp.control.XNumberField xNumberField4;
    private com.rameses.rcp.control.XNumberField xNumberField5;
    private com.rameses.rcp.control.XNumberField xNumberField6;
    private com.rameses.rcp.control.XTextField xTextField1;
    // End of variables declaration//GEN-END:variables
    
}
