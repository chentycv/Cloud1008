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
    
    $delete.on("click", function(){
        
    })
    ;
}
;

// attach ready event
$(document)
    .ready(semantic.toolbar.ready)
;