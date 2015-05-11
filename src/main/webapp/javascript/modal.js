semantic.modal = {};

// ready event
semantic.modal.ready = function() {

  // selector cache
  var

    $loginModal          = $('.login.modal'),
    $registerModal       = $('.register.modal'),
      
    // alias
    handler
  ;
    
  // event handlers
 handler = {
  
  showModal: function(modal) {
      modal.modal('show');
    },
  
  };
    
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
  .ready(semantic.modal.ready)
;
