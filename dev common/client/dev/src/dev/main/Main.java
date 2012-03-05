package dev.main;
import com.rameses.osiris2.client.OsirisTestPlatform;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.UIManager;



public class Main {
    
    public static void main(String[] args) throws Exception {
        try {
            String os = System.getProperty("os.name");
            if( os.toLowerCase().contains("windows") )
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){;}
        
        Properties p = new Properties();
        InputStream is = Main.class.getResourceAsStream("/META-INF/settings.properties");
        if( is != null ) p.load(is);
        OsirisTestPlatform.runTest(p);
    }
    
}
