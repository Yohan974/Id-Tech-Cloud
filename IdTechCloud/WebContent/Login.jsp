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
					<h1>Se connecter</h1>
					<form method='post' action='<%= request.getContextPath() %>/login'>
						<span class='errorMessage'>${errorLogin}</span>
						<span class='infoMessage'>${infoPassword}</span>
						<div class='row'>
							<div class='col'>
								<label for='login'></label>
								<input type='text' name='login' id='login' value='${login}' placeholder='identifiant' autofocus>
							</div>
						</div>
						<div class='row'>
							<div class='col'>
								<label for='password'></label>
								<input type='password' name='password' id='password' value='${password}' placeholder='mot de passe'>
							</div>
						</div>
						<div class='row'>
							<div class='col my-4'>
								<input class='btn btn-primary' type='submit' name='btnConnect' value='Suivant'>
							</div>
						</div>
						<div><a class='forget-password-text' href='<%= request.getContextPath() %>/forgetpassword'>Mot de passe oublié?</a></div>
					</form>
				</div>
			</div>
		</div>	
	</body>
</html>