/*
 * AssetUtil.java
 *
 * Created on March 21, 2011, 2:43 PM
 * @author jaycverg
 */

package loan.ui.asset.util;

import com.rameses.io.FileUtil;
import java.io.File;
import java.io.FileOutputStream;

public class AssetUtil {
    
    private static final long serialVersionUID = 1L;
    private static final String RES_DIR = System.getProperty("user.dir") + "/cache/loan/";
    private static final String RES_DIR_TMP = System.getProperty("user.dir") + "/tmp/loan/";
    private static final int READ_SIZE = 1024 * 60;
    private static final int BUFFER_SIZE = 1024 * 8;
    
    
    private static String getFileId(String appid, String fileid, boolean temp) {
        StringBuffer sb = new StringBuffer();
        
        if( appid == null ) throw new RuntimeException("Application id is required.");
        
        sb.append("/LOAN_APP_" + appid.hashCode() );
        if( fileid != null) sb.append("/FILE_" + fileid.hashCode() );
        
        sb.insert(0, temp? RES_DIR_TMP : RES_DIR);
        
        return sb.toString();
    }
    
    /**
     * reads file from cache dir
     */
    public static File getFile(String appid, String fileid) {
        File f = new File( getFileId(appid, fileid, false) );
        if( f.exists() && !f.isDirectory() )
            return f;
        
        return null;
    }
    
    /**
     * reads file from temp dir
     */
    public static File getFileForUpload(String appid, String fileid) {
        File f = new File( getFileId(appid, fileid, true) );
        if( f.exists() && !f.isDirectory() )
            return f;
        
        return null;
    }
    
    /**
     * utility to transfer images to temp dir for upload
     */
    public static File transferImage(File source, String appid, String fileid) {
        File target = new File( getFileId(appid, fileid, true) );
        
        if( !target.getParentFile().exists() )
            target.getParentFile().mkdirs();
        
        FileUtil.copy(source, target);
        
        return target;
    }
    
    /**
     * clears all resources from temp and cache directories for a specified application
     */
    public static void clearResources(String appid) {
        removeImage(appid, null, true);
        removeImage(appid, null, false);
    }
    
    /**
     * removes image from cache
     */
    public static void removeImage(String appid, String fileid) {
        File f = new File( getFileId(appid, fileid, false) );
        
        if( !f.exists() ) return;
        
        if( f.isDirectory() )
            removeRecursive( f );
        else
            f.delete();
    }
    
    public static void removeImage(String appid, String fileid, boolean temp) {
        File f = new File( getFileId(appid, fileid, temp) );
        
        if( !f.exists() ) return;
        
        if( f.isDirectory() )
            removeRecursive( f );
        else
            f.delete();
    }
    
    public static void appendData(String appid, String fileid, byte[] bytes) {
        File f = new File( getFileId(appid, fileid, false) );
        FileOutputStream fos = null;
        
        try {
            if( f.exists() ) {
                fos = new FileOutputStream(f, true);
            } else {
                if( !f.getParentFile().exists() ) f.getParentFile().mkdirs();
                fos = new FileOutputStream(f);
            }
            fos.write(bytes);
            fos.flush();
            
        } catch(Exception e) {
            //ignore
        } finally {
            try { fos.close(); }catch(Exception e){}
        }
    }
        
    // helper method(s)
    private static void removeRecursive(File f) {
        if( !f.exists() ) return;
        
        if( f.isDirectory() ) {
            for(File cf : f.listFiles()) {
                removeRecursive(cf);
            }
        }
        
        f.delete();
    }   
}