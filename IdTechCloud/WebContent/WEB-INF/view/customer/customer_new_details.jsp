<div class='app-layout'>
	<main>
		<div class='card addForm'>
			<a href="<%= request.getContextPath() %>/clients?action=list" class="close"></a>
			<h1>Nouveau client</h1>
			<div class='container'>
				<form method='post' action='<%= request.getContextPath() %>/clients?action=insert'>
				<hr>
					<h2>Informations générales</h2>
					<div class='row'>
						<div class='col'>
							<label for='name'></label>
							<input type='text' name='name' id='name' value='${name}' placeholder='nom client' autofocus>
							<span class='errorMessage'>${errorName}</span>
						</div>
					</div>
					<h2>Administrateur</h2>
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