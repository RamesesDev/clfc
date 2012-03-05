package treasury.tester;
/*
 * Main.java
 *
 * Created on February 20, 2011, 2:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import com.rameses.osiris2.client.OsirisTestPlatform;
import java.util.HashMap;
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
        
        Map x=new HashMap();
        x.put("default.host","10.0.0.118:8080");
        x.put("app.context","clfc");
        OsirisTestPlatform.runTest(x);
        
    }
    
}
