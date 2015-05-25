semantic.loader = {};

// ready event
semantic.loader.ready = function(){
    
    // selector cache
	var

        $loader          = $('.ui.inverted.dimmer'),

        // alias
        handler
	;
	
    // event handlers
    semantic.loader.handler = {
        
        active : function(){
            $loader.addClass("active");
        },
        
        deactivate : function(){
            $loader.removeClass("active");
        }
    }
    ;
    
    
}
;

// attach ready event
$(document)
    .ready(semantic.loader.ready)
;