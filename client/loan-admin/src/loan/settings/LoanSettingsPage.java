package loan.settings;

import com.rameses.rcp.ui.annotations.StyleSheet;

/*
 * LoanSettingsPage.java
 *
 * Created on March 17, 2011, 1:11 PM
 * @author jaycverg
 */

@StyleSheet
public class LoanSettingsPage extends javax.swing.JPanel {
    
    public LoanSettingsPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        formPanel2 = new com.rameses.rcp.util.FormPanel();
        xActionTextField5 = new com.rameses.rcp.control.XActionTextField();
        xActionTextField6 = new com.rameses.rcp.control.XActionTextField();
        xActionTextField8 = new com.rameses.rcp.control.XActionTextField();
        xActionTextField9 = new com.rameses.rcp.control.XActionTextField();
        xActionTextField10 = new com.rameses.rcp.control.XActionTextField();
        xButton2 = new com.rameses.rcp.control.XButton();
        xButton3 = new com.rameses.rcp.control.XButton();
        xSeparator1 = new com.rameses.rcp.control.XSeparator();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Account Settings");
        jPanel1.setBorder(xTitledBorder1);

        formPanel2.setCaptionWidth(150);
        xActionTextField5.setActionName("lookupRA");
        xActionTextField5.setCaption("Receivable Acct.");
        xActionTextField5.setIndex(-10);
        xActionTextField5.setName("entity.receivable_acct.title");
        xActionTextField5.setPreferredSize(new java.awt.Dimension(150, 18));
        xActionTextField5.setRequired(true);
        formPanel2.add(xActionTextField5);

        xActionTextField6.setActionName("lookupCPA");
        xActionTextField6.setCaption("Principal Acct.");
        xActionTextField6.setName("entity.curr_principal_acct.title");
        xActionTextField6.setPreferredSize(new java.awt.Dimension(150, 18));
        xActionTextField6.setRequired(true);
        formPanel2.add(xActionTextField6);

        xActionTextField8.setActionName("lookupIA");
        xActionTextField8.setCaption("Interest Acct.");
        xActionTextField8.setName("entity.interest_acct.title");
        xActionTextField8.setPreferredSize(new java.awt.Dimension(150, 18));
        xActionTextField8.setRequired(true);
        formPanel2.add(xActionTextField8);

        xActionTextField9.setActionName("lookupSA");
        xActionTextField9.setCaption("Penalty Acct.");
        xActionTextField9.setName("entity.surcharge_acct.title");
        xActionTextField9.setPreferredSize(new java.awt.Dimension(150, 18));
        xActionTextField9.setRequired(true);
        formPanel2.add(xActionTextField9);

        xActionTextField10.setActionName("lookupPDIA");
        xActionTextField10.setCaption("Past Due Acct.");
        xActionTextField10.setName("entity.past_due_interest_acct.title");
        xActionTextField10.setPreferredSize(new java.awt.Dimension(150, 18));
        xActionTextField10.setRequired(true);
        formPanel2.add(xActionTextField10);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 338, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(formPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        xButton2.setText("Update");
        xButton2.setName("toggle");

        xButton3.setMnemonic('c');
        xButton3.setText("Close");
        xButton3.setImmediate(true);
        xButton3.setName("_close");

        org.jdesktop.layout.GroupLayout xSeparator1Layout = new org.jdesktop.layout.GroupLayout(xSeparator1);
        xSeparator1.setLayout(xSeparator1Layout);
        xSeparator1Layout.setHorizontalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 415, Short.MAX_VALUE)
        );
        xSeparator1Layout.setVerticalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 11, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, xSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(273, Short.MAX_VALUE)
                .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(xSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(xButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel2;
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XActionTextField xActionTextField10;
    private com.rameses.rcp.control.XActionTextField xActionTextField5;
    private com.rameses.rcp.control.XActionTextField xActionTextField6;
    private com.rameses.rcp.control.XActionTextField xActionTextField8;
    private com.rameses.rcp.control.XActionTextField xActionTextField9;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XButton xButton3;
    private com.rameses.rcp.control.XSeparator xSeparator1;
    // End of variables declaration//GEN-END:variables
    
}
