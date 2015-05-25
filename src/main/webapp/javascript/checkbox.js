semantic.checkbox = {};

// ready event
semantic.checkbox.ready = function(){
    
    // selector cache
	var

        $checkboxs          = $('.ui.checkbox'),

        // alias
        handler
	;
	
    // event handlers
    semantic.checkbox.handler = {
        
        update : function(element){
            element
                .checkbox()
            ;
        },
        
        add : function(dzPreview){
            var file = dzPreview.data("file");
            checked[file.id] = dzPreview;
        },
        
        remove : function(dzPreview){
            var file = dzPreview.data("file");
            delete checked[file.id];
        },
        
        getChecked : function(){
            return checked;
        }       
    }; 

    // Initlizate all checkboxs
    $checkboxs
        .checkbox()
    ;
    
    // Initlizate the checked list of checkboxs
    var checked = {};
};

// attach ready event
$(document)
    .ready(semantic.checkbox.ready)
;