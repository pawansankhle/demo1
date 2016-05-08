function User() {
	 return {
	'addressLine1': '',
    'addressLine2': '',
    'city': '',
    'deliveryAddress': '',
    'email': '',
    'enabled': false,
    'firstName': '',
    'id': '',
    'image': {},
    'lastName': '',
    'mobile': '',
    'postalCode': '',
    'roles': [],
    'username': ''
   };
  	
};


app.service('SessionSrv', function () {
  
  this.clearUser = function()
  {
	  this.user = null;
  },
  this.saveUser = function(user)
  {
   
	  this.user = user;
 },
 this.getUser = function(){
      return this.user;
 }
  
  });
