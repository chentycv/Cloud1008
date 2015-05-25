semantic.init = {};

semantic.init.handler = {
  
  // Update the user profile
  updateUser : function(){
    
    // Sent http request when modal is actived
    var cookies = document.cookie.split("; ")
    for (var i = 0; i < cookies.length; i++){
        var cookieStr = cookies[i].split("=");
        var cookie = {};
        cookies[cookieStr[0]] = cookieStr[1];
        delete cookies[i];
    }
    
    // Get current user information  
    $.ajax({
      url  : './rest/users/' + cookies["wallet.cookie.userid"] +'.json',
      type : 'get',
      data : {},
        success: function (user) {
          semantic.dropdown.handler.updateUsername(user.loginName);
          semantic.init.handler.user = user;
            
          // Initlizate the breadcrumb 
          semantic.breadcrumb.handler.render([{id: 0, name: "主目录"}], "private");
            
            
            // Active loader
            semantic.loader.handler.active();
            
            // Get all files of current user
            $.ajax({
              url  : './rest/users/' + semantic.init.handler.user.id + '/files.json',
              type : 'get',
              data : {},
              success: function (files) {
                
                // Render the previews  
                myDropzone.renderPreviews(files);
                  
                // Deactive loader
                semantic.loader.handler.deactivate();
              },
              error: function (errormessage) {
              }
            })
            ;
      },
        error: function (errormessage) {
      }
    });
  }    
};

// ready event
semantic.init.ready = function(){
    
  // Show login modal when no cookies
  if (document.cookie === ""){
    semantic.modal.handler.showLoginModal();
  } else {
    semantic.init.handler.updateUser();
  }
    
};

// attach ready event
$(document)
  .ready(semantic.init.ready)
;