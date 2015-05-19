semantic.menu = {};

// ready event
semantic.menu.ready = function() {

  // selector cache
  var
  
    $menu                = $('#toc'),
      
    $menuFriends         = $('#toc-friends'),     
    $menuFriendDetails   = $('#toc-friend-details'),
    $menuGroups          = $('#toc-groups'),
    $menuGroupMembers    = $('#toc-group-members'),

    requestAnimationFrame = window.requestAnimationFrame
      || window.mozRequestAnimationFrame
      || window.webkitRequestAnimationFrame
      || window.msRequestAnimationFrame
      || function(callback) { setTimeout(callback, 0); },

    // alias
    handler
  ;
    
  // event handlers
  semantic.menu.handler = {
    renderMenuFriends: function(users){
        
      // Update the users model
      if (users) { this.renderMenuFriends.users = users; };
     
      // Clear the model
      $launchTocFriendDetails.html("");
        
      for (var i in users){
         var friendElement = $friendTemplete.clone();
         friendElement.find("b").html(users[i].loginName);
          
         // Show the details of a friend
         friendElement.on('click', users[i], function(event) {
            
            // Set the content of the elements
            $menuFriendDetails.find("[data='loginName']").html(event.data.loginName);
            $menuFriendDetails.find("[data='telephone']").html(event.data.mobile);
            $menuFriendDetails.find("[data='email']").html(event.data.email);
             
            // Show the siderbar
            $menuFriendDetails.sidebar('toggle');
            event.preventDefault();
         });
         friendElement.appendTo($launchTocFriendDetails);
      } 
    }
  };
    
  // Main menu sidebar
  $menu
    .sidebar({
      closable         : true,
      transition       : 'overlay',
      mobileTransition : 'overlay'
    })
  ;
  $('.launch.button, .view-ui, .launch.item, #toc .item.title.back, .show-file')
    .on('click', function(event) {
      $menu.sidebar('toggle');
      event.preventDefault();
    })
  ;

  // Friends sidebar
  $menuFriends
    .sidebar({
      closable         : true,
      transition       : 'overlay',
      mobileTransition : 'overlay'
    })
  ;
    
  $friendTemplete = $menuFriends.find(".launch-toc-friend-details .item").first(),
  $launchTocFriendDetails = $menuFriends.find(".launch-toc-friend-details"),
 
  $('#toc .launch-toc-friends,  #toc-friends .item.title.back')
    .on('click', function(event) {
      $menuFriends.sidebar('toggle');
      
      // Sent http request when friend sidebar is actived
      if( $menuFriends.sidebar('is hidden') ){
          $.ajax({
		  url  : './rest/users/' + semantic.init.handler.user.id + '/friends.json',
		  type : 'get',
		  data : {},
		  success: function (friends) {
              var users = [];
              var renderTrigger = 0;
              for(var i = 0; i < friends.length; i++){
                  $.ajax({
                    url : './rest/users/' + friends[i].userBId + '.json',
                    type : 'get',
                    data : {},
                    success: function (user) {
                        users.push(user);
                        
                        // Render the friend details menu when all user got
                        if ( ++renderTrigger == friends.length){
                            semantic.menu.handler.renderMenuFriends(users);
                        }
                    }
                  }); 
              }
              

          },
          error: function (errormessage) {
          }
		});
      }
      event.preventDefault();
    })
  ;    
    
  // Friend details sidebar
  $menuFriendDetails
    .sidebar({
      closable         : true,
      transition       : 'overlay',
      mobileTransition : 'overlay'
    })
  ;
  $('#toc-friend-details .item.title.back')
    .on('click', function(event) {
      $menuFriendDetails.sidebar('toggle');
      event.preventDefault();
    })
  ;
    
    
  // Groups sidebar
  $menuGroups
    .sidebar({
      closable         : true,
      transition       : 'overlay',
      mobileTransition : 'overlay'
    })
  ;
  $('#toc .launch-toc-groups,  #toc-groups .item.title.back')
    .on('click', function(event) {
      $menuGroups.sidebar('toggle');
      event.preventDefault();
    })
  ;

  // Group members sidebar
  $menuGroupMembers
    .sidebar({
      closable         : true,
      transition       : 'overlay',
      mobileTransition : 'overlay'
    })
  ;
  $('#toc-groups .launch-toc-group-members,  #toc-group-members .item.title.back')
    .on('click', function(event) {
      $menuGroupMembers.sidebar('toggle');
      event.preventDefault();
    })
  ;
}

// attach ready event
$(document)
  .ready(semantic.menu.ready)
;
