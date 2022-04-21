package App;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import Details.AccountDetails;
import Details.Customer;
import logic_With_persistence.LogicLayer;
import newexception.MistakeOccuredException;
import util.HelperUtil;

///**
// * Servlet implementation class Profile
// */
//@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		HttpSession session=request.getSession();
//		if(session.getAttribute("lastId")==null)
//		{
//			RequestDispatcher req=request.getRequestDispatcher("login.jsp");
//			req.forward(request, response);
//		}
//		else
//		{
		Customer cusObj=null;
		AccountDetails accObj=null;
		Map mapObj=new HashMap();
		try {
			if(request.getParameter("update").equals("active"))
			{
				HelperUtil.stringCheck(request.getParameter("customerId"));
		        int id=Integer.parseInt(request.getParameter("customerId"));
		        cusObj=obj.getCustomerDetails(id);
		        session.setAttribute("Customer", cusObj);
				RequestDispatcher req=request.getRequestDispatcher("AddCustomer.jsp");
				req.forward(request, response);
			}
			else if(request.getParameter("update").equals("profile"))
			{
                HelperUtil.stringCheck((String) session.getAttribute("userId"));
				cusObj=obj.getCustomerDetails(Integer.parseInt((String) session.getAttribute("userId")));
				mapObj.put("customerName", cusObj.getName());
				mapObj.put("DOB", cusObj.getDob());
				mapObj.put("address", cusObj.getAddress());
				mapObj.put("phoneNumber", cusObj.getPhoneNumber());
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(mapObj));
//				session.setAttribute("Customer", cusObj);
//				RequestDispatcher req=request.getRequestDispatcher("myprofile.jsp");
//				req.forward(request, response);
//				doGet(request, response);
			}
			else if(request.getParameter("update").equals("account"))
			{
				accObj=obj.getAccount(Integer.parseInt(request.getParameter("acc")), Long.parseLong(request.getParameter("accNo")));
				session.setAttribute("Account", accObj);
				RequestDispatcher req=request.getRequestDispatcher("CustomerCount?moneyexchange=update");
				req.forward(request, response);
			}
		}catch (NumberFormatException | MistakeOccuredException e)
		{
			e.printStackTrace();
		}
//		}
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
//			RequestDispatcher req=request.getRequestDispatcher("login.jsp");
//			req.forward(request, response);
//		}
//		else
//		{
//		Customer cusObj=null;
//		AccountDetails accObj=null;
//		try {
//			if(request.getParameter("update").equals("active"))
//			{
//				HelperUtil.stringCheck(request.getParameter("customerId"));
//		        int id=Integer.parseInt(request.getParameter("customerId"));
//		        cusObj=obj.getCustomerDetails(id);
//		        session.setAttribute("Customer", cusObj);
//				RequestDispatcher req=request.getRequestDispatcher("AddCustomer.jsp");
//				req.forward(request, response);
//			}
//			else if(request.getParameter("update").equals("profile"))
//			{
//                HelperUtil.stringCheck((String) session.getAttribute("userId"));
//				cusObj=obj.getCustomerDetails(Integer.parseInt((String) session.getAttribute("userId")));
//				session.setAttribute("Customer", cusObj);
//				RequestDispatcher req=request.getRequestDispatcher("myprofile.jsp");
//				req.forward(request, response);
//				doGet(request, response);
//			}
//			else if(request.getParameter("update").equals("account"))
//			{
//				accObj=obj.getAccount(Integer.parseInt(request.getParameter("acc")), Long.parseLong(request.getParameter("accNo")));
//				session.setAttribute("Account", accObj);
//				RequestDispatcher req=request.getRequestDispatcher("CustomerCount?moneyexchange=update");
//				req.forward(request, response);
//			}
//		}catch (NumberFormatException | MistakeOccuredException e)
//		{
//			e.printStackTrace();
//		}
//		}
//	}

}
