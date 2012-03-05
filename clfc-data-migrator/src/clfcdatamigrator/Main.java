/*
 * Main.java
 *
 * Created on August 1, 2011, 1:44 PM
 */

package clfcdatamigrator;


import com.rameses.sql.SimpleDataSource;
import com.rameses.sql.SqlContext;
import com.rameses.sql.SqlManager;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author jaycverg
 */
public class Main 
{
    
    public static void main(String[] args) 
    {
        String arg = (args!=null && args.length>0)? args[0] : "";
        
        if( "help".equals(arg) || "".equals(arg) ) {
            System.out.println("***********************************************************");
            System.out.println("This is a simple clfc data migrator utility.");
            System.out.println("Actions: c - client   l - loan    p - payment    u - update ledger (build balances and total payments)");
            System.out.println("You can pass options to run an action or actions.");
            System.out.println("Example:\n\tjava -jar clfc-data-migrator.jar cl\n this will migrate only the client and loan data.");
            System.out.println("The steps in migration are as follows: ");
            System.out.println("1. migrate client and payment (these can be done in parallel");
            System.out.println("2. migrate loan (loan depends on client because of its client information)");
            System.out.println("3. run update ledger (this action depends on payment and loan, both should be migrated first before building the balances)");
            System.out.println("***********************************************************");
            return;
        }
        
        Properties props = new Properties();
        InputStream is = null;
        try {
            props.load(is = new FileInputStream("settings.conf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{ is.close(); }catch(Exception ign){}
        }
        
        //put props to system properties
        System.getProperties().putAll(props);
        
        try {
            List<String> scripts = new ArrayList();
            if( arg.contains("c") ) scripts.add("transfer_client.groovy");
            if( arg.contains("l") ) scripts.add("transfer_loan.groovy");
            if( arg.contains("p") ) scripts.add("transfer_loan_payment.groovy");
            if( arg.contains("u") ) scripts.add("process_ledger_items.groovy");
            
            start(scripts);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void start(List<String> scripts) throws Exception 
    {
        SqlContext sqlCtx;
        
        String host =  System.getProperty("db.host");
        String user =  System.getProperty("db.user");
        String pwd =   System.getProperty("db.pwd");
        String tmpDb = System.getProperty("db.tmp.name");

        NumberFormat codeFormatter = new DecimalFormat(System.getProperty("loan.code.format"));
        Logger logger = new Logger();
        logger.log("=== STARTING DATA MIGRATION ===");
        logger.log(" run date: " + new Date() );
        
        RowPointer rowPointer = new RowPointer();
        
        long start = System.currentTimeMillis();
        boolean retry = true;
        
        for(String s : scripts) 
        {
            Script script = loadScript(s);
            retry = true;
            while( retry ) 
            {
                DataSource ds = new SimpleDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://"+host+"/"+tmpDb, user, pwd);
                sqlCtx = SqlManager.getInstance().createContext(ds);

                Map env = new HashMap();
                env.put("sqlCtx", sqlCtx);
                env.put("logger", logger);
                env.put("codeFormatter", codeFormatter);
                env.put("rowPointer", rowPointer);
                script.setBinding(new Binding(env));

                try {
                    script.run();
                    retry = false;
                }
                catch(Exception e) {
                    String err_msg = (e.getMessage()+"");
                    if(err_msg.equals(MigrationDoneException.MSG) || e instanceof MigrationDoneException || e.getCause() instanceof MigrationDoneException) {
                        retry = false; //proceed to next script
                    }
                    else if(err_msg.toLowerCase().contains("communications link failure")) {
                        sqlCtx.closeConnection();

                        try {
                            System.out.println("connection error: trying to reconnect in 5 sec.");
                            Thread.sleep(1000);
                            System.out.println("                  trying to reconnect in 4 sec.");
                            Thread.sleep(1000);
                            System.out.println("                  trying to reconnect in 3 sec.");
                            Thread.sleep(1000);
                            System.out.println("                  trying to reconnect in 2 sec.");
                            Thread.sleep(1000);
                            System.out.println("                  trying to reconnect in 1 sec.");
                            Thread.sleep(1000);
                        } catch (Exception ex) {}
                    }
                    else {
                        throw e;
                    }
                }
            }
        }
        
        logger.log("processed in " +(System.currentTimeMillis()-start)+ " millis.");
        logger.log("=== DATA MIGRATION DONE ===");
        logger.close();
    }
    
    private static Script loadScript(String scriptname) {
        GroovyShell gs = null;
        try {
            InputStream is = Main.class.getResourceAsStream(scriptname);
            return (gs = new GroovyShell()).parse(new InputStreamReader(is));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            gs = null;
        }
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="  Logger Class  ">
    private static class Logger {
    
        private PrintWriter logger;
        
        Logger() {
            try{
                logger = new PrintWriter(new FileWriter("error.log", true));
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        
        public void log(String msg) {
            log(msg, true);
        }

        public void log(String msg, boolean write) {
            System.out.println(msg);
            if( write ) {
                logger.println(msg);
                logger.flush();
            }
        }

        public void close() {
            logger.flush();
            logger.close();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="  RowPointer classs  ">
    public static class RowPointer {
        
        private Map<String, Integer> index = new HashMap();
        
        public int getLastRow(String name) {
            Integer row = index.get(name);
            if( row != null ) return row;
            
            EndPoints ep = getEndPoints(name);
            
            row = read(name);
            if( ep.start >=0 && row <= ep.start )
                row = ep.start - 1;
                
            index.put(name, row);
            
            if( ep.end >= 0 && row >= ep.end )
                throw new MigrationDoneException();
            
            return row;
        }
        
        public void updateLastRow(String name, int row) {
            index.remove(name);
            write(name, row);
            
            EndPoints ep = getEndPoints(name);
            if( ep.end >= 0 && row >= ep.end )
                throw new MigrationDoneException();
        }
        
        private int read(String name) {
            BufferedReader rdr = null;
            try {
                rdr = new BufferedReader(new InputStreamReader(new FileInputStream(name+".point")));
                return Integer.valueOf(rdr.readLine());
            } catch (IOException e){
                //do nothing
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(rdr != null) try{ rdr.close(); }catch(Exception e){};
            }
            
            return -1;
        }
        
        private void write(String name, int row) {
            PrintWriter wr = null;
            try {
                wr = new PrintWriter(name+".point");
                wr.println( row+"" );
                wr.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(wr != null) try{ wr.close(); }catch(Exception e){;}
            }
        }
        
        private Map<String, EndPoints> endPointsIndex = new HashMap();
        
        private EndPoints getEndPoints(String name) {
            EndPoints ep = endPointsIndex.get(name);
            if( ep != null ) return ep;
            
            endPointsIndex.put(name, (ep = new EndPoints()));
            String s = System.getProperty(name+".startrow");
            String e = System.getProperty(name+".endrow");
            
            try {
                ep.start = Integer.valueOf(s);
            } catch(Exception ign){}
            
            try {
                ep.end = Integer.valueOf(e);
            } catch (Exception ign) {}
            
            return ep;
        }
        
    }
    
    private static class EndPoints {
        
        public int start = -1;
        public int end = -1;
        
    }
    
    public static class MigrationDoneException extends RuntimeException {
        
        public static String MSG = "MIGRATION_DONE";
        
        public MigrationDoneException() {
            super(MSG);
        }
        
    }
    //</editor-fold>
    
}
