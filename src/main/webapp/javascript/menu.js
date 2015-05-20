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
    
    hideAllMenus: function(){
      $menu.sidebar('hide');
      $menuFriends.sidebar('hide');
      $menuFriendDetails.sidebar('hide');
      $menuGroups.sidebar('hide');
      $menuGroupMembers.sidebar('hide');
    },
    
    renderMenuFriends: function(users){
        
      // Update the users model
      if (users) { 
        $menuFriends.data("friends", users);
      } else {
        users = $menuFriends.data();
      }
     
      // Sort the users array
      users.sort(function(a, b){
        return (!a.new);
      });
        
      // Clear the model
      $menuFriends.find(".item.launch-toc-new-friend-details").remove();
      $menuFriends.find(".item.launch-toc-friend-details").remove();
        
      for (var i in users){
         var friendElement = ( users[i].new ? $newFriendTemplete : $friendTemplete).clone();
         friendElement.find("b").html(users[i].loginName);
          
         // Show the details of a friend
         friendElement.on('click', users[i], function(event) {
             
            // Close the friends modal
            $menuFriends.sidebar('hide');
            
            // Update the data of the friend detais
            $menuFriendDetails.data("user", event.data);          
             
            // Adapt to the new friend entity
            if (event.data.new || event.data.searched ){
                $menuFriendDetails.find("#view-file").addClass("disabled");
                $menuFriendDetails.find("#delete-friend").html("加为好友");
            } else {
                $menuFriendDetails.find("#view-file").removeClass("disabled");
                $menuFriendDetails.find("#delete-friend").html("删除好友");
            }
             
            // Set the content of the elements
            $menuFriendDetails.find("[data='loginName']").html(event.data.loginName);
            $menuFriendDetails.find("[data='telephone']").html(event.data.mobile === "" ? "暂无" : event.data.mobile );
            $menuFriendDetails.find("[data='email']").html(event.data.email === "" ? "暂无" : event.data.email);
             
            // Show the siderbar
            $menuFriendDetails.sidebar('toggle');
            event.preventDefault();
         });
         friendElement.appendTo($menuFriends);
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
    
  $friendTemplete = $menuFriends.find(".item.launch-toc-friend-details").first();
  $newFriendTemplete = $menuFriends.find(".item.launch-toc-new-friend-details").first(),

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
              var usersDegrees = {};
              
              // Update the degrees of users graph
              for(var i in friends){
                if ( semantic.init.handler.user.id === friends[i].userAId ){
                    if (usersDegrees[friends[i].userBId] !== undefined ) {
                      usersDegrees[friends[i].userBId] ++;
                    } else {
                      usersDegrees[friends[i].userBId] = 0;
                    }
                } else {
                    if (usersDegrees[friends[i].userAId] !== undefined ) {
                      if (usersDegrees[friends[i].userAId] === 0) {
                        usersDegrees[friends[i].userAId] = 2;
                      } else {
                        usersDegrees[friends[i].userAId] ++;
                      }
                    } else {
                      usersDegrees[friends[i].userAId] = 1;
                    }
                }
              }
              
              // Request the user entities
              for(var userId in usersDegrees){
                  $.ajax({
                    url : './rest/users/' + userId + '.json',
                    type : 'get',
                    data : {},
                    success: function (user) {
                        
                        // Append new property and user entity
                        switch  (usersDegrees[user.id]){
                            case 1: user.new = true; users.push(user); break;
                            case 2: user.new = false; users.push(user); break;
                        }
                        
                        // Render the friend details menu when all user got
                        if ( ++renderTrigger == Object.keys(usersDegrees).length){
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

  // The search input in friend menu
  var $menuFriendsSearch = $menuFriends.find(".ui.icon.input");
    
  // The search icon onclick callback 
  $menuFriendsSearch.find(".search").on("click", function(){
      
    // Find the search button
    var searchBottom = $menuFriendsSearch.find(".search");
      
    // Perserve the friends
    if ($menuFriendsSearch.data("friends") === undefined)  {
      $menuFriendsSearch.data( "friends", $menuFriends.data("friends") );
    }
    
    if (searchBottom.hasClass("remove")) {
        
      // Updat the search input icon
      searchBottom.removeClass("remove");
      $menuFriendsSearch.find("input").val("");
      semantic.menu.handler.renderMenuFriends($menuFriendsSearch.data("friends"));       
        
    } else {
        
      // Change the icon of the input
      searchBottom.addClass("remove");
    
      // Get the value of search input 
      var searchTerm = $menuFriendsSearch.find("input").val();
      
      // Request the users by searchTerm
      $.ajax({
        url : './rest/users.json?searchTerm=' + searchTerm,
        type : 'get',
        data : {},
        success: function (users) {
            
          // Initlizate the hide list
          var hideUsers = $menuFriends.data();
          var hideIdList = {};
          for (var i in hideUsers){
            hideIdList[hideUsers[i].id] = true;
          }
          
          // Filter by the hide list
          for (var i in users){
            if (hideIdList[users[i].id]) {
              users.splice(i, 1);
            } else {
              users[i].searched = true;
            }
          }

          semantic.menu.handler.renderMenuFriends(users);                      
        }
      }); 
    }
  });
    
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
      
      // Show the friends modal
      $('#toc .launch-toc-friends').click();
      event.preventDefault();
    })
  ;
    
  // The view file callback
  $menuFriendDetails.find("#view-file").on("click", function(event){
    console.log($menuFriendDetails.data("user"))
  })
  ;
    
  // The delete friend or add friend callback
  $menuFriendDetails.find("#delete-friend").on("click", function(event){
    var user = $menuFriendDetails.data("user");
    
    if ( $menuFriendDetails.find("#delete-friend").html() == "加为好友" ){
        $.ajax({
          url  : './rest/friends.json',
          type : 'post',
          data : JSON.stringify({userAId: semantic.init.handler.user.id, userBId: user.id }),
          contentType: "application/json; charset=utf-8",
          dataType: "json",
          success: function (friend) {
              $('#toc-friend-details .item.title.back').click();
          },
          error: function (errormessage) {
              $('#toc-friend-details .item.title.back').click();
          }
        });
    } else {
        $.ajax({
          url  : './rest/friends/' + semantic.init.handler.user.id + '.json',
          type : 'delete',
          data: JSON.stringify({ userAId: semantic.init.handler.user.id, userBId: user.id }),
          contentType: "application/json; charset=utf-8",
          dataType: "json",
          success: function (friend) {
              $('#toc-friend-details .item.title.back').click();
          },
          error: function (errormessage) {
              $('#toc-friend-details .item.title.back').click();
          }
        });
    }
    
    // Update the search icon
    $menuFriendsSearch.find(".search").removeClass("remove");
    $menuFriendsSearch.find("input").val("");
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
