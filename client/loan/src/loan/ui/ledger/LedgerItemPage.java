package loan.ui.ledger;

import com.rameses.rcp.ui.annotations.Template;
import templates.FormTemplate;

/*
 * LedgerItemPage.java
 *
 * Created on May 2, 2011, 10:19 AM
 * @author jaycverg
 */

@Template(FormTemplate.class)
public class LedgerItemPage extends javax.swing.JPanel {
    
    public LedgerItemPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xDataTable1 = new com.rameses.rcp.control.XDataTable();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        jPanel1 = new javax.swing.JPanel();
        xLabel2 = new com.rameses.rcp.control.XLabel();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xLabel3 = new com.rameses.rcp.control.XLabel();
        xLabel4 = new com.rameses.rcp.control.XLabel();
        xLabel5 = new com.rameses.rcp.control.XLabel();
        xLabel7 = new com.rameses.rcp.control.XLabel();
        xLabel6 = new com.rameses.rcp.control.XLabel();
        formPanel2 = new com.rameses.rcp.util.FormPanel();
        xLabel8 = new com.rameses.rcp.control.XLabel();
        xLabel9 = new com.rameses.rcp.control.XLabel();
        xLabel13 = new com.rameses.rcp.control.XLabel();
        xSeparator1 = new com.rameses.rcp.control.XSeparator();

        xDataTable1.setAutoResize(false);
        xDataTable1.setHandler("listHandler");
        xDataTable1.setScrollbarAlwaysVisible(true);

        xLabel1.setText("<html><b>Ledger Details</b></html>");

        xLabel2.setText("<html><h2>#{entity.appno} - #{entity.fullborrowername}</h2></html>");

        xLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel3.setForeground(new java.awt.Color(51, 102, 255));
        xLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel3.setCaption("Loan Amount");
        xLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel3.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel3.setName("ledger.loanAmount");
        xLabel3.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel3);

        xLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel4.setForeground(new java.awt.Color(0, 204, 51));
        xLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel4.setCaption("Date Granted");
        xLabel4.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel4.setFormat(new java.text.SimpleDateFormat("MMMM dd, yyyy"));
        xLabel4.setName("ledger.dtcreated");
        xLabel4.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel4);

        xLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel5.setForeground(new java.awt.Color(204, 0, 0));
        xLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel5.setCaption("Maturity Date");
        xLabel5.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel5.setFormat(new java.text.SimpleDateFormat("MMMM dd, yyyy"));
        xLabel5.setName("ledger.maturityDate");
        xLabel5.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel5);

        xLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel7.setCaption("Term");
        xLabel7.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel7.setName("ledger.term");
        xLabel7.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel7);

        xLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel6.setForeground(new java.awt.Color(204, 0, 204));
        xLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel6.setCaption("Daily Payment");
        xLabel6.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel6.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel6.setName("ledger.dailyPayment");
        xLabel6.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel1.add(xLabel6);

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

        xLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        xLabel13.setForeground(new java.awt.Color(0, 153, 153));
        xLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        xLabel13.setCaption("Total Payment");
        xLabel13.setFont(new java.awt.Font("Arial", 1, 12));
        xLabel13.setFormat(new java.text.DecimalFormat("#,##0.00"));
        xLabel13.setName("ledger.totalPaid");
        xLabel13.setPreferredSize(new java.awt.Dimension(0, 22));
        formPanel2.add(xLabel13);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, xLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 309, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(formPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(xLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(formPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        org.jdesktop.layout.GroupLayout xSeparator1Layout = new org.jdesktop.layout.GroupLayout(xSeparator1);
        xSeparator1.setLayout(xSeparator1Layout);
        xSeparator1Layout.setHorizontalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 732, Short.MAX_VALUE)
        );
        xSeparator1Layout.setVerticalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 12, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xDataTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.util.FormPanel formPanel2;
    private javax.swing.JPanel jPanel1;
    private com.rameses.rcp.control.XDataTable xDataTable1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel13;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLabel xLabel3;
    private com.rameses.rcp.control.XLabel xLabel4;
    private com.rameses.rcp.control.XLabel xLabel5;
    private com.rameses.rcp.control.XLabel xLabel6;
    private com.rameses.rcp.control.XLabel xLabel7;
    private com.rameses.rcp.control.XLabel xLabel8;
    private com.rameses.rcp.control.XLabel xLabel9;
    private com.rameses.rcp.control.XSeparator xSeparator1;
    // End of variables declaration//GEN-END:variables
    
}
