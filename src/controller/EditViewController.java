package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDaoImp;
import model.Product;




@WebServlet(urlPatterns = {"/edit"})
public class EditViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
  		try {
			showEditForm(request,response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductDaoImp productDAO = new ProductDaoImp();
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingproduct = productDAO.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/productListView.jsp");
		request.setAttribute("productlist", existingproduct);
		dispatcher.forward(request, response);

	}
	
}
