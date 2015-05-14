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
//    $sidebarButton       = $('.fixed.launch.button'),

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
  
  showModal: function(modal) {
      modal.modal('show');
    },
  
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
  $('#toc .launch-toc-friends,  #toc-friends .item.title.back')
    .on('click', function(event) {
      $menuFriends.sidebar('toggle');
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
  $('#toc-friends .launch-toc-friend-details,  #toc-friend-details .item.title.back')
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
