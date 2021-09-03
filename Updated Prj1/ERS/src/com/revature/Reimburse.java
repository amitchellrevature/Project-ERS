import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Reimburse extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		
		res.setContentType("text/html");
		try(PrintWriter write = res.getWriter()){
			
			String action = req.getParameter("action");
			int id = Integer.parseInt(req.getParameter("id"));
			
			TicketDAO ticketDAO = new TicketDAO();
			
			if(action.equalsIgnoreCase("approved")) {
				
				ticketDAO.approveTicket(id);
				
			} else if(action.equalsIgnoreCase("rejected")) {
				
				ticketDAO.rejectTicket(id);
			}
			
			res.sendRedirect("Tickets");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
