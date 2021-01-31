package com.idtech.cloud.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idtech.cloud.Peripheral;
import com.idtech.cloud.User;
import com.idtech.cloud.dao.PeripheralDAO;

/**
 * Servlet implementation class Peripheriques
 */
@WebServlet("/peripheriques")
public class Peripheriques extends CommonServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Peripheriques() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validate(request, response);
		
		String action = request.getParameter("action");
		
		if (action.equals("list")) {
			int id = (int) request.getSession().getAttribute("userID");
	        List<Peripheral> peripherals = PeripheralDAO.list(id);
	        request.getSession().setAttribute("peripherals", peripherals);
	        request.setAttribute("peripheralsMenuLink", " active");
	        request.getRequestDispatcher("/WEB-INF/view/peripheral/peripheral.jsp").forward(request, response);
		} else if (action.equals("new")) {
		    String command = "wmic csproduct get UUID";
		    StringBuffer output = new StringBuffer();

		    Process SerNumProcess = Runtime.getRuntime().exec(command);
		    BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
		    
		    String line = "";
		    while ((line = sNumReader.readLine()) != null) {
		        output.append(line + "\n");
		    }
		    
		    InetAddress IP=InetAddress.getLocalHost();
		    
		    String peripheralID = output.toString().substring(output.indexOf("\n"), output.length()).trim();
		    String type = "Station de travail";
		    String name = System.getProperty("user.name");
		    String ipAddress = IP.getHostAddress();
		    String operatingSystem = System.getProperty("os.name");
		    String directoryPath = System.getProperty("user.home") + "\\IdTechCloud\\";
		    int userID = (int)request.getSession().getAttribute("userID");
		    
		    Peripheral p = new Peripheral();
		    p.setPeripheralID(peripheralID);
		    p.setType(type);
		    p.setName(name);
		    p.setIpAddress(ipAddress);
		    p.setOperatingSystem(operatingSystem);
		    p.setDirectoryPath(directoryPath);
		    p.setUserID(userID);
		    PeripheralDAO.insert(p);
		    
		    response.sendRedirect( "peripheriques?action=list" );
		    
		} else if (action.equals("delete_form")) {
			Peripheral peripheral = PeripheralDAO.find(request.getParameter("id"));
        	request.setAttribute("peripheral", peripheral);
			request.getRequestDispatcher("/WEB-INF/view/peripheral/peripheral_delete.jsp").forward(request, response);
		} else if (action.equals("delete")) {
			String peripheralID = request.getParameter("peripheralID");
			Peripheral peripheral = PeripheralDAO.find(peripheralID);
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
        		Peripheral p = new Peripheral();
        		p.setPeripheralID(peripheralID);
        		p.setType(peripheral.getType());
        		p.setName(peripheral.getName());
        		p.setIpAddress(peripheral.getIpAddress());
        		p.setOperatingSystem(peripheral.getOperatingSystem());
        		p.setUserID(peripheral.getUserID());
        		PeripheralDAO.delete(p);
        		
        		response.sendRedirect( "peripheriques?action=list" );
        	} else {
        		request.setAttribute("ID", peripheralID);
        		request.getRequestDispatcher("/WEB-INF/view/peripheral/peripheral_delete.jsp").forward(request, response);
        	}
		}
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
