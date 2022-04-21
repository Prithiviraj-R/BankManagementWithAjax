package App;

import java.io.IOException;

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
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
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
		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		HttpSession session=request.getSession();
		if(session.getAttribute("userId")==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);
		}
		try 
		{
			HelperUtil.stringCheck(request.getParameter("AccNum"));
			HelperUtil.stringCheck(request.getParameter("AccNo"));
			HelperUtil.stringCheck(request.getParameter("Amount"));
		    long fromAccNo=Long.parseLong(request.getParameter("AccNum"));
		    long toAccNo=Long.parseLong(request.getParameter("AccNo"));
		    double amount=Double.parseDouble(request.getParameter("Amount"));
			obj.amountTransfer(fromAccNo, toAccNo, amount);
		} 
		catch (MistakeOccuredException e) 
		{
			request.setAttribute("text", e.getMessage());
			if(request.getParameter("Customer").equals("Customer"))
			{
				RequestDispatcher req=request.getRequestDispatcher("CustomerCount?fromAccNum="+request.getParameter("AccNum")+"&moneyexchange=user");
				req.forward(request, response);
			}
			else
			{
				RequestDispatcher req=request.getRequestDispatcher("CustomerCount?moneyexchange=transaction");
				req.forward(request, response);
			}
		}
		if(request.getParameter("Customer").equals("Customer"))
		{
			request.setAttribute("text", "Transaction sucessfully");
			RequestDispatcher req=request.getRequestDispatcher("UserServlet");
			req.forward(request, response);
		}
		else
		{
			request.setAttribute("text", "Transaction sucessfully");
			RequestDispatcher req=request.getRequestDispatcher("AccountServlet");
			req.forward(request, response);
		}
		doGet(request, response);
	}

}
