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
      
    $.ajax({
      url  : './rest/users/' + cookies["wallet.cookie.userid"] +'.json',
      type : 'get',
      data : {},
        success: function (user) {
          semantic.dropdown.handler.updateUsername(user.loginName);
          semantic.init.handler.user = user;
            
          // Update the breadcrumb 
          semantic.breadcrumb.handler.render([{id: 0, name: "主目录"}], "private");
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