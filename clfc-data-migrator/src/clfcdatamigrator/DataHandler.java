/*
 * DataHandler.java
 *
 * Created on August 5, 2011, 5:52 PM
 */

package clfcdatamigrator;

import com.rameses.sql.MapFetchHandler;
import groovy.lang.Closure;
import java.sql.ResultSet;

/**
 *
 * @author jaycverg
 */
public class DataHandler extends MapFetchHandler {
    
    private Closure handler;
    
    DataHandler(Closure handler) {
        this.handler = handler;
    }
    
    public Object getObject(ResultSet rs) throws Exception {
        Object item = super.getObject(rs);
        if(handler!=null) handler.call(item);
        return null;
    }
    
}
