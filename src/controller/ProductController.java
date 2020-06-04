package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDaoImp;
import model.Product;

/**
 * Servlet implementation class ProductController
 */
@WebServlet( urlPatterns= {"/new"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Getting into Add New product");
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/productListView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entering do post");
		
		String productName = request.getParameter("productName");
		int quantity= Integer.parseInt(request.getParameter("quantity"));
		int mrp= Integer.parseInt(request.getParameter("mrp"));
		System.out.println("New Product/item added to inventory:");
		System.out.println("Product Name:" +productName);
		System.out.println("Product Quantity:" +quantity);
		System.out.println("Product Price:"+mrp);
		
		
		Product  product = new Product();
		product.setProductname(productName);
		product.setQuantity(quantity);
		product.setMrp(mrp);
		
		ProductDaoImp productdao = new ProductDaoImp();
		try {
			productdao.insertProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("AllItems");
	}

}
