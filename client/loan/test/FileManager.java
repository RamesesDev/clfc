/*
 * FileManager.java
 *
 * Created on April 5, 2011, 2:26 PM
 * @author jaycverg
 */

import com.rameses.rcp.common.ScheduledTask;
import com.rameses.rcp.framework.ClientContext;
import com.rameses.rcp.framework.TaskManager;
import com.rameses.util.ValueUtil;
import java.util.List;
import java.util.Vector;

public class FileManager {
    
    private static FileManager instance;
    
    public synchronized static FileManager getInstance() {
        if( instance != null ) return instance;
        instance = new FileManager();
        return instance;
    }
    
    private List<ResourceProcessor> resourceList = new Vector();
    private Listener listener;
    
    private ResourceListener resListener = new ResourceListener() {
        
        public void onMessage(ResourceProcessor resource, String message) {
            if( listener != null ) listener.onMessage(resource, message);
        }
        
        public void onCompleted(ResourceProcessor resource) {
            resourceList.remove(resource);
            if( listener != null ) listener.onResourceCompleted(resource);
        }
        
    };
    
    
    public FileManager() {
    }
    
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    
    public void add(ResourceProcessor res) {
        resourceList.add(res);
        res.setListener(resListener);
        if( listener != null ) {
            listener.onResourceAdded(res);
        }
        
        TaskManager taskmgr = ClientContext.getCurrentContext().getTaskManager();
        taskmgr.addTask( new ResourceTask(res) );
    }
    
    public void close() {
        for(ResourceProcessor res : resourceList) {
            res.setCancelled(true);
        }
        resourceList.clear();
    }
    
    public List getResources() {
        return resourceList;
    }
    
    
    public static interface Listener {
        
        void onMessage(ResourceProcessor res, String msg);
        void onResourceAdded(ResourceProcessor res);
        void onResourceCompleted(ResourceProcessor res);
        
    }
    
    private class ResourceTask extends ScheduledTask {
        
        private ResourceProcessor resource;
        
        ResourceTask(ResourceProcessor resource) {
            this.resource = resource;
        }
        
        public long getInterval() { return 10; }
        
        public void execute() {
            if( ValueUtil.isEmpty(resource.getMessage()) )
                resource.setMessage("Processing....");
            
            resource.run();
        }
        
        public boolean isEnded() {
            return resource.isCompleted() || resource.isCancelled();
        }
        
    }
    
}
