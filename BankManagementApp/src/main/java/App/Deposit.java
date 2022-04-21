package App;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic_With_persistence.LogicLayer;
import newexception.MistakeOccuredException;
import util.HelperUtil;

/**
 * Servlet implementation class Deposit
 */
@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		if(session.getAttribute("lastId")==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);
		}
		try 
	    {
		HelperUtil.stringCheck(request.getParameter("cars"));
	    HelperUtil.stringCheck(request.getParameter("accNo"));
	    HelperUtil.stringCheck(request.getParameter("amount"));
	    String idArr=request.getParameter("cars");
	    int id=Integer.parseInt(idArr);
	    
	    long accNo=Long.parseLong(request.getParameter("accNo"));
	   
	    double amount=Double.parseDouble(request.getParameter("amount"));
	    
	    	if(request.getParameter("moneyexchange").equals("deposit"))
	    	{
	    		obj.deposit(id, accNo, amount);
	    		response.getWriter().write("Deposited");
//	    		request.setAttribute("text","Deposited Sucessfully");
//	    		RequestDispatcher req=request.getRequestDispatcher("AccountServlet");
//		 	    req.forward(request, response);
	    	}
	    	else if(request.getParameter("moneyexchange").equals("withdraw"))
	    	{
	    	    obj.withDraw(id, accNo, amount);
	    	    response.getWriter().write("Withdrawed");
//	    	    request.setAttribute("text","Withdrawed Sucessfully");
//	    	    RequestDispatcher req=request.getRequestDispatcher("AccountServlet");
//		 	    req.forward(request, response);
	    	}
		}
	    catch (MistakeOccuredException e) 
	    {
	    	request.setAttribute("text",e.getMessage());
	    	if(request.getParameter("moneyexchange").equals("deposit"))
	    	{
		    	RequestDispatcher req=request.getRequestDispatcher("CustomerCount?moneyexchange=deposit");
		 	    req.forward(request, response);
	    	}
	    	else if(request.getParameter("moneyexchange").equals("withdraw"))
	    	{
	    	    RequestDispatcher req=request.getRequestDispatcher("CustomerCount?moneyexchange=withdraw");
		 	    req.forward(request, response);
	    	}
	    	System.out.println(e.getMessage());
	    	
		}
	}

}
