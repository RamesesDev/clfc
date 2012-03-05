
package classes;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.digester.Rule;

public class Rules {
    
    private String str;
    private StringBuffer strbuufer;
    private String fileName;
    private Map params;
    
    public Rules(String fileName, Map params) {
        this.str = "\n package clfc.loan.fees.rule4;\n\n"
        + " import clfc.loan.fees.LoanApplication;\n"
        + " import java.util.*;\n" 
        + " import java.math.*;\n\n"
        + " global java.util.List result;\n\n";
        
        this.fileName = fileName;
        this.params = params;
        strbuufer = new StringBuffer(str);
    }
    
    public static String spaces(int size){
        return String.format(("%" + size + "s"), " ");
    }
    
    public void init(){
        
        List<Map> list = (List<Map>) params.get("list");
        int i = 0;
        for(Map map : list){
            
            strbuufer.append(" rule \"rule" + ++i + "\"\n");
            strbuufer.append(spaces(5) + "when\n");
            strbuufer.append(spaces(9) + "LoanApplication(renewalcount >= " + params.get("fromtimes") + ", renewalcount <= " + params.get("totimes") + 
                    ", $amount: amount > " + map.get("from") + ", amount <= " + map.get("to") + " )\n");
            strbuufer.append(spaces(5) + "then\n");
            strbuufer.append(spaces(9) + "Map map = new HashMap();\n");
            strbuufer.append(spaces(9) + "map.put( \"typed\", \"" + map.get("typed") + "\" );\n");
            strbuufer.append(spaces(9) + "map.put( \"accttitle\", \"" + params.get("accounts") + "\" );\n");
            strbuufer.append(spaces(9) + "map.put( \"amount\", new BigDecimal( " + map.get("amount") + " ) );\n");
            strbuufer.append(spaces(9) + "result.add( map );\n");
            strbuufer.append(" end\n\n");
            
        }
        
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(strbuufer.toString());
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        System.out.println(strbuufer.toString());
        
    }
    
    public static void main(String[] args){
        Map maps = new HashMap();
        List list = new ArrayList();        
        
        for(int i=0; i<6; i++){
            maps.put("from", 1.1);
            maps.put("to", 2.0);
            maps.put("amount", 3.0);
            maps.put("typed", "FIXED");
            list.add(i, maps);
        }
        
        Map mp = new HashMap();
        mp.put("fromtimes", 2);
        mp.put("totimes", 5);
        mp.put("accounts", "DOCUMENTARY STAMPS");
        mp.put("list", list);
        
        Rules rule = new Rules("C:\\Users\\MS\\chodoro\\loantarrif\\src\\loantariff\\ui\\teodoor.txt", mp);
        rule.init();
        
    }
    
}
