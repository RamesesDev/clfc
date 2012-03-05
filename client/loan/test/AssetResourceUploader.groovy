package loan.ui.asset.util;

import com.rameses.osiris2.client.*;
import com.rameses.rcp.common.*;


public class AssetResourceUploader extends ResourceProcessor {
    
    String title;
    String id;
    
    def entity;
    def list = [];
    def uploadParam = [:];
    
    def count = 1;
    def max_photos = 1;
    
    public AssetResourceUploader(entity, photos, title) {
        this.entity = entity;
        this.title = title;
        this.id = entity.objid;
        
        list.addAll( photos );
        max_photos = list.size();
    }
    
    
    def _svc;
    
    private def getSvc() {
        if( _svc ) return _svc;
        _svc = InvokerProxy.instance.create('LoanAttachmentsService')
        return _svc;
    }
    
    public boolean process() {
        if( list.size() > 0 ) {            
            def photo = list[0];
            
            uploadParam.objid = photo.objid;
            def content = AssetUtil.readData( entity.objid, uploadParam.objid );
            if( content == null ) {
                list.remove(0);
                uploadParam.clear();
                count++;
            } else {
                uploadParam.content = content;
                try {
                    setMessage("Processing $count/$max_photos " + (count%2==0? '...' : '..'));
                    uploadParam.putAll( svc.saveByBlock( uploadParam ) );
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return list.size() == 0;
    }
    
}
