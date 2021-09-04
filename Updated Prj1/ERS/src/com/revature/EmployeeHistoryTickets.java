import java.io.IOException;

import java.io.PrintWriter;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

//import com.google.gson.Gson;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EmployeeHistoryTickets extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3997757796385622659L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		Gson gson = new Gson();

		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();

		HttpSession session = req.getSession(false);
		String username = String.valueOf(session.getAttribute("username"));
		String password = String.valueOf(session.getAttribute("password"));
		int empID = Integer.parseInt(String.valueOf(session.getAttribute("empID")));
		
		if(username.equalsIgnoreCase("") & password.equalsIgnoreCase("")) {
			
			req.getRequestDispatcher("index.html").include(req, res);
			out.println("<h5 class='text-center text-warning'>Please login first</h5>");
		} else {
					
			try {
				TicketsImpl dao = new TicketsImpl();
				DecimalFormat df = new DecimalFormat();
				
				List<Tickets> allTickets = dao.historyEmployee(empID);
//				out.println(gson.toJson(tickets));
				req.getRequestDispatcher("tickets.html").include(req, res);
				
				if(allTickets.size() == 0) {
					
					out.println("<h5 class='text-center text-warning'>No tickets to show</h5>");
				} else {
					
					for(Tickets ticket : allTickets) {
						out.println("<h5 class='text-center text-warning'>Employee Id: " + empID + "</h5>" +
							  "<section class='text-warning w-50 container p-5 bg-dark bg-gradient opacity-75 shadow-lg rounded-3 border border-2 border-success'><div>" +
		                      "Ticket: " + ticket.getTicketId() + "<br>" +
		                      "Type: " + ticket.getExpType() + "<br>" +
		                      "Amount: $" + df.format(ticket.getAmount()) + "<br>" +
		                      "Description: " + ticket.getDesc() + "<br>" +
		                      "Time Submitted: " + ticket.getDate() + "<br>" +
		                      "Status: " + ticket.getStat() + "<br>" +
		                      "</div></section>");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}