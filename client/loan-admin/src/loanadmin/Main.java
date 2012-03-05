package loanadmin;

import com.rameses.osiris2.client.OsirisTestPlatform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.UIManager;

public class Main {
    
    public static void main(String[] args) throws Exception{
        try {
            String os = System.getProperty("os.name");
            if( os.toLowerCase().contains("windows") )
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            else
                UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
        } catch(Exception e) {;}
        
        Map map=new HashMap();
        map.put("default.host","10.0.0.118:8080");
        map.put("app.context","clfc");
        
        List l = new ArrayList();
        l.add(".*");
        map.put("CLIENT_PERMISSIONS", l);
        OsirisTestPlatform.runTest(map);
    }
    
}