<div class='app-layout'>
	<main>
		<div class='card addForm'>
		<a href="<%= request.getContextPath() %>/utilisateurs?action=list" class="close"></a>
			<h1>Supprimer le client ${customer.name}</h1>
			<div class='container'>
				<form method='post' action='<%= request.getContextPath() %>/clients?action=delete'>
					<hr>
					<h2>Cette opération est irréversible !</h2>
					<p>Tout les utilisateurs au sein de ce client seront supprimés</p>
					<p>Toutes les données associées au client et à ses utilisateurs (par exemple : périphériques, sauvegardes, fichiers synchronisés)  seront supprimées.</p>
					<h2>Saisissez votre identifiant pour confirmation</h2>
					<div class='col'>
							<input  type='hidden' name='customerID' readonly='readonly' value='${customer.customerID}${ID}'>
					</div>
					<div class='row'>
						<div class='col-6'>
							<label for='login'></label>
							<input  type='text' name='login' id='login' value='' placeholder='connexion'>
							<span class='errorMessage'>${errorLogin}</span>
						</div>
					</div>
					<div class='row justify-content-end'>
						<div class='col-3 my-4'>
							<input class='btn btn-primary' type='submit' name='btnConnect' value='Supprimer'>
						</div>
					</div>
				</form>
			</div>
		</div>
	</main>
</div>	