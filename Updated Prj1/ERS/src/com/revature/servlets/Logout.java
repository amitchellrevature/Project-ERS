package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import requests.ConnectionFactory;

public class Logout extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2269853261244707653L;
	
	private static final Logger logger = ConnectionFactory.configLogger(Logout.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		logger.info("GET REQUEST MADE TO LOGOUT");
		response.setContentType("text/html");
		try(PrintWriter out = response.getWriter()){
			
			HttpSession session = request.getSession(false);
			session.setAttribute("username", "");
			session.setAttribute("password", "");
			
			request.getRequestDispatcher("index.html").include(request, response);
			out.println("<h5 class='text-center text-warning'>User logged out</h5>");
			out.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}