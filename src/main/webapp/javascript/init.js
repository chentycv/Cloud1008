semantic.init = {};

semantic.init.handler = {
};

// ready event
semantic.init.ready = function(){
    
  // Show login modal when no cookies
  if (document.cookie === ""){
      semantic.modal.handler.showLoginModal();
  } else {
    
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
      },
        error: function (errormessage) {
      }
    });
  }
};

// attach ready event
$(document)
  .ready(semantic.init.ready)
;