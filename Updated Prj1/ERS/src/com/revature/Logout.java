import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
