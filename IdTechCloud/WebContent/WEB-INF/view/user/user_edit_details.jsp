<div class='app-layout'>
	<main>
		<div class='card addForm'>
		<a href="<%= request.getContextPath() %>/utilisateurs?action=list" class="close"></a>
			<h1>Modifier utilisateur</h1>
			<div class='container'>
				<form method='post' action='<%= request.getContextPath() %>/utilisateurs?action=update'>
					<hr>
					<h2>Informations générales</h2>
					<div class='col'>
							<input  type='hidden' name='userID' id='userID' readonly='readonly' value='${user.userID}${ID}'>
					</div>
					<div class='row'>
						<div class='col-6'>
							<label for='login'></label>
							<input  type='text' name='login' id='login' readonly='readonly' value='${user.login}${login}' placeholder='connexion'>
							<span class='errorMessage'>${errorLogin}</span>
						</div>
						<div class='col-6'>
							<label for='email'></label>
							<input type='text' name='email' id='email' value='${user.email}${email}' placeholder='email'>
							<span class='errorMessage'>${errorEmail}</span>
						</div>
						<div class='col-6'>
							<label for='firstname'></label>
							<input type='text' name='firstname' id='firstname' value='${user.firstname}${firstname}' placeholder='prénom'>
							<span class='errorMessage'>${errorFirstname}</span>
						</div>
						<div class='col-6'>
							<label for='lastname'></label>
							<input type='text' name='lastname' id='lastname' value='${user.lastname}${lastname}' placeholder='nom'>
							<span class='errorMessage'>${errorLastname}</span>
						</div>
					</div>
					<div>
					</div>
					<div class='row justify-content-end'>
						<div class='col-3 my-4'>
							<input class='btn btn-primary' type='submit' name='btnConnect' value='Enregistrer'>
						</div>
					</div>
				</form>
			</div>
		</div>
	</main>
</div>	