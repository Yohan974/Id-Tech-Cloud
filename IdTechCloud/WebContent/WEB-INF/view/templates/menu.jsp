<link rel='stylesheet' type='text/css' href='styles.css'/>


<nav class='app-nav'>
	<div id='nav' class='menu'>
		<ul class='nav-list'>
			<li class='nav-item'>
				<a class='nav-link' href='#'>Tableau de Bord</a>
			</li>
			<li class='nav-item'>
				<a class='nav-link${customersMenuLink}' href='<%= request.getContextPath() %>/clients?action=list'>Clients</a>
			</li>
			<li class='nav-item'>
				<a class='nav-link${usersMenuLink}' href='<%= request.getContextPath() %>/utilisateurs?action=list'>Utilisateurs</a>
			</li>
			<li class='nav-item'>
				<a class='nav-link${peripheralsMenuLink}' href='<%= request.getContextPath() %>/peripheriques?action=list'>Périphériques</a>
			</li>
			<li class='nav-item dropdown'>
  				<a class="nav-link droplink${settingsMenuLink}" href="#">Paramètres</a>
  				<div class="dropdown-content">
    				<a class='nav-link' href="<%= request.getContextPath() %>/utilisateurs?action=editPassword">Modifier mot de passe</a>
  				</div>
			</li>
		</ul>
	</div>
</nav>
<script src='main.js'></script>