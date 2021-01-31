<%@ page import="java.util.List" %>
<%@ page import="com.idtech.cloud.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='app-layout'>
	<main>
		<div class='d-flex flex-row-reverse'>
			<div><a class='btn btn-primary' href='<%= request.getContextPath() %>/utilisateurs?action=new'>+ nouveau</a></div>
		</div>
		
		<table>
			<thead>
				<tr>
					<th>Nom</th>
					<th>Etat</th>
					<th>Connexion</th>
					<th>Rôle</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.firstname} ${user.lastname}</td>
						<td><i class="fas fa-check-circle"></i> Actif</td>
						<td>${user.login}</td>
						<td>${user.role}</td>
						<td><a href="<%= request.getContextPath() %>/utilisateurs?action=edit&id=${user.userID}">modifier</a></td>
						<td><a href="<%= request.getContextPath() %>/utilisateurs?action=delete_form&id=${user.userID}">supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</main>
</div>