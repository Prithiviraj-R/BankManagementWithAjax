package App;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import logic_With_persistence.LogicLayer;
import Details.Customer;
import newexception.MistakeOccuredException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<Integer, Customer> customer = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {

		// TODO Auto-generated method stub
//		LogicLayer obj = new LogicLayer(false);
//		HttpSession session = request.getSession();
//		Map mapObj = new HashMap();
//		if (session.getAttribute("lastId") == null) {
//			
//			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//			rd.forward(request, response);
//		}
//		try {
//			
//			Map<Integer, Customer> customerMap = obj.getactiveCustomer();
////			request.setAttribute("temp", customerMap);
//			/*if (request.getParameter("class") != null) {*/
//				Map<Integer, Customer> inactive = obj.getInactiveCustomer();
//				System.out.println("came and go");
//				/*
//				 * request.setAttribute("inactive", inactive); request.setAttribute("class",
//				 * "inactive");
//				 */
////			}
//
//			response.setContentType("application/json");
//			response.getWriter().write(new Gson().toJson(customerMap));
////			RequestDispatcher rd=request.getRequestDispatcher("customer.jsp");  
////			rd.forward(request, response);
//		} catch (MistakeOccuredException e) {
//			e.printStackTrace();
//		}

//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		LogicLayer obj=(LogicLayer) request.getServletContext().getAttribute("Object");
		LogicLayer obj = new LogicLayer(false);
		HttpSession session = request.getSession();
		Map mapObj = new HashMap();
		if (session.getAttribute("lastId") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		try {
			Map<Integer, Customer> customerMap = obj.getactiveCustomer();
			request.setAttribute("temp", customerMap);
			if (request.getParameter("class") != null) {
				Map<Integer, Customer> inactive = obj.getInactiveCustomer();
				request.setAttribute("inactive", inactive);
				request.setAttribute("class", "inactive");
			}

//			response.setContentType("application/json");
//			response.getWriter().write(new Gson().toJson(customerMap));
			RequestDispatcher rd=request.getRequestDispatcher("customer.jsp");  
			rd.forward(request, response);
		} catch (MistakeOccuredException e) {
			e.printStackTrace();
		}
//		doGet(request, response);
	}

}
