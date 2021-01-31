<div class='app-layout'>
	<main>
		<div class='card addForm'>
			<h1>Modifier mot de passe</h1>
			<div class='container'>
				<form method='post' action='<%= request.getContextPath() %>/utilisateurs?action=updatePassword'>
					<span class='infoMessage'>${infoPassword}</span>
					<div class='row'>
						<div class='col-12'>
							<label for='password'></label>
							<input  type='password' name='password' id='password' value='${password}' placeholder='ancien mot de passe'>
							<span class='errorMessage'>${errorPassword}</span>
						</div>
						<div class='col-12'>
							<label for='newPassword'></label>
							<input type='password' name='newPassword' id='newPassword' value='${newPassword}' placeholder='nouveau mot de passe'>
							<span class='errorMessage'>${errorNewPassword}</span>
						</div>
						<div class='col-12'>
							<label for='newPasswordConfirmation'></label>
							<input type='password' name='newPasswordConfirmation' id='newPasswordConfirmation' value='${newPasswordConfirmation}' placeholder='confirmez le nouveau mot de passe'>
							<span class='errorMessage'>${errorNewPasswordConfirmation}</span>
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