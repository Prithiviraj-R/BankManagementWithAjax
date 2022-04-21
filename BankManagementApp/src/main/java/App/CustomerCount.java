package App;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Details.AccountDetails;
import logic_With_persistence.LogicLayer;
import newexception.MistakeOccuredException;

/**
 * Servlet implementation class CustomerCount
 */
@WebServlet("/CustomerCount")
public class CustomerCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCount() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		List allId=null;
		List branch=null;
		Map<Integer,Map<Long,AccountDetails>> accountMap=null;
		HttpSession session=request.getSession();
		if(session.getAttribute("userId")==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);	
		}
		else
		{
		try 
		{
			allId=obj.getAllActiveCustomerId();
			accountMap=obj.getAllAccount();
			branch=obj.getBranch();
		} 
		catch (MistakeOccuredException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("AllId",allId);
		request.setAttribute("AccountMap", accountMap);
		request.setAttribute("Branch",branch);
		String name=request.getParameter("moneyexchange");
		request.setAttribute("moneyexchange", name);
		if(request.getParameter("moneyexchange").equals("deposit"))
		{	
		RequestDispatcher req=request.getRequestDispatcher("deposit.jsp");
		req.forward(request, response);
		}
		else if(request.getParameter("moneyexchange").equals("withdraw"))
		{
			RequestDispatcher req=request.getRequestDispatcher("deposit.jsp");
			req.forward(request, response);
		}
		else if(request.getParameter("moneyexchange").equals("transaction"))
		{
			System.out.println("hello");
			RequestDispatcher req=request.getRequestDispatcher("AdminTransaction.jsp");
			req.forward(request, response);
		}
		else if(request.getParameter("moneyexchange").equals("addAccount"))
		{
			RequestDispatcher req=request.getRequestDispatcher("AddAccount.jsp");
			req.forward(request, response);
		}
		else if(request.getParameter("moneyexchange").equals("user"))
		{
			RequestDispatcher req=request.getRequestDispatcher("Transaction.jsp");
			req.forward(request, response);
		}
		else if(request.getParameter("moneyexchange").equals("update"))
		{
			RequestDispatcher req=request.getRequestDispatcher("AddAccount.jsp");
			req.forward(request, response);
		}
		}
	}

}
