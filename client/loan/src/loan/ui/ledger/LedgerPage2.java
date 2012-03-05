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
public class LedgerPage2 extends javax.swing.JPanel {
    
    public LedgerPage2() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xLabel5 = new com.rameses.rcp.control.XLabel();
        xLabel2 = new com.rameses.rcp.control.XLabel();
        xLabel3 = new com.rameses.rcp.control.XLabel();
        xLabel6 = new com.rameses.rcp.control.XLabel();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xLabel7 = new com.rameses.rcp.control.XLabel();
        xLabel4 = new com.rameses.rcp.control.XLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        formPanel2 = new com.rameses.rcp.util.FormPanel();
        xLabel8 = new com.rameses.rcp.control.XLabel();
        xLabel9 = new com.rameses.rcp.control.XLabel();
        xLabel10 = new com.rameses.rcp.control.XLabel();
        xLabel11 = new com.rameses.rcp.control.XLabel();
        xLabel12 = new com.rameses.rcp.control.XLabel();
        xLabel13 = new com.rameses.rcp.control.XLabel();
        jPanel3 = new javax.swing.JPanel();
        xLabel14 = new com.rameses.rcp.control.XLabel();

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder1 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder1.setTitle("Loan Information");
        jPanel1.setBorder(xTitledBorder1);

        formPanel1.setCaptionWidth(120);
        xLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel5.setForeground(new java.awt.Color(51, 102, 255));
        xLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel5.setCaption("Loan Amount");
        xLabel5.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel5.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel5.setName("ledger.loanAmount");
        xLabel5.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel5);

        xLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel2.setForeground(new java.awt.Color(0, 204, 51));
        xLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel2.setCaption("Date Granted");
        xLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel2.setFormat(new java.text.SimpleDateFormat("MMMM dd, yyyy"));
        xLabel2.setName("ledger.dtcreated");
        xLabel2.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel2);

        xLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel3.setForeground(new java.awt.Color(204, 0, 0));
        xLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel3.setCaption("Maturity Date");
        xLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel3.setFormat(new java.text.SimpleDateFormat("MMMM dd, yyyy"));
        xLabel3.setName("ledger.maturityDate");
        xLabel3.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel3);

        xLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel6.setCaption("Daily Payment");
        xLabel6.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel6.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel6.setName("ledger.dailyPayment");
        xLabel6.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel6);

        xLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel1.setCaption("Term");
        xLabel1.setExpression("#{ledger.term} Days");
        xLabel1.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel1.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel1);

        xLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel7.setCaption("Interest Rate");
        xLabel7.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel7.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel7.setName("ledger.interest");
        xLabel7.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel7);

        xLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel4.setForeground(new java.awt.Color(255, 0, 255));
        xLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel4.setCaption("Last Schedule Paid");
        xLabel4.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel4.setFormat(new java.text.SimpleDateFormat("MMMM dd, yyyy"));
        xLabel4.setName("ledger.lastDatePaid");
        xLabel4.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel4);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 301, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        com.rameses.rcp.control.border.XTitledBorder xTitledBorder2 = new com.rameses.rcp.control.border.XTitledBorder();
        xTitledBorder2.setTitle("Balances");
        jPanel2.setBorder(xTitledBorder2);

        formPanel2.setCaptionWidth(160);
        xLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel8.setForeground(new java.awt.Color(255, 0, 0));
        xLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel8.setCaption("Actual/Loan Balance");
        xLabel8.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel8.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel8.setName("ledger.loanBalance");
        xLabel8.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel2.add(xLabel8);

        xLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel9.setForeground(new java.awt.Color(0, 204, 0));
        xLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel9.setCaption("Principal/Outstanding Balance");
        xLabel9.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel9.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel9.setName("ledger.principalBalance");
        xLabel9.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel2.add(xLabel9);

        xLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel10.setCaption("Total Principal Paid");
        xLabel10.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel10.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel10.setName("ledger.totalPrincipalPaid");
        xLabel10.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel2.add(xLabel10);

        xLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel11.setCaption("Total Interest Paid");
        xLabel11.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel11.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel11.setName("ledger.totalInterestPaid");
        xLabel11.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel2.add(xLabel11);

        xLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel12.setCaption("Total Penalty Paid");
        xLabel12.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel12.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel12.setName("ledger.totalPenaltyPaid");
        xLabel12.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel2.add(xLabel12);

        xLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel13.setForeground(new java.awt.Color(0, 153, 153));
        xLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel13.setCaption("Total Payment");
        xLabel13.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel13.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel13.setName("ledger.totalPaid");
        xLabel13.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel2.add(xLabel13);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(formPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );

        xLabel14.setText("<html><h2>#{entity.appno} - #{entity.fullborrowername}</h2></html>");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(xLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(xLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.util.FormPanel formPanel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel10;
    private com.rameses.rcp.control.XLabel xLabel11;
    private com.rameses.rcp.control.XLabel xLabel12;
    private com.rameses.rcp.control.XLabel xLabel13;
    private com.rameses.rcp.control.XLabel xLabel14;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLabel xLabel3;
    private com.rameses.rcp.control.XLabel xLabel4;
    private com.rameses.rcp.control.XLabel xLabel5;
    private com.rameses.rcp.control.XLabel xLabel6;
    private com.rameses.rcp.control.XLabel xLabel7;
    private com.rameses.rcp.control.XLabel xLabel8;
    private com.rameses.rcp.control.XLabel xLabel9;
    // End of variables declaration//GEN-END:variables
    
}
