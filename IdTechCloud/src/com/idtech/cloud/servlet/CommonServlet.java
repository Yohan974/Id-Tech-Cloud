package com.idtech.cloud.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommonServlet
 */
@WebServlet
public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ( request.getSession().getAttribute( "connectedUser" ) == null ) {
			request.setAttribute("errorLogin", "Vous n'êtes pas connecté");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
	}

}
