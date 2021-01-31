<div class='app-layout'>
	<main>
		<div class='card addForm'>
			<a href="<%= request.getContextPath() %>/clients?action=list" class="close"></a>
			<h1>Modifier client</h1>
			<div class='container'>
				<form method='post' action='<%= request.getContextPath() %>/clients?action=update'>
					<hr>
					<h2>Informations générales</h2>
					<div class='col'>
							<input  type='hidden' name='customerID' id='customerID' readonly='readonly' value='${customer.customerID}${ID}'>
					</div>
					<div class='row'>
						<div class='col'>
							<label for='name'></label>
							<input type='text' name='name' id='name' value='${customer.name}${name}' placeholder='nom client' autofocus>
							<span class='errorMessage'>${errorName}</span>
						</div>
					</div>
					<h2>Informations de contact</h2>
					<div class='row'>
						<div class='col-6'>
							<label for='login'></label>
							<input  type='text' name='lastname' id='lastname' value='${customer.contactLastname}${contactLastname}' placeholder='nom'>
							<span class='errorMessage'>${errorLastname}</span>
						</div>
						<div class='col-6'>
							<label for='email'></label>
							<input type='text' name='firstname' id='firstname' value='${customer.contactFirstname}${contactFirstname}' placeholder='prénom'>
							<span class='errorMessage'>${errorFirstname}</span>
						</div>
						<div class='col-6'>
							<label for='firstname'></label>
							<input type='text' name='city' id='city' value='${customer.contactCity}${contactCity}' placeholder='ville'>
							<span class='errorMessage'>${errorCity}</span>
						</div>
						<div class='col-6'>
							<label for='lastname'></label>
							<input type='text' name='address' id='address' value='${customer.contactAddress}${contactAddress}' placeholder='adresse'>
							<span class='errorMessage'>${errorAddress}</span>
						</div>
						<div class='col-6'>
							<label for='firstname'></label>
							<input type='text' name='phone' id='phone' value='${customer.contactPhone}${contactPhone}' placeholder='téléphone'>
							<span class='errorMessage'>${errorPhone}</span>
						</div>
						<div class='col-6'>
							<label for='lastname'></label>
							<input type='text' name='email' id='email' value='${customer.contactEmail}${contactEmail}' placeholder='email'>
							<span class='errorMessage'>${errorEmail}</span>
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