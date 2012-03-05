import com.rameses.rcp.control.XDataTable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.*;
import org.apache.commons.beanutils.MethodUtils;
import tester.TestProxy;
/*
 * NewEmptyJUnitTest.java
 * JUnit based test
 *
 * Created on March 16, 2011, 2:48 PM
 */

/**
 *
 * @author rameses
 */
public class NewEmptyJUnitTest extends TestCase {
    
    public NewEmptyJUnitTest(String testName) {
        super(testName);
        
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public void testHello() throws Exception {
        XDataTable tbl = new XDataTable();
        tbl.setScrollbarAlwaysVisible(true);
    }
    
}
