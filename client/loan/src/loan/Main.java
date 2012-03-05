/*
 * Main.java
 *
 * Created on March 3, 2011, 9:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package loan;

import com.rameses.osiris2.client.OsirisAppContext;
import com.rameses.osiris2.client.OsirisTestPlatform;
import com.rameses.rcp.framework.ClientContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.UIManager;

/**
 *
 * @author rameses
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        try {
            String os = System.getProperty("os.name");
            if( os.toLowerCase().contains("windows") )
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            else
                UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
        } catch(Exception e) {;}
        
        
        Map map=new HashMap();
        map.put("default.host","localhost:8080");
        //map.put("default.host","10.10.106.254:8080");
        map.put("app.context","clfc");
        
        List perms = new ArrayList();
//        perms.add("loan:application.viewPendingList");
//        perms.add("loan:application.viewForInspectionList");
//        perms.add("loan:application.viewForApprovalList");
//        perms.add("loan:application.viewApprovedList");
//        perms.add("loan:application.viewForReleaseList");
//        perms.add("loan:application.viewReleasedList");
//        
//        perms.add("loan:application.create");
//        perms.add("loan:application.edit");
//        perms.add("loan:application.print");
//        perms.add("loan:application.submitForInspection");
//        perms.add("loan:application.submitForApproval");
//        perms.add("loan:application.approve");
//        perms.add("loan:application.submitForRelease");
//        perms.add("loan:application.viewList");     
        perms.add(".*");
        map.put("CLIENT_PERMISSIONS", perms);
        
        OsirisTestPlatform.runTest(map);
    }   
}