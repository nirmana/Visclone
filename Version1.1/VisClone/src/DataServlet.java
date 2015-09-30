

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import cyclone.exception.cycloneConfException;
import cyclone.initialize.Project;

/**
 * Servlet implementation class DataServlet
 */

public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			Project project=new Project();
			project.getCloneClassList().getCloneClassAtIndex(1).getccId();
		    response.setContentType("text/html");
			// Actual logic goes here.
		      PrintWriter out = response.getWriter();
		      out.println("<h1>" + project.getCloneClassList().getCloneClassAtIndex(1).getccId() + "</h1>");
		      
			request.getSession().setAttribute("Project", project);
			response.sendRedirect("index.jsp");
		} catch (TransformerException | ParserConfigurationException | SAXException | cycloneConfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
