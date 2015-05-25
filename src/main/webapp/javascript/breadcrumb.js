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
                        
                        // Get the section files and index
                        var
                            section = event.data,
                            files = $breadcrumb.data("files"),
                            index = section.data("index");
                        
                        // Cut off the files array
                        files.splice(index + 1, files.length - index - 1);
                        
                        // Update the uploader
                        var file = files[index];
                        

                        // Active loader
                        semantic.loader.handler.active();
                        
                        // Get all files of current folder
                        $.ajax({
                          url  : './rest/files.json?parentid=' + file.id,
                          type : 'get',
                          data : {},
                          success: function (files) {

                            // Update the dropzone
                            myDropzone.renderPreviews(files);

                            // Update the breadcrumb
                            semantic.breadcrumb.handler.render();
                              
                              
                            // Deactive loader
                            semantic.loader.handler.deactivate();
                          },
                          error: function (errormessage) {
                          }
                        })
                        ;
                        
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
}
;

// attach ready event
$(document)
    .ready(semantic.breadcrumb.ready)
;