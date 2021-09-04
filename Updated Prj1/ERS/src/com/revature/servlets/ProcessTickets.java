package servlets;

import requests.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProcessTickets extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3856425543358836562L;
	
	private static final Logger logger = ConnectionFactory.configLogger(ProcessTickets.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		logger.info("GET REQUEST MADE TO PROCESS TICKETS");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String username = String.valueOf(session.getAttribute("username"));
		String password = String.valueOf(session.getAttribute("password"));
		int id = Integer.parseInt(String.valueOf(session.getAttribute("empID")));
		
		if(username.equalsIgnoreCase("") & password.equalsIgnoreCase("")) {
			
			request.getRequestDispatcher("index.html").include(request, response);
			out.println("<h5 class='text-center text-warning'>Please login first</h5>");
		} else {
			
			TicketsImpl ticketDAO = new TicketsImpl();
			
//			Cookie[] cookie = request.getCookies();
//			
//			request.getRequestDispatcher("tickets.html").include(request, response);
			
			try {
				if(ticketDAO.manage(id)) {
					request.getRequestDispatcher("history.html").include(request, response);
				}
				else {
					request.getRequestDispatcher("tickets.html").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.close();
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		logger.info("POST REQUEST MADE TO PROCESS TICKETS");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");
		double amount = Double.valueOf(request.getParameter("amount"));
		String description = request.getParameter("description");
		
//		request.getRequestDispatcher("tickets.html").include(request, response);

		TicketsImpl ticketDAO = new TicketsImpl();
		
		HttpSession session = request.getSession(false);
		int empID = Integer.parseInt(String.valueOf(session.getAttribute("empID")));
		
//		Cookie[] cookie = request.getCookies();
		
		try {
			int ticketId = ticketDAO.historyAll().size();
			ticketId++;
			ticketDAO.request(new Tickets(ticketId, empID, type, amount, description, new Date(), "Pending"));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		try {
			if(ticketDAO.manage(empID)) {
				request.getRequestDispatcher("history.html").include(request, response);
			}
			else {
				request.getRequestDispatcher("tickets.html").include(request, response);
				out.println("<h5 class='text-center text-warning'>Request Submited!</h5>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
//		try {
//			if(ticketDAO.manage(Integer.valueOf(cookie[0].getValue())))
//				request.getRequestDispatcher("history.html").include(request, response);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		out.close();
//    }
	}
}