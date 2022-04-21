package App;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Details.AccountDetails;
import logic_With_persistence.LogicLayer;
import newexception.MistakeOccuredException;
import util.HelperUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @param session 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		HttpSession session=request.getSession();
		if(session.getAttribute("userId")==null)
		{
			RequestDispatcher req=request.getRequestDispatcher("login.jsp");
			req.forward(request, response);
		}
		try 
		{
			HelperUtil.stringCheck((String) request.getSession().getAttribute("userId"));
			int id=Integer.parseInt((String) request.getSession().getAttribute("userId"));
			Map<Long,AccountDetails> account=obj.getAccountdetails(id);
			request.setAttribute("accountDetails", account);
			RequestDispatcher req=request.getRequestDispatcher("User.jsp");
			req.forward(request, response);
		} catch (MistakeOccuredException e) 
		{
			request.setAttribute("text","Details cannot be fetched.");
			RequestDispatcher req=request.getRequestDispatcher("User.jsp");
			req.forward(request, response);
		}
		doGet(request, response);
	}

}
