<%@ page import="java.util.List" %>
<%@ page import="com.idtech.cloud.Peripheral" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='app-layout'>
	<main>
		<div class='d-flex flex-row-reverse'>
			<div><a class='btn btn-primary' href='<%= request.getContextPath() %>/peripheriques?action=new'>+ ajouter</a></div>
		</div>
		<table>
			<thead>
				<tr>
					<th>Type</th>
					<th>Nom</th>
					<th>Compte</th>
					<th>Etat</th>
					<th>Dernière sauvegarde</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${peripherals}" var="peripheral">
					<tr>
						<td>${peripheral.type}</td>
		 				<td>${peripheral.name}</td>
		 				<td></td>
		 				<td><i class="fas fa-check-circle"></i> Actif</td>
		 				<td></td>
		 				<td><a href="<%= request.getContextPath() %>/peripheriques?action=delete_form&id=${peripheral.peripheralID}">supprimer</a></td>
		 				<td><a href="<%= request.getContextPath() %>/download/${peripheral.peripheralID}/Backup.zip">restaurer</a></td>
					</tr>
		 		</c:forEach>
			</tbody>
		</table>		
	</main>
</div>