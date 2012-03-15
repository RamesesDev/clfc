/*
 * LoanRemittanceShortagePage.java
 *
 * Created on March 4, 2012, 4:23 PM
 */

package loantreasury.remittance.field;

/**
 *
 * @author  Amancio Family
 */
public class LoanRemittance_ShortageOverPaymentPage extends javax.swing.JPanel {
    
    /** Creates new form LoanRemittanceShortagePage */
    public LoanRemittance_ShortageOverPaymentPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        xTextArea1 = new com.rameses.rcp.control.XTextArea();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xNumberField1 = new com.rameses.rcp.control.XNumberField();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();

        xTextArea1.setColumns(20);
        xTextArea1.setRows(5);
        xTextArea1.setWrapStyleWord(true);
        xTextArea1.setCaption("Remarks");
        xTextArea1.setHint("Specify your remarks here.");
        xTextArea1.setName("entity.reasons");
        xTextArea1.setRequired(true);
        xTextArea1.setShowCaption(false);
        xTextArea1.setToolTipText("Type your remarks here.");
        jScrollPane1.setViewportView(xTextArea1);

        formPanel1.setCaptionWidth(102);
        xTextField1.setCaption("Collector's Name");
        xTextField1.setName("entity.collector");
        xTextField1.setPreferredSize(new java.awt.Dimension(150, 19));
        xTextField1.setReadonly(true);
        xTextField1.setToolTipText("Collector's Name");
        formPanel1.add(xTextField1);

        xDateField1.setCaption("Date");
        xDateField1.setName("entity.date");
        xDateField1.setPreferredSize(new java.awt.Dimension(150, 19));
        xDateField1.setReadonly(true);
        xDateField1.setToolTipText("Date");
        formPanel1.add(xDateField1);

        xTextField3.setCaption("Route");
        xTextField3.setName("entity.routedescription");
        xTextField3.setPreferredSize(new java.awt.Dimension(150, 19));
        xTextField3.setReadonly(true);
        xTextField3.setToolTipText("Time of Arrival");
        formPanel1.add(xTextField3);

        xTextField2.setCaption("Time of Arrival");
        xTextField2.setName("entity.arrivaltime");
        xTextField2.setPreferredSize(new java.awt.Dimension(150, 19));
        xTextField2.setReadonly(true);
        xTextField2.setToolTipText("Time of Arrival");
        formPanel1.add(xTextField2);

        xNumberField1.setCaption("Amount");
        xNumberField1.setFieldType(java.math.BigDecimal.class);
        xNumberField1.setFont(new java.awt.Font("Arial", 1, 20));
        xNumberField1.setForeground(new java.awt.Color(204, 0, 0));
        xNumberField1.setName("entity.amount");
        xNumberField1.setPattern("#,##0.00");
        xNumberField1.setPreferredSize(new java.awt.Dimension(150, 40));
        xNumberField1.setReadonly(true);
        xNumberField1.setToolTipText("Amount Shortage");
        formPanel1.add(xNumberField1);

        xLabel1.setText("Reasons :");

        xActionBar1.setName("formActions");
        xActionBar1.setOpaque(false);
        xActionBar1.setUseToolBar(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .add(xLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XNumberField xNumberField1;
    private com.rameses.rcp.control.XTextArea xTextArea1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
    
}
