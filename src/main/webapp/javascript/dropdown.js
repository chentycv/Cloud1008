semantic.dropdown = {};

// ready event
semantic.dropdown.ready = function() {

  // selector cache
  var
    $accountDropdown     = $('.right.menu #account'),
    $updateInformation   = $accountDropdown.find('#update-information'),
    $updatePassowrd      = $accountDropdown.find('#update-password'),
    $logout              = $accountDropdown.find('#logout'),
      
      
    // alias
    handler
  ;
    
  // event handlers
  semantic.dropdown.handler = {
    updateUsername : function(loginName) {
      $accountDropdown.find("#username").html(loginName);
    }
  
  };
    
  $accountDropdown
    .popup({
      position : 'bottom center',
      delay    : {
        show: 500,
        hide: 50
      }
    })
    .dropdown({
      action     : 'hide',
      allowTab   : false,
      on         : 'click',
      onShow     : function() {
        $(this).popup('hide');
      }
    })
  ;
    
  $updateInformation.on('click', function(event) {
          
      // Show the profile
      semantic.modal.handler.showProfileModal();
      event.preventDefault();
    })
  ;

  $logout.on('click', function(event) {
      $.ajax({
        url  : './logout',
        type : 'get',
        data : {},
        success: function (msg) {

          // Clear the loginName
          semantic.dropdown.handler.updateUsername("");

          // Show the login modal
          semantic.modal.handler.showLoginModal();

          // Clear the cookies
          document.cookie = "";
            
          // Hide all the modal
          semantic.menu.handler.hideAllMenus();
          
        },
        error: function (errormessage) {
        }
      });
      event.preventDefault();
    })
  ;

}


// attach ready event
$(document)
  .ready(semantic.dropdown.ready)
;
