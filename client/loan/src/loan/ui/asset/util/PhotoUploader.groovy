package loan.ui.asset.util;

import com.rameses.osiris2.*;
import com.rameses.osiris2.client.*;
import com.rameses.rcp.common.*;
import com.rameses.platform.interfaces.*;
import com.rameses.io.*;
import com.rameses.invoker.client.*;
import java.util.concurrent.*;
import java.io.*;


public class PhotoUploader {

    //-- static context --
    
    private static String PHOTO_SVC = 'LoanAttachmentsService';
    private static String SVC_METHOD = 'saveByBlock';
    private static def _instance;
    private static def _executorSvc;
    
    public static PhotoUploader getInstance() {
        if( _instance ) return _instance;
        
        return (_instance = new PhotoUploader());
    }
    
    
    //-- instance context --
    
    private def onUpdate;
    private def onListUpdate;
    private def processList = [:];
    
    
    public PhotoUploader() {
        _executorSvc = Executors.newCachedThreadPool();
        
        OsirisContext.mainWindowListener.add([
            onEvent: { eventName, evt -> return null },
            onClose: {
                try {
                    _executorSvc.shutdown();
                }
                catch(e) { e.printStackTrace(); }
                return true;
            }
        ] as MainWindowListener );
    }
    
    public def getList() {
        return processList.values() as List;
    }
    
    public void addAll( appid, photos ) {
        photos.each { add(appid, it) }
    }
    
    public void add( appid, photo ) {
        def file = AssetUtil.getFileForUpload( appid, photo.objid );
        if( !file?.exists() ) return;
        
        def process = [:];
        process.putAll( photo );
        processList[process.objid] = process;
        if( onListUpdate ) onListUpdate();
        
        process.future = _executorSvc.submit({
            try {
                def appEnv = AppContext.instance.env;
                def fout = new DynamicHttpTransfer.Out(appEnv['default.host'], appEnv['app.context'], PHOTO_SVC, SVC_METHOD);
                fout.parameters.putAll([
                    appid: appid, objid: photo.objid
                ]);

                def fsrc = new FileTransfer.FileInputSource( file );
                def ft = new FileTransfer();

                ft.transfer(fsrc, fout, [
                    start: {
                        process.message = "initializing...";
                        if( onUpdate ) onUpdate();
                    },
                    process: {
                        process.message = "processing...";
                        if( onUpdate ) onUpdate();
                    },
                    end: {
                        println "deleting file $file";
                        println file.delete();
                        processList.remove( process.objid );
                        if( onListUpdate ) onListUpdate();
                    }
                ] as FileTransfer.TransferListener );
            }
            catch(e) {
                e.printStackTrace();
                processList.remove( process.objid );
                if( onListUpdate ) onListUpdate();
            }
            
        } as Runnable);
    }
    
    public void close() {
        processList.each { k, process ->
            try {
                if( process.future ) process.future.cancel(true);
            }
            catch(e) { e.printStackTrace() }
        }
        
        processList.clear();
    }
    
}
