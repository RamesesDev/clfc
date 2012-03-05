/*
 * OpenerQueuePage.java
 *
 * Created on January 2, 2011, 8:20 AM
 */

package shared.custom.address;

import com.rameses.rcp.ui.annotations.StyleSheet;


/**
 *
 * @author  ms
 */
@StyleSheet
public class PhAddressControl extends javax.swing.JPanel {
    
    /** Creates new form OpenerQueuePage */
    public PhAddressControl() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xLabel1 = new com.rameses.rcp.control.XLabel();

        setLayout(null);

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(359, 60));
        xTextField1.setCaption("Street");
        xTextField1.setHint("Address 1");
        xTextField1.setName("context.address1");
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField1.setRequired(true);
        xTextField1.setShowCaption(false);
        xTextField1.setToolTipText("Address 1");
        add(xTextField1);
        xTextField1.setBounds(0, 0, 316, 19);

        xTextField2.setHint("Address 2");
        xTextField2.setName("context.address2");
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 19));
        xTextField2.setShowCaption(false);
        xTextField2.setToolTipText("Address 2");
        add(xTextField2);
        xTextField2.setBounds(0, 20, 316, 19);

        xTextField4.setCaption("Province");
        xTextField4.setHint("Province");
        xTextField4.setName("context.province");
        xTextField4.setRequired(true);
        xTextField4.setShowCaption(false);
        xTextField4.setToolTipText("Province");
        add(xTextField4);
        xTextField4.setBounds(0, 40, 92, 19);

        xTextField3.setCaption("City");
        xTextField3.setHint("City");
        xTextField3.setName("context.city");
        xTextField3.setRequired(true);
        xTextField3.setShowCaption(false);
        xTextField3.setToolTipText("City");
        add(xTextField3);
        xTextField3.setBounds(94, 40, 67, 19);

        xTextField5.setCaption("Zipcode");
        xTextField5.setHint("Zipcode");
        xTextField5.setName("context.zipcode");
        xTextField5.setRequired(true);
        xTextField5.setShowCaption(false);
        xTextField5.setToolTipText("Zipcode");
        xTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xTextField5ActionPerformed(evt);
            }
        });

        add(xTextField5);
        xTextField5.setBounds(164, 40, 88, 19);

        xLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        xLabel1.setName("context.country");
        add(xLabel1);
        xLabel1.setBounds(254, 40, 62, 18);

    }// </editor-fold>//GEN-END:initComponents

    private void xTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xTextField5ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_xTextField5ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    // End of variables declaration//GEN-END:variables
    
}
