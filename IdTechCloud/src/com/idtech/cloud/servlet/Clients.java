package com.idtech.cloud.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idtech.cloud.Customer;
import com.idtech.cloud.User;
import com.idtech.cloud.dao.CustomerDAO;
import com.idtech.cloud.dao.UserDAO;
import com.idtech.cloud.util.EmailUtility;
import com.idtech.cloud.util.FormController;
import com.idtech.cloud.util.PasswordUtility;

import it.cosenonjaviste.crypto.BCrypt;

/**
 * Servlet implementation class Clients
 */
@WebServlet("/clients")
public class Clients extends CommonServlet {
	private static final long serialVersionUID = 1L;
	
	private String host;
    private String port;
    private String user;
    private String pass;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Clients() {
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
        
        if (action.equals("list") && request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        	List<Customer> customers = CustomerDAO.list();
        	request.getSession().setAttribute("customers", customers);
        	request.setAttribute("customersMenuLink", " active");
    		request.getRequestDispatcher("/WEB-INF/view/customer/customer.jsp").forward(request, response);
        } else if (action.equals("new") && request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        	request.getRequestDispatcher("/WEB-INF/view/customer/customer_new.jsp").forward(request, response);
        } else if (action.equals("edit") && request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        	Customer customer = CustomerDAO.find(Integer.parseInt(request.getParameter("id")));
        	request.setAttribute("customer", customer);
        	request.getRequestDispatcher("/WEB-INF/view/customer/customer_edit.jsp").forward(request, response);
        } else if (action.equals("delete_form") && request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        	Customer customer = CustomerDAO.find(Integer.parseInt(request.getParameter("id")));
        	request.setAttribute("customer", customer);
        	request.getRequestDispatcher("/WEB-INF/view/customer/customer_delete.jsp").forward(request, response);
        } else if (action.equals("delete") && request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        	int customerID = Integer.parseInt(request.getParameter("customerID"));
        	Customer customer = CustomerDAO.find(customerID);
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
        		Customer c = new Customer();
        		c.setCustomerID(customerID);
        		c.setName(customer.getName());
        		c.setContactFirstname(customer.getContactFirstname());
        		c.setContactLastname(customer.getContactLastname());
        		c.setContactCity(customer.getContactCity());
        		c.setContactAddress(customer.getContactAddress());
        		c.setContactPhone(customer.getContactPhone());
        		c.setContactEmail(customer.getContactEmail());
        		CustomerDAO.delete(c);
        		
        		response.sendRedirect( "clients?action=list" );
        	} else {
        		request.setAttribute("ID", customerID);
        		request.getRequestDispatcher("/WEB-INF/view/customer/customer_delete.jsp").forward(request, response);
        	}
        } else if (action.equals("update") && request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        	
        	int customerID = Integer.parseInt(request.getParameter("customerID"));
        	String name = request.getParameter("name");
        	String contactFirstname = request.getParameter("firstname");
        	String contactLastname = request.getParameter("lastname");
        	String contactCity = request.getParameter("city");
        	String contactAddress = request.getParameter("address");
        	String contactPhone = request.getParameter("phone");
        	String contactEmail = request.getParameter("email");
        	
        	
        	int errors = 0;
        	
        	String errorName = "";
        	if (!FormController.nameCheck(name)) {
        		if (request.getParameter("name").equals("")) {
            		errorName = "Le nom doit être renseigné";		
            	} else {
            		errorName = "Format non valide";
            	}
        		request.setAttribute("errorName", errorName);
        		errors++;
        	}
        	
        	String errorLastname = "";
        	if (!FormController.lastnameCheck(contactLastname) && !contactLastname.equals("")) {
        		errorLastname = "Format non valide";
        		request.setAttribute("errorLastname", errorLastname);
        		errors++;
        	}
        	
        	String errorFirstname = "";
        	if (!FormController.firstnameCheck(contactFirstname) && !contactFirstname.equals("")) {
        		errorFirstname = "Format non valide";
        		request.setAttribute("errorFirstname", errorFirstname);
        		errors++;
        	}
        	
        	String errorCity = "";
        	if (!FormController.cityCheck(contactCity) && !contactCity.equals("")) {
        		errorCity = "Format non valide";
        		request.setAttribute("errorCity", errorCity);
        		errors++;
        	}
        	
        	String errorAddress = "";
        	if (!FormController.addressCheck(contactAddress) && !contactAddress.equals("")) {
        		errorAddress = "Format non valide";
        		request.setAttribute("errorAddress", errorAddress);
        		errors++;
        	}
        	
        	String errorPhone = "";
        	if (!FormController.phoneCheck(contactPhone) && !contactPhone.equals("")) {
        		errorPhone = "Format non valide";
        		request.setAttribute("errorPhone", errorPhone);
        		errors++;
        	}
        	
        	String errorEmail = "";
        	if (!FormController.emailCheck(contactEmail) && !contactEmail.equals("")) {
        		errorEmail = "Cette adresse e-mail est incorrecte";
        		request.setAttribute("errorEmail", errorEmail);
        		errors++;
        	}
	
        	Customer exitingName = CustomerDAO.isExistingName(name);
        	Customer customer = CustomerDAO.find(customerID);
        	if (exitingName != null && !name.equals(customer.getName())) {
    			errorName = "Ce nom est déjà utilisé";
    			request.setAttribute("errorName", errorName);
    			errors++;
    		}
        	
        	if (errors == 0) {
        		Customer c = new Customer();
        		c.setCustomerID(customerID);
            	c.setName(name);
            	c.setContactFirstname(contactFirstname);
            	c.setContactLastname(contactLastname);
            	c.setContactCity(contactCity);
            	c.setContactAddress(contactAddress);
            	c.setContactPhone(contactPhone);
            	c.setContactEmail(contactEmail);
            	CustomerDAO.update(c);
            	
            	response.sendRedirect( "clients?action=list" );
        	} else {
        		request.setAttribute("ID", customerID);
        		request.setAttribute("name", name);
        		request.setAttribute("contactFirstname", contactFirstname);
        		request.setAttribute("contactLastname", contactLastname);
        		request.setAttribute("contactCity", contactCity);
        		request.setAttribute("contactAddress", contactAddress);
        		request.setAttribute("contactPhone", contactPhone);
        		request.setAttribute("contactEmail", contactEmail);
        		
        		request.getRequestDispatcher("/WEB-INF/view/customer/customer_edit.jsp").forward(request, response);
        	}
        } else if (action.equals("insert") && request.getSession().getAttribute("role").equals("administrateurIDTech")) {
        	
        	String name = request.getParameter("name");
        	String login = request.getParameter("login");
        	String email = request.getParameter("email");
        	String firstname = request.getParameter("firstname");
        	String lastname = request.getParameter("lastname");
        	
        	int errors = 0;
        	
        	String errorName = "";
        	if (!FormController.nameCheck(name)) {
        		if (request.getParameter("name").equals("")) {
            		errorName = "Le nom doit être renseigné";		
            	} else {
            		errorName = "Format non valide";
            	}
        		request.setAttribute("errorName", errorName);
        		errors++;
        	} 
        	
        	String errorLogin = "";
        	if (!FormController.loginCheck(login)) {
        		if (request.getParameter("login").equals("")) {
            		errorLogin = "L'identifiant doit être renseigné";
            	} else {
            		errorLogin = "L'identifiant ne peut contenir de charactères spéciaux";
            	}
        		request.setAttribute("errorLogin", errorLogin);
        		errors++;
        	} 
        	
        	String errorEmail = "";
        	
        	if (!FormController.emailCheck(email)) {
        		if (request.getParameter("email").equals("")) {
            		errorEmail = "L'email doit être renseigné";
            	} else {
            		errorEmail = "Cette adresse e-mail est incorrecte";
            	}
        		request.setAttribute("errorEmail", errorEmail);
        		errors++;
        	} 
        	
    		String errorFirstname = "";
        	if (!FormController.firstnameCheck(firstname)) {
        		if (request.getParameter("firstname").equals("")) {
            		errorFirstname = "Le prénom doit être renseigné";
            	} else {
            		errorFirstname = "Format non valide";
            	}
        		request.setAttribute("errorFirstname", errorFirstname);
        		errors++;
        	} 
        	
        	String errorLastname = "";
        	if (!FormController.lastnameCheck(lastname)) {
        		if (request.getParameter("lastname").equals("")) {
            		errorLastname = "Le prénom doit être renseigné";
        		} else {
        			errorLastname = "Format non valide";
        		}
        		request.setAttribute("errorLastname", errorLastname);
        		errors++;
        	}
        	
        	Customer exitingName = CustomerDAO.isExistingName(name);
        	if (exitingName != null) {
    			errorName = "Ce nom est déjà utilisé";
    			request.setAttribute("errorName", errorName);
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
        		Customer c = new Customer();
            	c.setName(request.getParameter("name"));
            	int lastID = CustomerDAO.insert(c);
            	
            	String passwordGenerated = PasswordUtility.generatePassword(8);
            	String tmpPassword = passwordGenerated;
        		passwordGenerated = BCrypt.hashpw(passwordGenerated, BCrypt.gensalt());
                
            	User u = new User();
            	u.setFirstname(request.getParameter("firstname"));
            	u.setLastname(request.getParameter("lastname"));
            	u.setEmail(request.getParameter("email"));
            	u.setLogin(request.getParameter("login"));
            	u.setPassword(passwordGenerated);
            	u.setRole("administrateur");
            	u.setCustomerID(lastID);
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
            	
                response.sendRedirect( "clients?action=list" );
        	} else {
        		request.setAttribute("name", name);
        		request.setAttribute("login", login);
        		request.setAttribute("email", email);
        		request.setAttribute("firstname", firstname);
        		request.setAttribute("lastname", lastname);
        		
        		request.getRequestDispatcher("/WEB-INF/view/customer/customer_new.jsp").forward(request, response);
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
