package host.ui;

import com.rameses.rcp.constant.TextCase;
import com.rameses.rcp.ui.annotations.StyleSheet;

/*
 * OrgPage.java
 *
 * Created on September 7, 2010, 11:12 AM
 * @author jaycverg
 */

@StyleSheet()
public class HostFormPage extends javax.swing.JPanel {
    
    public HostFormPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xSubControl1 = new com.rameses.rcp.control.XSubControl();
        xComboBox4 = new com.rameses.rcp.control.XComboBox();
        xTextField4 = new com.rameses.rcp.control.XTextField();
        xTextField5 = new com.rameses.rcp.control.XTextField();
        xTextField6 = new com.rameses.rcp.control.XTextField();
        xActionBar1 = new com.rameses.rcp.control.XActionBar();

        formPanel1.setCaptionWidth(100);
        xTextField1.setCaption("Code");
        xTextField1.setIndex(-8);
        xTextField1.setName("entity.code");
        xTextField1.setPreferredSize(new java.awt.Dimension(200, 19));
        xTextField1.setReplaceExpr(new String[] {"\\s+"});
        xTextField1.setReplaceString(new String[] {"_"});
        xTextField1.setRequired(true);
        formPanel1.add(xTextField1);

        xTextField2.setCaption("Name");
        xTextField2.setIndex(-9);
        xTextField2.setName("entity.name");
        xTextField2.setPreferredSize(new java.awt.Dimension(200, 19));
        xTextField2.setRequired(true);
        formPanel1.add(xTextField2);

        xSubControl1.setCaption("Address");
        xSubControl1.setHandler("control:address");
        xSubControl1.setName("entity.address");
        org.jdesktop.layout.GroupLayout xSubControl1Layout = new org.jdesktop.layout.GroupLayout(xSubControl1);
        xSubControl1.setLayout(xSubControl1Layout);
        xSubControl1Layout.setHorizontalGroup(
            xSubControl1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 40, Short.MAX_VALUE)
        );
        xSubControl1Layout.setVerticalGroup(
            xSubControl1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 40, Short.MAX_VALUE)
        );
        formPanel1.add(xSubControl1);

        xComboBox4.setCaption("Time Zone");
        xComboBox4.setDepends(new String[] {"entity.country"});
        xComboBox4.setDynamic(true);
        xComboBox4.setItems("timezoneList");
        xComboBox4.setName("entity.timezone");
        xComboBox4.setPreferredSize(new java.awt.Dimension(200, 19));
        xComboBox4.setRequired(true);
        formPanel1.add(xComboBox4);

        xTextField4.setCaption("Email");
        xTextField4.setIndex(1);
        xTextField4.setName("entity.contact.email");
        xTextField4.setPreferredSize(new java.awt.Dimension(150, 19));
        xTextField4.setTextCase(TextCase.NONE);
        formPanel1.add(xTextField4);

        xTextField5.setCaption("Phone No.");
        xTextField5.setIndex(1);
        xTextField5.setName("entity.contact.phone");
        xTextField5.setPreferredSize(new java.awt.Dimension(150, 19));
        formPanel1.add(xTextField5);

        xTextField6.setCaption("Mobile No.");
        xTextField6.setIndex(1);
        xTextField6.setName("entity.contact.mobile");
        xTextField6.setPreferredSize(new java.awt.Dimension(150, 19));
        formPanel1.add(xTextField6);

        xActionBar1.setDynamic(true);
        xActionBar1.setName("formActions");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(formPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                        .add(10, 10, 10))
                    .add(xActionBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(xActionBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private com.rameses.rcp.control.XActionBar xActionBar1;
    private com.rameses.rcp.control.XComboBox xComboBox4;
    private com.rameses.rcp.control.XSubControl xSubControl1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField4;
    private com.rameses.rcp.control.XTextField xTextField5;
    private com.rameses.rcp.control.XTextField xTextField6;
    // End of variables declaration//GEN-END:variables
    
}
