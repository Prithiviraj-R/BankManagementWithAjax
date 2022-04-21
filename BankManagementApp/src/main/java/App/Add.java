package App;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import logic_With_persistence.LogicLayer;
import Details.AccountDetails;
import Details.Customer;
import newexception.MistakeOccuredException;
import util.HelperUtil;

/**
 * Servlet implementation class AddCustomer
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		LogicLayer obj=new LogicLayer(false);
		HttpSession session=request.getSession();
//		if(session.getAttribute("userId")==null)
//		{
//			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
//            rd.forward(request, response);
//		}
//		else
//		{
//		if(request.getParameter("action").equals("Customer"))
//		{
		try
		{
		HelperUtil.stringCheck(request.getParameter("name"),"please Enter the correct name.");
		HelperUtil.stringCheck(request.getParameter("dob"));
		HelperUtil.stringCheck(request.getParameter("address"),"please enter the correct address");
		HelperUtil.stringCheck(request.getParameter("phone"));
		String name=request.getParameter("name");
		System.out.println(name);
		String dob=request.getParameter("dob");
		String address=request.getParameter("address");
		long phoneNumber=Long.parseLong(request.getParameter("phone"));
		System.out.println(phoneNumber);
//		HelperUtil.numberCheck((int) Long.parseLong(request.getParameter("phone")));
		Customer cusObj=new Customer();
		cusObj.setName(name);
		cusObj.setDob(dob);
		cusObj.setAddress(address);
		cusObj.setPhoneNumber(phoneNumber);
		System.out.println(cusObj);
		obj.addCustomerInfo(cusObj);
		System.out.println("Sucessfully inserted");
		response.getWriter().write("Sucessfully inserted");
//		   request.setAttribute("text","Customer added sucessfully");
//		   RequestDispatcher disp=request.getRequestDispatcher("CustomerServlet");
//		   disp.forward(request, response);
	   }
	   catch(MistakeOccuredException ex)
	   {
		   ex.printStackTrace();
		   System.out.println("Error in Exception Area");
		   request.setAttribute("message",ex.getMessage());
		   RequestDispatcher disp=request.getRequestDispatcher("AddCustomer.jsp");
		   disp.forward(request, response);
	   }
//	   }
//	   else if(request.getParameter("action").equals("Account"))
//		{
//		   try 
//		   {
//			HelperUtil.stringCheck(request.getParameter("cars"));
//			HelperUtil.stringCheck(request.getParameter("branch"));
//			HelperUtil.stringCheck(request.getParameter("Balance"));
//			int id=Integer.parseInt(request.getParameter("cars"));
//		    String branch=request.getParameter("branch");
//		    System.out.println(branch);
//		    double amount=Double.parseDouble(request.getParameter("Balance"));
//		    HelperUtil.numberCheck((int) amount);
//		    AccountDetails accDetails=new AccountDetails();
//		    accDetails.setCustomerId(id);
//		    accDetails.setBranch(branch);
//		    accDetails.setBalance(amount);
//			obj.accountToCustomerId((int) id,accDetails);
//			request.setAttribute("text","Account added sucessfully");
//			RequestDispatcher req=request.getRequestDispatcher("AccountServlet");
//			req.forward(request, response);
//			} 
//		   catch (MistakeOccuredException e) 
//			{
////			   e.printStackTrace();
//				   request.setAttribute("message",e.getMessage());
//				   RequestDispatcher disp=request.getRequestDispatcher("CustomerCount?moneyexchange=addAccount");
//				   disp.forward(request, response);
//			}
//		}
//		}
	}

}
