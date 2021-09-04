import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 931175993433480040L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		out.close();
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		TicketsImpl ticketDAO = new TicketsImpl();
		
		try {
			int empID = ticketDAO.login(username, password);
			if(empID == 0) {
				
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<h5 class='text-center text-warning'>Incorrect username and password</h5>");
			} else {
//				Cookie cookie = new Cookie("empID", String.valueOf(empID));
//				response.addCookie(cookie);
				
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				session.setAttribute("empID", empID);
				
				response.sendRedirect("ProcessTickets");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.close();
    }
}