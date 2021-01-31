<div class='app-layout'>
	<main>
		<div class='card addForm'>
		<a href="<%= request.getContextPath() %>/utilisateurs?action=list" class="close"></a>
			<h1>Nouvel utilisateur</h1>
			<div class='container'>
				<form method='post' action='<%= request.getContextPath() %>/utilisateurs?action=insert'>
					<hr>
					<h2>Informations générales</h2>
					<div class='row'>
						<div class='col-6'>
							<label for='login'></label>
							<input  type='text' name='login' id='login' value='${login}' placeholder='connexion'>
							<span class='errorMessage'>${errorLogin}</span>
						</div>
						<div class='col-6'>
							<label for='email'></label>
							<input type='text' name='email' id='email' value='${email}' placeholder='email'>
							<span class='errorMessage'>${errorEmail}</span>
						</div>
						<div class='col-6'>
							<label for='firstname'></label>
							<input type='text' name='firstname' id='firstname' value='${firstname}' placeholder='prénom'>
							<span class='errorMessage'>${errorFirstname}</span>
						</div>
						<div class='col-6'>
							<label for='lastname'></label>
							<input type='text' name='lastname' id='lastname' value='${lastname}' placeholder='nom'>
							<span class='errorMessage'>${errorLastname}</span>
						</div>
					</div>
					<div>
					</div>
					<h2>Rôle</h2>
						<div class="form-check d-flex">
  							<input class="form-check-input" type="radio" name="role" id="admin" value="administrateur">
  							<label class="form-check-label mx-3" for="admin" >Administrateur</label>
						</div>
						<div class="form-check d-flex">
  							<input class="form-check-input" type="radio" name="role" id="user" value="utilisateur" checked>
  							<label class="form-check-label mx-3" for="user">Utilisateur</label>
						</div>
						<span class='errorMessage'>${errorRole}</span>
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