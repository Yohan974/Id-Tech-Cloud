package com.idtech.cloud.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idtech.cloud.User;
import com.idtech.cloud.dao.UserDAO;
import com.idtech.cloud.util.EmailUtility;
import com.idtech.cloud.util.FormController;
import com.idtech.cloud.util.PasswordUtility;

import it.cosenonjaviste.crypto.BCrypt;

/**
 * Servlet implementation class Utilisateurs
 */
@WebServlet("/utilisateurs")
public class Utilisateurs extends CommonServlet {
	private static final long serialVersionUID = 1L;
	
	private String host;
    private String port;
    private String user;
    private String pass;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Utilisateurs() {
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
		validate(request, response);
        
        String action = request.getParameter("action");
        
        if (action.equals("list") && !request.getSession().getAttribute("role").equals("utilisateur")) {
        	int id = (int) request.getSession().getAttribute("customerID");
            List<User> users = UserDAO.list(id);
            request.getSession().setAttribute("users", users);
            request.setAttribute("usersMenuLink", " active");
            request.getRequestDispatcher("/WEB-INF/view/user/user.jsp").forward(request, response);
        } else if (action.equals("new") && !request.getSession().getAttribute("role").equals("utilisateur")) {
        	request.getRequestDispatcher("/WEB-INF/view/user/user_new.jsp").forward(request, response);
        } else if (action.equals("edit") && !request.getSession().getAttribute("role").equals("utilisateur")) {
        	User user = UserDAO.find(Integer.parseInt(request.getParameter("id")));
        	request.setAttribute("user", user);
        	request.getRequestDispatcher("/WEB-INF/view/user/user_edit.jsp").forward(request, response);
        } else if (action.equals("delete_form") && !request.getSession().getAttribute("role").equals("utilisateur")) {
        	User user = UserDAO.find(Integer.parseInt(request.getParameter("id")));
        	request.setAttribute("user", user);
        	request.getRequestDispatcher("/WEB-INF/view/user/user_delete.jsp").forward(request, response);
        } else if (action.equals("editPassword")) {
        	request.setAttribute("settingsMenuLink", " active");
        	request.getRequestDispatcher("/WEB-INF/view/user/user_edit_password.jsp").forward(request, response);
        } else if (action.equals("updatePassword")) {
        	User user = (User)request.getSession().getAttribute("connectedUser");
        	String password = request.getParameter("password");
        	String newPassword = request.getParameter("newPassword");
        	String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");
        	
        	int errors = 0;
        	
        	String errorPassword = "";
        	if (!BCrypt.checkpw(password , user.getPassword())) {
            	errorPassword = "mot de passe incorrect";
        		request.setAttribute("errorPassword", errorPassword);
        		errors++;
        	} 
        	String errorNewPassword = "";
        	if (!FormController.passwordCheck(newPassword)) {
        		if (newPassword.equals("")) {
            		errorNewPassword = "Veuillez renseigner un nouveau mot de passe";
            	} else {
            		errorNewPassword = "Le mot de passe doit contenir au moins 8 caractères, combinant des lettres majuscules et minuscules et au moins un chiffre et un caractère spécial (!, @, # ou $)";
            	}
        		request.setAttribute("errorNewPassword", errorNewPassword);
        		errors++;
        	} 
        	String errorNewPasswordConfirmation = "";
        	if (!newPasswordConfirmation.equals(newPassword)) {
        		errorNewPasswordConfirmation = "Les mots de passe doivent correspondre";
        		request.setAttribute("errorNewPasswordConfirmation", errorNewPasswordConfirmation);
        		errors++;
        	}
        	
        	if (errors == 0 && newPasswordConfirmation.equals(newPassword)) {
        		newPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        		
        		User u = new User();
        		u.setUserID(user.getUserID());
        		u.setLogin(user.getLogin());
        		u.setPassword(newPassword);
        		u.setEmail(user.getEmail());
        		u.setFirstname(user.getFirstname());
        		u.setLastname(user.getLastname());
        		u.setRole(user.getRole());
        		u.setCustomerID(user.getCustomerID());
        		UserDAO.update(u);
        		
        		request.setAttribute("infoPassword", "Le mot de passe à bien été modifié");
        		request.getRequestDispatcher("/WEB-INF/view/user/user_edit_password.jsp").forward(request, response);
        	} else {
        		request.setAttribute("password", password);
        		request.setAttribute("newPassword", newPassword);
        		request.setAttribute("newPasswordConfirmation", newPasswordConfirmation);
        		
        		request.getRequestDispatcher("/WEB-INF/view/user/user_edit_password.jsp").forward(request, response);
        	}
        } else if (action.equals("delete") && !request.getSession().getAttribute("role").equals("utilisateur")) {
        	int userID = Integer.parseInt(request.getParameter("userID"));
        	User user = UserDAO.find(userID);
        	String login = request.getParameter("login");
        	User connectedUser = (User)request.getSession().getAttribute("connectedUser");
        	
        	int errors = 0;
        	
        	String errorLogin = "";
        	if (!login.equals(connectedUser.getLogin())) {
            	errorLogin = "L'identifiant est incorrect";
        		request.setAttribute("errorLogin", errorLogin);
        		errors++;
        	}
        	
        	if (errors == 0) {
        		User u = new User();
        		u.setUserID(userID);
        		u.setLogin(user.getLogin());
        		u.setPassword(user.getPassword());
        		u.setEmail(user.getEmail());
        		u.setFirstname(user.getFirstname());
        		u.setLastname(user.getLastname());
        		u.setRole(user.getRole());
        		u.setCustomerID(user.getCustomerID());
        		UserDAO.delete(u);
        		
        		if (u.getUserID() == connectedUser.getUserID()) {
        			response.sendRedirect( "logout" );
        		} else {
        			response.sendRedirect( "utilisateurs?action=list" );
        		}
        		
        	} else {
        		request.setAttribute("ID", userID);
        		request.getRequestDispatcher("/WEB-INF/view/user/user_delete.jsp").forward(request, response);
        	}
        } else if (action.equals("update") && !request.getSession().getAttribute("role").equals("utilisateur")) {
        	
        	int userID = Integer.parseInt(request.getParameter("userID"));
        	String login = request.getParameter("login");
        	String email = request.getParameter("email");
        	String firstname = request.getParameter("firstname");
        	String lastname = request.getParameter("lastname");
        	
        	int errors = 0;
        	
        	String errorLogin = "";
        	User user = UserDAO.find(userID);
        	if (!login.equals(user.getLogin())) {
            	errorLogin = "L'identifiant ne peut pas être modifié";
        		request.setAttribute("errorLogin", errorLogin);
        		errors++;
        	} 
        	
        	String errorEmail = "";
        	if (!FormController.emailCheck(email)) {
        		if (email.equals("")) {
            		errorEmail = "L'email doit être renseigné";
            	} else {
            		errorEmail = "Cette adresse e-mail est incorrecte";
            	}
        		request.setAttribute("errorEmail", errorEmail);
        		errors++;
        	} 
        	
    		String errorFirstname = "";
        	if (!FormController.firstnameCheck(firstname)) {
        		if (firstname.equals("")) {
            		errorFirstname = "Le prénom doit être renseigné";
            	} else {
            		errorFirstname = "Format non valide";
            	}
        		request.setAttribute("errorFirstname", errorFirstname);
        		errors++;
        	} 
        	
        	String errorLastname = "";
        	if (!FormController.lastnameCheck(lastname)) {
        		if (lastname.equals("")) {
            		errorLastname = "Le prénom doit être renseigné";
        		} else {
        			errorLastname = "Format non valide";
        		}
        		request.setAttribute("errorLastname", errorLastname);
        		errors++;
        	}
        	
        	
        	User exitingEmail = UserDAO.isExistingEmail(email);
        	if (exitingEmail != null && !email.equals(user.getEmail())) {
    			errorEmail = "Cet email existe déjà";
    			request.setAttribute("errorEmail", errorEmail);
    			errors++;
    		}
        	
        	if (errors == 0) {
        		User u = new User();
        		u.setUserID(userID);
        		u.setLogin(login);
        		u.setPassword(user.getPassword());
        		u.setEmail(email);
        		u.setFirstname(firstname);
        		u.setLastname(lastname);
        		u.setRole(user.getRole());
        		u.setCustomerID(user.getCustomerID());
        		UserDAO.update(u);
        		
        		response.sendRedirect( "utilisateurs?action=list" );
        	} else {
        		request.setAttribute("ID", userID);
        		request.setAttribute("login", login);
        		request.setAttribute("email", email);
        		request.setAttribute("firstname", firstname);
        		request.setAttribute("lastname", lastname);
        		
        		request.getRequestDispatcher("/WEB-INF/view/user/user_edit.jsp").forward(request, response);
        	}
        	
        } else if (action.equals("insert") && !request.getSession().getAttribute("role").equals("utilisateur")) {
        	
        	String login = request.getParameter("login");
        	String email = request.getParameter("email");
        	String firstname = request.getParameter("firstname");
        	String lastname = request.getParameter("lastname");
        	String role = request.getParameter("role");
        	
        	int errors = 0;
        	
        	String errorLogin = "";
        	if (!FormController.loginCheck(login)) {
        		if (login.equals("")) {
            		errorLogin = "L'identifiant doit être renseigné";
            	} else {
            		errorLogin = "L'identifiant ne peut contenir de charactères spéciaux";
            	}
        		request.setAttribute("errorLogin", errorLogin);
        		errors++;
        	} 
        	
        	String errorEmail = "";
        	if (!FormController.emailCheck(email)) {
        		if (email.equals("")) {
            		errorEmail = "L'email doit être renseigné";
            	} else {
            		errorEmail = "Cette adresse e-mail est incorrecte";
            	}
        		request.setAttribute("errorEmail", errorEmail);
        		errors++;
        	} 
        	
    		String errorFirstname = "";
        	if (!FormController.firstnameCheck(firstname)) {
        		if (firstname.equals("")) {
            		errorFirstname = "Le prénom doit être renseigné";
            	} else {
            		errorFirstname = "Format non valide";
            	}
        		request.setAttribute("errorFirstname", errorFirstname);
        		errors++;
        	} 
        	
        	String errorLastname = "";
        	if (!FormController.lastnameCheck(lastname)) {
        		if (lastname.equals("")) {
            		errorLastname = "Le prénom doit être renseigné";
        		} else {
        			errorLastname = "Format non valide";
        		}
        		request.setAttribute("errorLastname", errorLastname);
        		errors++;
        	}
        	
        	String errorRole = "";
        	
        	if (role == null) {
        		errorRole = "Vous devez sélectionner un role";
        		request.setAttribute("errorRole", errorRole);
        		errors++;
    		} else if (!FormController.roleCheck(role)) {
        		errorRole = "Une erreur a été détectée";
        		request.setAttribute("errorRole", errorRole);
        		errors++;
        	}
        	
        	User exitingLogin = UserDAO.isExistingLogin(login);
        	if (exitingLogin != null) {
    			errorLogin = "Cet identifiant existe déjà";
    			request.setAttribute("errorLogin", errorLogin);
    			errors++;
    		}
        	User exitingEmail = UserDAO.isExistingEmail(email);
        	if (exitingEmail != null) {
    			errorEmail = "Cet email existe déjà";
    			request.setAttribute("errorEmail", errorEmail);
    			errors++;
    		}
        	
        	if (errors == 0) {
        		
        		String passwordGenerated = PasswordUtility.generatePassword(8);
        		String tmpPassword = passwordGenerated;
        		passwordGenerated = BCrypt.hashpw(passwordGenerated, BCrypt.gensalt());
            	
            	
        		int customerID = (int) request.getSession().getAttribute("customerID");
                
            	User u = new User();
            	u.setFirstname(firstname);
            	u.setLastname(lastname);
            	u.setEmail(email);
            	u.setLogin(login);
            	u.setPassword(passwordGenerated);
            	u.setRole(role);
            	u.setCustomerID(customerID);
            	UserDAO.insert(u);
            	
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
                
                response.sendRedirect( "utilisateurs?action=list" );
        	} else {
        		request.setAttribute("login", login);
        		request.setAttribute("email", email);
        		request.setAttribute("firstname", firstname);
        		request.setAttribute("lastname", lastname);
        		
        		request.getRequestDispatcher("/WEB-INF/view/user/user_new.jsp").forward(request, response);
        	}
        } else {
        	if (request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        		response.sendRedirect( "clients?action=list" );
        	} else if (request.getSession().getAttribute("role").equals("administrateur")) {
        		response.sendRedirect( "utilisateurs?action=list" );
        	} else if (request.getSession().getAttribute("role").equals("utilisateur")) {
        		response.sendRedirect( "peripheriques?action=list" );
        	} 	
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
