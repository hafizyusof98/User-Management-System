package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.UserBean;


@WebServlet("/registerUserController")
public class registerUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userD = new UserDao();
	String action = "";

    public registerUserController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	action = request.getParameter("action");
    	HttpSession session = request.getSession();
    	if(action.equalsIgnoreCase("updateR")) {
    		UserBean userB = new UserBean();
    		int id = Integer.parseInt(request.getParameter("id"));
    		userB.setId(id);
    		userB = userD.getUserByID(id);
    		request.setAttribute("user", userB);
    		RequestDispatcher view = request.getRequestDispatcher("updateUser.jsp");
    		view.forward(request, response);
    	}
    	else if(action.equalsIgnoreCase("deleteR")) {
    		UserBean userB = new UserBean();
    		int id = Integer.parseInt(request.getParameter("id"));
    		userB.setId(id);
    		userB = userD.getUserByID(id);
    		request.setAttribute("user", userB);
    		RequestDispatcher view = request.getRequestDispatcher("deleteUser.jsp");
    		view.forward(request, response);
    	}
    	else if(action.equalsIgnoreCase("logout")) {
    		session.setAttribute("userID", null);
    		session.setAttribute("userName", null);
    		session.invalidate();
    		response.sendRedirect("login.jsp");
    	}

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (action.equalsIgnoreCase("register")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("country");
			
			UserBean userB = new UserBean();
			
			userB.setName(name);
			userB.setEmail(email);
			userB.setCountry(country);
			
			try {
				userD.addUser(userB);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("success.jsp");
		}
		else if(action.equalsIgnoreCase("login")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");
			
			UserBean userB = new UserBean();
			
			userB.setId(id);
			userB.setPassword(password);
			
			userB = userD.login(userB);
			
			if(userB.isValid()) {
				session.setAttribute("userID", userB.getId());
				session.setAttribute("userName",userB.getName());
				RequestDispatcher view = request.getRequestDispatcher("registerUser.jsp");
				view.forward(request, response);
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("login.jsp");
				view.forward(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("updateW")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("country");
			
			UserBean userB = new UserBean();
			
			userB.setId(id);
			userB.setName(name);
			userB.setEmail(email);
			userB.setCountry(country);
			
			userB = userD.userExistance(userB);
			
			if(!userB.isValid()) {
				try {
					userD.addUser(userB);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("success.jsp");
			}
			else if(userB.isValid()) {
				try {
				userD.updateUser(userB);
				}catch(Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("user", userB);
				RequestDispatcher view = request.getRequestDispatcher("viewUser.jsp");
				view.forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("deleteW")) {
			int id = Integer.parseInt(request.getParameter("id"));
			UserBean userB = new UserBean();
			userB.setId(id);
			userD.deleteUser(id);
			List<UserBean> userL = userD.getAllUser();
			request.setAttribute("user", userL);
			RequestDispatcher view = request.getRequestDispatcher("viewListUser.jsp");
			view.forward(request, response);
		}

	}

}
