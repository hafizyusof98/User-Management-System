package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.UserBean;


@WebServlet("/viewUserController")
public class viewUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public viewUserController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		UserBean userB = new UserBean();
		UserDao userD = new UserDao();
		if(action.equalsIgnoreCase("viewUser")) {
			
			int Id = Integer.parseInt(request.getParameter("id"));
			userB.setId(Id);
			userB = userD.getUserByID(Id);
			request.setAttribute("user", userB);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewUser.jsp");
			dispatcher.forward(request, response);
		}

	}


}
