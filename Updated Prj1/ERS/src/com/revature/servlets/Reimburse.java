package servlets;

import requests.*;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.log4j.Logger;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Reimburse extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 377404989771189769L;
	
	private static final Logger logger = ConnectionFactory.configLogger(Logout.class);

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		logger.info("POST REQUEST MADE TO REIMBURSE TICKET");
		res.setContentType("text/html");
		try(PrintWriter write = res.getWriter()){
						
			String[] details = req.getParameter("details").split(",");
			String action = req.getParameter("action");

			TicketsImpl ticketDAO = new TicketsImpl();
			
			Tickets ticket = new Tickets(Integer.parseInt(details[0]), Integer.parseInt(details[1]), details[2], Double.parseDouble(details[3]), details[4], new Date(), details[6]);
			ticket.setStat(action);
			ticketDAO.changeTicket(ticket);
			
			res.sendRedirect("ProcessTickets");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}