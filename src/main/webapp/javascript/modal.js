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
	semantic.modal.handler = {

	  showModal: function(modal) {
	    modal.modal('show');
	  },

	  showLoginModal: function() {
	    $loginModal.modal('show');
	  },

	  showRegisterModal: function() {
	    $loginModal.modal('show');
	  },

	  hideAllModal: function() {
	    $loginModal.modal('hide');
	    $registerModal.modal('hide');
	  }
	};
	  
	// Login modal
	$loginModal
	  .modal({
	    closable: false
	  })
	;

	$loginModal.find('.ui.form')
  	  .form({
  	    username: {
	      identifier : 'loginName',
	      rules: [
	        {
	          type   : 'empty',
	          prompt : '请输入用户名'
	        },
	       	{
	          type   : 'length[3]',
	          prompt : '用户名必须大于三位'
	        }
	      ]
	    },
	    password: {
	      identifier : 'password',
	      rules: [
	        {
	          type   : 'empty',
	          prompt : '请输入密码'
	        },
	        {
	          type   : 'length[6]',
	          prompt : '密码必须大于六位'
	        }
	      ]
	    }
  	  },{
  	  	inline : true,
    	on : 'blur'
  	  })
	;

	$loginModal.find("#register-bottom")
	  .on('click', function(event){

	  	// Get the submit form in login form
	    $form = $loginModal.find( ".form" );

	  	// Clear the input form 
        $form.form('clear');

        // Show the register form
	    semantic.modal.handler.showModal($registerModal);
	    event.preventDefault();
	})
	;
	  
	$loginModal.find(".submit.button")
	  .on('click', function(event){
	    
	    // Get the submit form in login form
	    $form = $loginModal.find( ".form" );

	    // Get some values from elements on the page:
	    var loginName = $form.find( "input[name='loginName']" ).val(),
	        password = $form.find( "input[name='password']" ).val();

		$.ajax({
		  url  : './login',
		  type : 'get',
		  data : {loginName: loginName, password: password},
		  success: function (msg) {

		  	// Close all error message 
			$loginModal.find(".error.message").hide();

			// Updat the loginName
          	semantic.dropdown.handler.updateUsername(loginName);

          	// Hide all modal
          	semantic.modal.handler.hideAllModal();

          	// Clear the input form 
          	$form.form('clear');
          },
          error: function (errormessage) {
			$loginModal.find(".error.message").show();
          }
		});

	    event.preventDefault();
	})
	;
	  
	// Register modal
	$registerModal
	  .modal({
	    closable: false
	  })
	;

	$registerModal.find('.ui.form')
  	  .form({
  	    username: {
	      identifier : 'loginName',
	      rules: [
	        {
	          type   : 'empty',
	          prompt : '请输入用户名'
	        },
	       	{
	          type   : 'length[3]',
	          prompt : '用户名必须大于三位'
	        }
	      ]
	    },
	    password: {
	      identifier : 'password',
	      rules: [
	        {
	          type   : 'empty',
	          prompt : '请输入密码'
	        },
	        {
	          type   : 'length[6]',
	          prompt : '密码必须大于六位'
	        }
	      ]
	    },
	    confirmPassword: {
	      identifier : 'confirmPassword',
	      rules: [
	        {
	          type   : 'empty',
	          prompt : '请输入密码'
	        },
	        {
	          type   : 'length[6]',
	          prompt : '密码必须大于六位'
	        },
	        {
	          type   : 'match[password]',
	          prompt : '两次密码必须相同'
	        }
	      ]
	    }
  	  },{
  	  	inline : true,
    	on : 'blur'
  	  })
	;
	$registerModal.find("#login-bottom")
	  .on('click', function(event){

	  	// Get the submit form in login form
	    $form = $registerModal.find( ".form" );

	  	// Clear the input form 
        $form.form('clear');

        // Show the login modal form
	    semantic.modal.handler.showModal($loginModal);
	    event.preventDefault();
	})
	;
	$registerModal.find(".submit.button")
	  .on('click', function(event){
	    
	    // Get the submit form in login form
	    $form = $registerModal.find( ".form" );

	    // Get some values from elements on the page:
	    var loginName = $form.find( "input[name='loginName']" ).val(),
	        password = $form.find( "input[name='password']" ).val(),
	        confirmPassword = $form.find( "input[name='confirmPassword']" ).val();

        if (loginName.length >= 3 && password === confirmPassword && password.length >=6 )
		$.ajax({
		  url  : './register',
		  type : 'get',
		  data : {loginName: loginName, password: password},
		  success: function (msg) {
			
		  	// Hide all the modal
			$registerModal.find(".error.message").hide();
          	
			// Show login form 
          	semantic.modal.handler.showModal($loginModal);

          	// Clear the input form 
          	$form.form('clear');
          },
          error: function (errormessage) {
			$registerModal.find(".error.message").show();
          }
		});

	    event.preventDefault();
	})
	;
}

// attach ready event
$(document)
	.ready(semantic.modal.ready)
;
