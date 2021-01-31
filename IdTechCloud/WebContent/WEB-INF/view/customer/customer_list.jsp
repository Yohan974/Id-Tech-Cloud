<%@ page import="java.util.List" %>
<%@ page import="com.idtech.cloud.Customer" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='app-layout'>
	<main>
		<div class='d-flex flex-row-reverse'>
			<div><a class='btn btn-primary' href='<%= request.getContextPath() %>/clients?action=new'>+ nouveau</a></div>
		</div>
		<table>
			<thead>
				<tr>
					<th>Nom</th>
					<th>Etat</th>
					<th>Taille totale du stockage dans le Cloud</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customers}" var="customer">
					<tr>
						<td>${customer.name}</td>
						<td><i class="fas fa-check-circle"></i> Actif</td>
						<td></td>
						<td><a href="<%= request.getContextPath() %>/clients?action=edit&id=${customer.customerID}">modifier</a></td>
						<td><a href="<%= request.getContextPath() %>/clients?action=delete_form&id=${customer.customerID}">supprimer</a></td>
					</tr>
		 		</c:forEach>
			</tbody>
		</table>
	</main>
</div>
