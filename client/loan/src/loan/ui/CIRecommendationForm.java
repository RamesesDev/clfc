package loan.ui;

import com.rameses.rcp.ui.annotations.Template;
import templates.PopupTemplate;
/*
 * CIRecommendationForm.java
 *
 * Created on May 27, 2011, 3:54 PM
 * @author jaycverg
 */

@Template(PopupTemplate.class)
public class CIRecommendationForm extends javax.swing.JPanel {
    
    public CIRecommendationForm() {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        xTextArea1 = new com.rameses.rcp.control.XTextArea();
        xLabel1 = new com.rameses.rcp.control.XLabel();

        xTextArea1.setColumns(20);
        xTextArea1.setRows(5);
        xTextArea1.setCaption("CI Recommendation");
        xTextArea1.setHint("Write your recommendation here.");
        xTextArea1.setName("recommendation");
        xTextArea1.setRequired(true);
        xTextArea1.setShowCaption(false);
        jScrollPane1.setViewportView(xTextArea1);

        xLabel1.setText("<html><b>CI Recommendation: </b></html>");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, xLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(xLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XTextArea xTextArea1;
    // End of variables declaration//GEN-END:variables
    
}
