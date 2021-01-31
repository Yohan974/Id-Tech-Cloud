package com.idtech.cloud.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.idtech.cloud.User;
import com.idtech.cloud.dao.UserDAO;
import com.idtech.cloud.util.EmailUtility;
import com.idtech.cloud.util.PasswordUtility;

import it.cosenonjaviste.crypto.BCrypt;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/forgetpassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String host;
    private String port;
    private String user;
    private String pass;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	ServletContext context = getServletContext();
    	host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ( request.getSession().getAttribute( "connectedUser" ) != null ) {
			request.getRequestDispatcher("/WEB-INF/view/customer/customer.jsp").forward(request, response);
        }
		
		String email = request.getParameter("email");
		if (email == null) email = "";
		
		HttpSession session = request.getSession(true);
		session.setAttribute("email", email);
			
		request.getRequestDispatcher("/forgetpassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		request.setAttribute("email", email);
		
		String errorEmail = "";
    	
		User exitingEmail = UserDAO.isExistingEmail(email);
		if (exitingEmail == null) {
			errorEmail = "Veuillez renseigner une adresse e-mail valide";
		}
    	
    	if (exitingEmail != null) {
    		String passwordGenerated = PasswordUtility.generatePassword(8);
    		String tmpPassword = passwordGenerated;
    		passwordGenerated = BCrypt.hashpw(passwordGenerated, BCrypt.gensalt());
    		
    		User u = exitingEmail;
    		u.setPassword(passwordGenerated);
    		UserDAO.update(u);
    		
    		String recipient = request.getParameter("email");
            String subject = "IdTechCloud";
            String content = "Votre mot de passe est : " + tmpPassword + ". Pensez à le modifier!";
           
            try {
                EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
                System.out.println("Email sent.");
            } catch (Exception ex) {
            	System.out.println("Failed to sent email.");
                ex.printStackTrace();
            }
            String infoPassword = "Un nouveau mot de passe vous a été envoyé";
            request.setAttribute("infoPassword", infoPassword);
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
    	} else {
    		request.setAttribute("errorEmail", errorEmail);
			request.getRequestDispatcher("/forgetpassword.jsp").forward(request, response);
    	}
	}
}
