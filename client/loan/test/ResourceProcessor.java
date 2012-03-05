/*
 * ResourceProcessor.java
 *
 * Created on April 5, 2011, 3:56 PM
 * @author jaycverg
 */

public abstract class ResourceProcessor {
    
    private ResourceListener listener;
    private String message;
    private boolean completed;
    private boolean cancelled;
    
    
    public abstract String getId();
    public abstract String getTitle();
    public abstract boolean process();
    
    
    public void setListener(ResourceListener listener) {
        this.listener = listener;
    }
    
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ResourceProcessor && this.getId().equals(((ResourceProcessor)obj).getId());
    }
    
    public int hashCode() {
        return (this.getClass() + "-" + getId()).hashCode();
    }
    
    public final void run() {
        completed = process();
        if( completed && listener != null )
            listener.onCompleted(this);
    }
    
    //<editor-fold defaultstate="collapsed" desc="  getters/setters  ">
    public final boolean isCompleted() {
        return completed;
    }
    
    public final void setMessage(String message) {
        this.message = message;
        if( listener != null )
            listener.onMessage(this, message);
    }
    
    public String getMessage() {
        return message;
    }
    
    public boolean isCancelled() {
        return cancelled;
    }
    
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    //</editor-fold>
    
    
    public static interface ResourceListener {
        
        void onMessage(ResourceProcessor resource, String message);
        void onCompleted(ResourceProcessor resource);
        
    }
    
}
