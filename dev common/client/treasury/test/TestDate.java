import com.rameses.util.DateUtil;
import java.util.Date;
import junit.framework.*;

/*
 * TestDate.java
 * JUnit based test
 *
 * Created on February 12, 2011, 2:27 PM
 */

/**
 *
 * @author ms
 */
public class TestDate extends TestCase {
    
    public TestDate(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testHello() {
        
        Date d1 = java.sql.Date.valueOf("2000-01-01");
        Date d2 = java.sql.Date.valueOf("2000-01-03");
        
        Date d3 = java.sql.Date.valueOf("2000-01-09");
        System.out.println(DateUtil.diff(d1,d2));
        //System.out.println(DateUtil.add(d2,d3));
    }

}
