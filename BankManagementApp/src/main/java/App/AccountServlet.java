package App;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import Details.AccountDetails;
import logic_With_persistence.LogicLayer;
import newexception.MistakeOccuredException;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		LogicLayer obj=new LogicLayer(false);
		HttpSession session=request.getSession();
		if(session.getAttribute("lastId")==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);
		}
		try
		{
			Map<Integer,Map<Long,AccountDetails>> accountMap=obj.getAllAccount();
			System.out.println(accountMap);
			response.getWriter().write(new ObjectMapper().writeValueAsString(accountMap));
//			response.setContentType("application/json");
//			response.getWriter().write(new Gson().toJson(accountMap));
//			request.setAttribute("AccountMap", accountMap);
//			RequestDispatcher disp=request.getRequestDispatcher("Account.jsp");
//			disp.forward(request, response);
		}
		catch(MistakeOccuredException ex)
		{
			ex.getMessage();
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
//		HttpSession session=request.getSession();
//		if(session.getAttribute("lastId")==null)
//		{
//			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
//            rd.forward(request, response);
//		}
//		try
//		{
//			Map<Integer,Map<Long,AccountDetails>> accountMap=obj.getAllAccount();
////			response.setContentType("application/json");
//			response.getWriter().write(new ObjectMapper().writeValueAsString(accountMap));
////			request.setAttribute("AccountMap", accountMap);
////			RequestDispatcher disp=request.getRequestDispatcher("Account.jsp");
////			disp.forward(request, response);
//		}
//		catch(MistakeOccuredException ex)
//		{
//			ex.getMessage();
//		}
//		
//		doGet(request, response);
//	}

}
