
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class History extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("tickets.html").include(request, response);
		
		TicketDAO ticketDAO = new TicketDAO();
		
		String filter = request.getParameter("filter");
		
		try {
			out.println(ticketDAO.historyAll(filter));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.close();
    }
}
