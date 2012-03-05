import groovy.ui.Console;
import junit.framework.*;
/*
 * GroovyConsoleTest.java
 * JUnit based test
 *
 * Created on May 12, 2011, 12:48 PM
 */

/**
 *
 * @author prmf
 */
public class GroovyConsoleTest extends TestCase {
    
    public GroovyConsoleTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    public void testHello() {
        new Console().run();
    }

}
