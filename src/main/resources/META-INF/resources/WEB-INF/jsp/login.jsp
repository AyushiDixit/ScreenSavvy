<html>
	<head>
		<title>Login page</title>
		<style>
			body {
				display: flex;
				justify-content: center; 
				align-items: center;
				text-align:center;
				flex-direction: column;
				height: 100vh;
				
			}
		</style>
	</head>

	<body>
		<h4 class="welcome-text">Welcome to the login page!</h4>
		<form method="post">
		  <div class="form-group">
		  	<label for="username">Username</label>
		  	<input type="username" class="form-control" placeholder="Username" name="name">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<span>${errorMessage}</span>
	</body>
</html>