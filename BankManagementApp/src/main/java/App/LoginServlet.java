package App;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;

import Details.AccountDetails;
import Details.Customer;
import logic_With_persistence.LogicLayer;
import newexception.MistakeOccuredException;
import util.HelperUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
@Override
public void init(ServletConfig config) throws ServletException 
{
	// TODO Auto-generated method stub
	LogicLayer obj=new LogicLayer(false);
	config.getServletContext().setAttribute("Object", obj);
	super.init(config);
}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println(session);
		String id =request.getParameter("uname");
		String password =request.getParameter("psw");
		session.setAttribute("userId", id);
		if(session.getAttribute("userId")==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);
		}
		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		int id1=0;
		int accNo=0;
		int activeCusId=0;
		int activeAccId=0;
		try 
		{
			HelperUtil.stringCheck(id);
			HelperUtil.stringCheck(password);
			id1 = obj.lastCustomer();
			accNo=obj.getLastAccountId();
			activeCusId=obj.getactiveCustomerCount();
			activeAccId=obj.activeAccountCount();
		}
		catch (MistakeOccuredException e) {}
		session.setAttribute("lastId",id1);
		session.setAttribute("lastAcc", accNo);
		session.setAttribute("activeCus", activeCusId);
		session.setAttribute("activeAcc", activeAccId);
		if(session.getAttribute("lastId")==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);
		}
		try
		{
			boolean role=obj.getRole(request.getParameter("uname"), password);
			System.out.println(role);
			if (role==false) 
			{
				RequestDispatcher rd=request.getRequestDispatcher("UserServlet");  
	            rd.forward(request, response);  
			}
			else if(role==true)
			{
				RequestDispatcher rd=request.getRequestDispatcher("Admin.jsp");  
	            rd.forward(request, response);  
			}
			else
			{
				request.setAttribute("text","Entered username/password is wrong.");
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
	            rd.forward(request, response);
			}
		} 
		catch (MistakeOccuredException e)
		{
			e.printStackTrace();
			request.setAttribute("text",e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);
		}
	}

}
