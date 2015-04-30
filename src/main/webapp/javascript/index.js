// namespace
window.semantic = {
  handler: {}
};

// Allow for console.log to not break IE
if (typeof window.console == "undefined" || typeof window.console.log == "undefined") {
  window.console = {
    log  : function() {},
    info : function(){},
    warn : function(){}
  };
}
if(typeof window.console.group == 'undefined' || typeof window.console.groupEnd == 'undefined' || typeof window.console.groupCollapsed == 'undefined') {
  window.console.group = function(){};
  window.console.groupEnd = function(){};
  window.console.groupCollapsed = function(){};
}
if(typeof window.console.markTimeline == 'undefined') {
  window.console.markTimeline = function(){};
}
window.console.clear = function(){};

// ready event
semantic.ready = function() {

  // selector cache
  var

    $sticky              = $('.ui.sticky'),

    $ui                  = $('.ui').not('.hover, .down'),
    $menu                = $('#toc'),
    $menuFriends         = $('#toc-friends'),
    $menuFriendDetails   = $('#toc-friend-details'),
    $menuGroups          = $('#toc-groups'),
    $menuGroupMembers    = $('#toc-group-members'),
    $hideMenu            = $('#toc .hide.item'),
    $sidebarButton       = $('.fixed.launch.button'),
      
    $loginModal          = $('.login.modal'),
    $registerModal       = $('.register.modal'),

    requestAnimationFrame = window.requestAnimationFrame
      || window.mozRequestAnimationFrame
      || window.webkitRequestAnimationFrame
      || window.msRequestAnimationFrame
      || function(callback) { setTimeout(callback, 0); },

    // alias
    handler
  ;
    
  // event handlers
  handler = {
  
  showModal: function(modal) {
      modal.modal('show');
    },
  
  };

  semantic.handler = handler;

  window.less.registerStylesheets();

    
  window.hljs.configure({
    languages: [
      'xml',
      'css',
      'javascript'
    ]
  });

    
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
    
  // Login modal
  $loginModal
    .modal({
      closable: false
    })
  ;
  $(".login.small.modal #register-bottom")
    .on('click', function(event){
      handler.showModal($registerModal);
      event.preventDefault();
  })
  ;
    
  handler.showModal($loginModal);
    
  // Register modal
  $registerModal
    .modal({
      closable: false
    })
  ;
  $(".register.small.modal #login-bottom")
    .on('click', function(event){
      handler.showModal($loginModal);
      event.preventDefault();
  })
  ;
    
}

// attach ready event
$(document)
  .ready(semantic.ready)
;
