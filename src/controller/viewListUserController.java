package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.UserBean;


@WebServlet("/viewListUserController")
public class viewListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public viewListUserController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userD = new UserDao();
		List<UserBean> userL = userD.getAllUser();
		request.setAttribute("userL", userL);
		RequestDispatcher view = request.getRequestDispatcher("viewListUser.jsp");
		view.forward(request, response);		
	}



}
