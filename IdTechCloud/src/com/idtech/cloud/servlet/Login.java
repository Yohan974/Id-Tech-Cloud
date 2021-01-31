package com.idtech.cloud.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.idtech.cloud.User;
import com.idtech.cloud.dao.DAOContext;
import com.idtech.cloud.dao.UserDAO;

import it.cosenonjaviste.crypto.BCrypt;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = "/login", loadOnStartup = 1)
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	public void init() throws ServletException {
		DAOContext.init(this.getServletContext());
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ( request.getSession().getAttribute( "connectedUser" ) != null ) {
			request.getRequestDispatcher("/WEB-INF/view/customer/customer.jsp").forward(request, response);
        }
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (login == null) login = "";
		if (password == null) password = "";
		
		HttpSession session = request.getSession(true);
		session.setAttribute("login", login);
		session.setAttribute("password", password);
		
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		request.setAttribute("login", login);
		request.setAttribute("password", password);
		
		User connectedUser = UserDAO.isValidLogin(login);
		if (connectedUser != null && BCrypt.checkpw(password , connectedUser.getPassword())) {
			int customerID = connectedUser.getCustomerID();
			int userID = connectedUser.getUserID();
			String role = connectedUser.getRole();
			HttpSession session = request.getSession(true);
			session.setAttribute("connectedUser", connectedUser);
			session.setAttribute("customerID", customerID);
			session.setAttribute("userID", userID);
			session.setAttribute("role", role);
			if (role.equals("administrateurIDTech")) {
				response.sendRedirect( "clients?action=list" );
			} else if (role.equals("administrateur")) {
				response.sendRedirect( "utilisateurs?action=list" );
			} else if (role.equals("utilisateur")) {
				response.sendRedirect( "peripheriques?action=list" );
			}
		} else {
			request.setAttribute("errorLogin", "Identifiant ou mot de passe incorrect");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
	}

}
