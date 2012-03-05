package loan.ui.asset;
/*
 * PhotoLookupPage.java
 *
 * Created on April 11, 2011, 9:29 AM
 * @author jaycverg
 */


public class PhotoLookupPage extends javax.swing.JPanel {
    
    public PhotoLookupPage() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        xImageViewer1 = new com.rameses.rcp.control.XImageViewer();
        xButton1 = new com.rameses.rcp.control.XButton();
        xButton2 = new com.rameses.rcp.control.XButton();
        xSeparator1 = new com.rameses.rcp.control.XSeparator();
        formPanel1 = new com.rameses.rcp.util.FormPanel();
        xFileBrowser1 = new com.rameses.rcp.control.XFileBrowser();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        xTextArea1 = new com.rameses.rcp.control.XTextArea();
        xLabel1 = new com.rameses.rcp.control.XLabel();

        xImageViewer1.setAdvanced(true);
        xImageViewer1.setDepends(new String[] {"photo.file"});
        xImageViewer1.setDynamic(true);
        xImageViewer1.setName("photoView");

        xButton1.setMnemonic('c');
        xButton1.setText("Cancel");
        xButton1.setImmediate(true);
        xButton1.setName("_close");

        xButton2.setMnemonic('s');
        xButton2.setText("Select");
        xButton2.setDefaultCommand(true);
        xButton2.setName("doSelect");

        org.jdesktop.layout.GroupLayout xSeparator1Layout = new org.jdesktop.layout.GroupLayout(xSeparator1);
        xSeparator1.setLayout(xSeparator1Layout);
        xSeparator1Layout.setHorizontalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 430, Short.MAX_VALUE)
        );
        xSeparator1Layout.setVerticalGroup(
            xSeparator1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 11, Short.MAX_VALUE)
        );

        formPanel1.setCaptionWidth(146);
        formPanel1.setPadding(new java.awt.Insets(5, 0, 5, 0));
        xFileBrowser1.setCaption("Image File");
        xFileBrowser1.setName("photo.file");
        xFileBrowser1.setPreferredSize(new java.awt.Dimension(260, 18));
        formPanel1.add(xFileBrowser1);

        xTextField1.setCaption("Name");
        xTextField1.setName("photo.name");
        xTextField1.setPreferredSize(new java.awt.Dimension(260, 19));
        formPanel1.add(xTextField1);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(260, 75));
        xTextArea1.setColumns(20);
        xTextArea1.setRows(5);
        xTextArea1.setCaption("Comment");
        xTextArea1.setName("photo.description");
        jScrollPane2.setViewportView(xTextArea1);

        formPanel1.add(jScrollPane2);

        xLabel1.setText("<html><b>Preview</b></html>");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, xSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xImageViewer1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(xLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xImageViewer1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(formPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(xSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(xButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(xButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.util.FormPanel formPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.rameses.rcp.control.XButton xButton1;
    private com.rameses.rcp.control.XButton xButton2;
    private com.rameses.rcp.control.XFileBrowser xFileBrowser1;
    private com.rameses.rcp.control.XImageViewer xImageViewer1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XSeparator xSeparator1;
    private com.rameses.rcp.control.XTextArea xTextArea1;
    private com.rameses.rcp.control.XTextField xTextField1;
    // End of variables declaration//GEN-END:variables
    
}
