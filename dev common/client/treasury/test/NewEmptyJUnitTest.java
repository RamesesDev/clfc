import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.*;
/*
 * NewEmptyJUnitTest.java
 * JUnit based test
 *
 * Created on March 12, 2011, 11:35 AM
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
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testHello() {
        Map m1 = new HashMap();
        m1.put("firstname", "jess");
        List l = new ArrayList();
        l.add("5674431");
        l.add("5674432");
        m1.put("phones",l);
        Map m2 = new HashMap();
        m2.put("firstname", "jess");
        m2.put("lastname", "zamora");
        List l2 = new ArrayList();
        //l2.add("1121212");
        //l2.add("5674430");
        //l2.add("2567654");
        //m2.put("phones", l2);
        Map m = com.rameses.util.MapVersionControl.getInstance().diff(m1,m2);
        System.out.println(m);
    
    }

}
