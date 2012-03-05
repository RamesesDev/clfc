package loan.ui.asset;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
import com.rameses.common.*;
import loan.ui.asset.util.AssetUtil;

public abstract class AbstractAssetFormController {
    
    def asset=[comments:[],photos:[]];
    
    def appid;
    
    def itemPhoto=[];
    def view='entry';
    def editmode='read';
    def selectHandler;
    
    def photo;
    
    void init() {}
    
    void create() {
        asset=[comments:[],photos:[]];
        photo=null;
    }
    
    def getPhotonum() {
        return asset.photos.size();
    }
    
    def getAttachmentInfo() {
        return '';
    }
    
    void view(){
        view="edit";
    }
    
    def addPhotoOpener;
    
    def addPhoto(){
        if( !addPhotoOpener ) {
            addPhotoOpener = InvokerUtil.lookupOpener('loan:asset_photo_lookup',[
                handler:{ photo ->
                    this.photo = photo;
                    photo.file = AssetUtil.transferImage( photo.file, appid, photo.objid );
                    asset.photos << photo;
                    binding.refresh('photo');
                }
            ]);
        }
        
        return addPhotoOpener;
    }
    
   void deletePhoto(){
        asset.photos.remove( photo );
        if( photo.file ) {
            photo.file.delete();
        }
        else {
            AssetUtil.removeImage( appid, photo.objid );
        }
    }
    
    def addClose(){
        selectHandler(asset);
        return "_close";
    }
    
    void addContinue() {
        selectHandler(asset);
        create();
        binding.focus("asset.subject");
        binding.refresh("asset.*");
    }
    
    def cancel(){
        return "_close";
    }
    
    def viewPhoto(){
        view='photo';
        editmode='read';
        return "page2";
    }
    
    def back(){
        if(view=='photo')
            view='entry';
        else
            view='edit';
        
        return "default";
    } 
}