import com.rameses.rcp.support.FormSupport;
import junit.framework.*;
/*
 * NewEmptyJUnitTest.java
 * JUnit based test
 *
 * Created on March 4, 2011, 3:47 PM
 */

/**
 *
 * @author ms
 */
public class NewEmptyJUnitTest extends TestCase {
    
    public NewEmptyJUnitTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    public void testHello() {
        FormSupport.buildFormControls(new List(), new List(), "", new HashMap(), null);
    }

}
