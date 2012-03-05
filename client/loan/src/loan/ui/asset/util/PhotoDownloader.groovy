package loan.ui.asset.util;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;

public class PhotoDownloader implements Runnable
{

    def appid;
    def images = []; //list of photo IDs
    def onCompleted;
    def onCancelled;
    def onMessage;
    
    private boolean cancelled = false;
    private def _svc;
    
    def getSvc() {
        return _svc? _svc : (_svc = InvokerProxy.getInstance().create('LoanAttachmentsService'));
    }
    
    void start() {
        if( !images ) return;
        new Thread(this).start();
    }

    void run() {
        def param = [:];
        def imageid;
        def server_lastmodified;
        boolean next = true;
        def maxcount = images.size();
        def ctr = 0;
        
        try {
            while( !cancelled ) {
                if( next ) {
                    if( imageid ) {
                        //set the last modified of the last file downloaded
                        def file = AssetUtil.getFile( appid, imageid );
                        if( file ) file.setLastModified( server_lastmodified );
                    }
                    if( images.isEmpty() ) break;

                    ctr++;
                    imageid = images.remove(0);
                    param.clear();
                    if( onMessage ) onMessage("Downloading $ctr / $maxcount");
                }

                param.appid = appid;
                param.objid = imageid;
                def result = svc.readByBlock( param );
                
                if( next && result ) {
                    server_lastmodified = result.lastModified;
                    def file = AssetUtil.getFile( appid, imageid );
                    if( file && file.lastModified() == result.lastModified && file.length() == result.fileLength ) {
                        println "Skipping download for $appid, the cache is up to date."
                        continue;
                    }
                    
                    AssetUtil.removeImage( appid, imageid );
                    next = false;
                }

                if( result ) {
                    AssetUtil.appendData( appid, imageid, result.remove('content') );
                    param.putAll( result );

                    if( result.last ) {
                        next = true;
                    }
                }
                else {
                    next = true;
                }
            }
        }
        catch(e) {
            println "download error:";
            e.printStackTrace();
            MsgBox.alert(e.message);
        }
        
        if( onCompleted && !cancelled ) onCompleted();
    }

    void cancel() {
        cancelled = true;
        if( onCancelled ) onCancelled();
    }

}