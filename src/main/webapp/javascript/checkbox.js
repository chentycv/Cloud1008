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
        }
    }; 

    // Initlizate all checkboxs
    $checkboxs
        .checkbox()
    ;
};

// attach ready event
$(document)
    .ready(semantic.checkbox.ready)
;