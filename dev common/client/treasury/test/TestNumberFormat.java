import java.text.DecimalFormat;
import java.text.NumberFormat;
import com.rameses.util.DateUtil;
import java.util.Date;
import junit.framework.*;

/*
 * TestNumberFormat.java
 *
 * Created on March 13, 2012, 10:20 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author rameses
 */
public class TestNumber{
    
    public TestNumber(String testName) {
        super(testName);
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testHello() {
        double amount = 2192.015;
        NumberFormat formatter = new DecimalFormat("#0.000");
        System.out.println("The Decimal Value is:"+formatter.format(amount));
    }

}