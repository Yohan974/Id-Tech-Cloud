<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Se connecter</title>
		<link rel='stylesheet' type='text/css' href='styles.css'/>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	</head>
	<body>
		<div class='app-layout'>
			<div class='card'>
				<div class='container'>
					<h1>Mot de passe oublié</h1>
					<form method='post' action='<%= request.getContextPath() %>/forgetpassword'>
						<span class='errorMessage'>${errorEmail}</span>
						<div class='row'>
							<div class='col'>
								<label for='login'></label>
								<input type='text' name='email' id='login' value='${email}' placeholder='E-mail' autofocus>
							</div>
						</div>
						<div>
							<br><p>Saisissez votre email et nous vous enverrons un nouveau mot de passe</p>
						</div>
						<div class='row'>
							<div class='col my-4'>
								<input class='btn btn-primary' type='submit' name='btnConnect' value='Envoyer'>
							</div>
						</div>
						<div><a class='forget-password-text' href='<%= request.getContextPath() %>/login'>Se connecter</a></div>
					</form>
				</div>
			</div>
		</div>	
	</body>
</html>