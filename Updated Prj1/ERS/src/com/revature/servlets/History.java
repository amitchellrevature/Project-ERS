package servlets;

import requests.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//import java.text.DecimalFormat;
import java.util.List;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class History extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5882749965320313261L;

	private static final Logger logger = ConnectionFactory.configLogger(History.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		logger.info("GET REQUEST MADE TO HISTORY");
		Gson gson = new Gson();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		request.getRequestDispatcher("tickets.html").include(request, response);
		
		HttpSession session = request.getSession(false);
		String username = String.valueOf(session.getAttribute("username"));
		String password = String.valueOf(session.getAttribute("password"));
		
		if(username.equalsIgnoreCase("") & password.equalsIgnoreCase("")) {
			
			response.setContentType("text/html");
			request.getRequestDispatcher("index.html").include(request, response);
			out.println("<h5 class='text-center text-warning'>Please login first</h5>");
		} else {
		
			TicketsImpl ticketDAO = new TicketsImpl();
			
//			DecimalFormat df = new DecimalFormat("0.00");
			
//			String filter = request.getParameter("filter");
			
			try {
//				request.getRequestDispatcher("history.html").include(request, response);
				List<Tickets> allTickets = ticketDAO.historyAll();
				if(allTickets == null) {
					
					response.setContentType("text/html");
					request.getRequestDispatcher("history.html").include(request, response);
					out.println("<h5 class='text-center text-warning'>No tickets to show</h5>");
				} else {
					
					out.println(gson.toJson(allTickets));
				}
//				for(Tickets ticket : allTickets) {
//					String data = gson.toJson(ticket);
//					out.write(data);
//				}
//				if(allTickets == null) {
//					
//					out.println("<h5 class='text-center text-warning'>No tickets to show</h5>");
//				} else {
//					
//					for(Tickets ticket : allTickets) {
//						switch(filter) {
//							case "All":
//					    	   out.println("<section class='container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success'>" +
//				    	            "Ticket: " + ticket.getTicketId() + "<br>" +
//				    	            "Type: " + ticket.getExpType() + "<br>" +
//				    	            "Amount: $" + df.format(ticket.getAmount()) + "<br>" +
//				    	            "Description: " + ticket.getDesc() + "<br>" +
//				    	            "Time Submitted: " + ticket.getDate() + "<br>" +
//				    	            "Status: " + ticket.getStat() + "<br>");
//					    	    
//					    	    if(ticket.getStat().equalsIgnoreCase("pending")) {
//					    	        out.println("Action: <div class='text-center my-2'>"
//						    	        		+ "<form action='Reimburse' method='post'>"
//						    	                + "<select name='action' class='form-control'>"
//						    	                + "<option value='Approved'>Approve</option>"
//						    	                + "<option value='Rejected'>Reject</option>"
//						    	                + "</select>"
//						    	                + "<input type='hidden' name='details' value='" + ticket.getTicketId() 
//						    	                +"," + ticket.getEmpId() + "," + ticket.getExpType() + "," + ticket.getAmount() 
//						    	                + "," + ticket.getDesc() + "," + ticket.getDate() + "," + ticket.getStat() + "'>"
//						    	                + "<input type='submit' class='btn btn-success my-2' value='Continue'>"
//						    	                + "</form></div><br>");
//					    	    }
//					    	    out.println("</section>");
//								break;
//							case "Pending":
//					    		if(ticket.getStat().equalsIgnoreCase("pending")) {
//					    			out.println("<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
//						    	                "Ticket: " + ticket.getTicketId() + "<br>" +
//						    	                "Type: " + ticket.getExpType() + "<br>" +
//						    	                "Amount: $" + df.format(ticket.getAmount()) + "<br>" +
//						    	                "Description: " + ticket.getDesc() + "<br>" +
//						    	                "Time Submitted: " + ticket.getDate() + "<br>" + 
//						    	                "Status: " + ticket.getStat() + "<br>"
//						    	                 +"Action: <div class='text-center my-2'>"
//						    	                 + "<form action='Reimburse' method='post'>"
//						    		             + "<select name='action' class='form-control'>"
//						    		             + "<option value='Approved'>Approve</option>"
//						    		             + "<option value='Rejected'>Reject</option>"
//						    		             + "</select>"
//							    	             + "<input type='hidden' name='details' value='" + ticket.getTicketId() 
//							    	             +"," + ticket.getEmpId() + "," + ticket.getExpType() + "," + ticket.getAmount() 
//							    	             + "," + ticket.getDesc() + "," + ticket.getDate() + "," + ticket.getStat() + "'>"
//							    	             + "<input type='submit' class='btn btn-success my-2' value='Continue'>"
//						    		             + "</form></div><br>"
//						    		             + "</section>");
//					    	    }
//								break;
//							case "Approved":
//					    		if(ticket.getStat().equalsIgnoreCase("approved")) {
//					    			out.println("<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
//					    	                "Ticket: " + ticket.getTicketId() + "<br>" +
//					    	                "Type: " + ticket.getExpType() + "<br>" +
//					    	                "Amount: $" + df.format(ticket.getAmount()) + "<br>" +
//					    	                "Description: " + ticket.getDesc() + "<br>" +
//					    	                "Time Submitted: " + ticket.getDate() + "<br>" +
//					    	                "Status: " + ticket.getStat() + "<br>"
//					    	                + "</section>");
//					    		}
//								break;
//							case "Rejected":
//					    		if(ticket.getStat().equalsIgnoreCase("rejected")) {
//					    			out.println("<section class=\"container text-warning w-50 p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success\">" +
//					    	                "Ticket: " + ticket.getTicketId() + "<br>" +
//					    	                "Type: " + ticket.getExpType() + "<br>" +
//					    	                "Amount: $" + df.format(ticket.getAmount()) + "<br>" +
//					    	                "Description: " + ticket.getDesc() + "<br>" +
//					    	                "Time Submitted: " + ticket.getDate() + "<br>" +
//					    	                "Status: " + ticket.getStat() + "<br>"
//					    	                + "</section>");
//					    		}
//								break;
//						}
//					}
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.close();
    }

//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		request.getRequestDispatcher("tickets.html").include(request, response);
//		
//		HttpSession session = request.getSession(false);
//		String username = String.valueOf(session.getAttribute("username"));
//		String password = String.valueOf(session.getAttribute("password"));
//		
//		if(username.equalsIgnoreCase("") & password.equalsIgnoreCase("")) {
//			
//			request.getRequestDispatcher("index.html").include(request, response);
//			out.println("<h5 class='text-center text-warning'>Please login first</h5>");
//		} else {
//		
//			TicketsImpl ticketDAO = new TicketsImpl();
//			
//			String filter = request.getParameter("filter");
//			
//			try {
//				request.getRequestDispatcher("history.html").include(request, response);
//				out.println(ticketDAO.historyAll(filter));
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		out.close();
//    }
}