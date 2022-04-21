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
 * Servlet implementation class CustomerStatus
 */
@WebServlet("/CustomerStatus")
public class CustomerStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerStatus() {
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
//		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		LogicLayer obj=new LogicLayer(false);
		HttpSession session=request.getSession();
		if(session.getAttribute("lastId")==null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
            rd.forward(request, response);
		}
		if(request.getParameter("status1").equals("customer"))
		{
			try 
			{
		    HelperUtil.stringCheck(request.getParameter("customerId"));
		    int id=Integer.parseInt(request.getParameter("customerId"));
			if(request.getParameter("update").equals("active"))
			{
			    obj.setCustomerStatus(id, 0);
			}
			else if(request.getParameter("update").equals("inactive"))
			{
				obj.setCustomerStatus(id, 1);
			}
		} 
		catch (MistakeOccuredException e)
	    {
		   System.out.println(e.getMessage());	
		}
	    request.setAttribute("text","Customer status updated Successfully");
		RequestDispatcher req=request.getRequestDispatcher("CustomerServlet");
		req.forward(request, response);
		}
		else if(request.getParameter("status1").equals("account"))
		{
			try 
			{
				HelperUtil.stringCheck(request.getParameter("cusId"));
				HelperUtil.stringCheck(request.getParameter("accId"));
				HelperUtil.stringCheck(request.getParameter("status"),"please Enter the correct Status");
			    int id=Integer.parseInt(request.getParameter("cusId"));
			    long accNo=Long.parseLong(request.getParameter("accId"));
			    int status=Integer.parseInt(request.getParameter("status"));
				obj.setAccountStatus(id, accNo, status);
			} catch (MistakeOccuredException e) 
			{
				request.setAttribute("text","status is not updated");
				RequestDispatcher req=request.getRequestDispatcher("AccountServlet");
				req.forward(request, response);
			}
//			request.setAttribute("text","status updated sucessfully.");
//			RequestDispatcher req=request.getRequestDispatcher("AccountServlet");
//			req.forward(request, response);
			response.getWriter().write("Status Updated Sucessfully");
		}
//		doGet(request, response);
	}

}
