semantic.toolbar = {};

// ready event
semantic.toolbar.ready = function(){
    
    // selector cache
	var

        $toolbar          = $('.tiny.ui.buttons'),
        $newFolder        = $toolbar.find("#new-folder"),
        $selectAll        = $toolbar.find("#select-all"),
        $copy             = $toolbar.find("#copy"),
        $move             = $toolbar.find("#move"),
        $share            = $toolbar.find("#share"),
        $delete           = $toolbar.find("#delete"),

        // alias
        handler
	;
	
    // event handlers
    semantic.toolbar.handler = {
        
    }
    ;
    
    // Initlizate the toolbar 
    $newFolder.on("click", function(){
        var file = {name: "新建文件夹", size: 0, path: null, type: "folder", thumbnail: "", created: true};  
        var files = $myDropzone.data("files");
        files.push(file);
        myDropzone.renderPreviews(files);
    })
    ;
    
    $selectAll.on("click", function(){
        var dzDetailsList = $(".dz-details .ui.checkbox")
        for(var i = 0; i < dzDetailsList.length; i++){
            var dzDetails = $( dzDetailsList[i] );
            if (dzDetails.checkbox && dzDetails.checkbox("uncheck")){
                dzDetails.click();
            }
        }
    })
    ;
    
    $delete.on("click", function(){
        
        // Get checked list from checkbox
        var checked = semantic.checkbox.handler.getChecked();
        
        // Batch delete the dzDetails
        for (var key in checked){
            var dzPreview = checked[key];
            var file = dzPreview.data("file");
            
            // Remove dzDetails in checklist
            semantic.checkbox.handler.remove(dzPreview);

            // Remove elements in page
            dzPreview.remove();
            
            // Remove elements of $myDropzone
            $myDropzone.data("files").splice(file.index, 1);
            
            // Delete the file of server
            $.ajax({
                  url  : './rest/files/' + file.id + '.json',
                  type : 'delete',
                  data : {},
                  success: function (file) {
                  },
                  error: function (errormessage) {
                  }
            });
        }
    })
    ;
}
;

// attach ready event
$(document)
    .ready(semantic.toolbar.ready)
;