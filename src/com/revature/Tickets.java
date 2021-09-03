
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Tickets extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("tickets.html").include(request, response);
		
		TicketDAO ticketDAO = new TicketDAO();
		
		Cookie[] cookie = request.getCookies();
		
		try {
			if(ticketDAO.manage(Integer.valueOf(cookie[0].getValue())))
				request.getRequestDispatcher("history.html").include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.close();
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");
		double amount = Double.valueOf(request.getParameter("amount"));
		String description = request.getParameter("description");
		
		request.getRequestDispatcher("tickets.html").include(request, response);

		TicketDAO ticketDAO = new TicketDAO();
		
		Cookie[] cookie = request.getCookies();
		
		try {
			ticketDAO.request(Integer.valueOf(cookie[0].getValue()), type, amount, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(ticketDAO.manage(Integer.valueOf(cookie[0].getValue())))
				request.getRequestDispatcher("history.html").include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
    }
}

