semantic.dropdown = {};

// ready event
semantic.dropdown.ready = function() {

  // selector cache
  var
    $accountDropdown     = $('.right.menu #account'),
    $updateInformation   = $accountDropdown.filter('#update-information'),
    $updatePassowrd      = $accountDropdown.filter('#update-password'),
    $logout              = $accountDropdown.filter('#logout'),
      
      
    // alias
    handler
  ;
    
  // event handlers
  handler = {
  
  
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
      },
      onChange : handler.translatePage
    })
  ;
    
  $updateInformation.on('click', function(event) {
      $menuGroupMembers.sidebar('toggle');
      event.preventDefault();
    })
  ;
}

// attach ready event
$(document)
  .ready(semantic.dropdown.ready)
;
