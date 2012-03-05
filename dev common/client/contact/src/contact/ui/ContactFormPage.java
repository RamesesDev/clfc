/*
 *
 * Created on December 30, 2010, 9:50 AM
 */

package contact.ui;

import com.rameses.rcp.ui.annotations.StyleSheet;



/**
 *
 * @author  ms
 */
@StyleSheet
public class ContactFormPage extends javax.swing.JPanel {
    
    public ContactFormPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        frmCustomFields = new com.rameses.rcp.util.FormPanel();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();

        setPreferredSize(new java.awt.Dimension(496, 497));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        frmCustomFields.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        frmCustomFields.setCaptionBorder(new com.rameses.rcp.control.border.XUnderlineBorder());
        frmCustomFields.setCaptionPadding(new java.awt.Insets(2, 2, 2, 2));
        frmCustomFields.setCaptionWidth(120);
        frmCustomFields.setDynamic(true);
        frmCustomFields.setEmptyText("No records found.");
        frmCustomFields.setName("customFieldsModel");
        frmCustomFields.setPadding(new java.awt.Insets(0, 0, 0, 0));
        frmCustomFields.setViewType("html");
        jScrollPane3.setViewportView(frmCustomFields);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        xActionBar1.setName("formActions");
        xActionBar1.setPadding(new java.awt.Insets(1, 1, 1, 1));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, xActionBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel frmCustomFields;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    // End of variables declaration//GEN-END:variables
    
}
