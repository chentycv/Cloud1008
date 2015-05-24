semantic.breadcrumb = {};

// ready event
semantic.breadcrumb.ready = function(){
    
    // selector cache
	var

        $breadcrumb          = $('.ui.breadcrumb'),
        $icondivider         = $breadcrumb.find('.divider').first(),
        $divider             = $breadcrumb.find('.divider').last(),
        $section             = $breadcrumb.find('.section').first(),
        $activesection       = $breadcrumb.find('.active.section').first(),

        // alias
        handler
	;
	
    // event handlers
    semantic.breadcrumb.handler = {
        
        getFiles : function(){
            return $breadcrumb.data("files");        
        },
        
        getCurrentFile : function(){
            var files = $breadcrumb.data("files"); 
            return files[files.length - 1];     
        },
        
        render : function(files, type){
            
            // Update the files or get files from data
            if (files !== undefined){
                $breadcrumb.data("files", files);
            } else {
                files = $breadcrumb.data("files");
            }
            
            // Update the files or get files from data
            if (type !== undefined){
                $breadcrumb.data("type", type);
            } else {
                type = $breadcrumb.data("type");
            }


            // Clear the bread crumb
            $breadcrumb.html("");
            
            // Render the type icon
            if (type === "private" || type === undefined){
                $icondivider.clone().appendTo($breadcrumb);
                $section.find("[data='path']").html(semantic.init.handler.user.loginName);
                $section.clone().appendTo($breadcrumb);                
            }
            
            
            for( var i = 0 ; i < files.length; i++){
                
                if( i < files.length - 1){
                    
                    // Append the clickable section and dividers
                    $divider.clone().appendTo($breadcrumb);
                    var section = $section.clone();
                    section.find("[data='path']").html(files[i].name);
                    section.appendTo($breadcrumb);   
                    section.data("index", i);


                    // Bind the event to the sections
                    section.on("click", section, function(event){
                        var section = event.data;
                        var files = $breadcrumb.data("files");
                        var index = section.data("index");
                        files.splice(index + 1, files.length - index - 1);
                        $breadcrumb.data("file", section.data("file"));
                        semantic.breadcrumb.handler.render();                           
                    })
                    ;  
                    
                } else {
                    
                    // Append the current file section
                    $divider.clone().appendTo($breadcrumb);
                    var section = $activesection.clone();
                    section.find("[data='path']").html(files[i].name);
                    section.appendTo($breadcrumb);   
                    section.data("index", i);
                }
            }
        }
    }
    ;
    
    // Initlizate the breadcrumb file
    $breadcrumb.data("file", { id: null, name: "主目录" });
}
;

// attach ready event
$(document)
    .ready(semantic.breadcrumb.ready)
;