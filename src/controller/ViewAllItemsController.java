package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDaoImp;
import model.Product;

/**
 * Servlet implementation class ViewAllItemsController
 */
@WebServlet( urlPatterns= {"/AllItems"})
public class ViewAllItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllItemsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("view all product");
		System.out.println("Getting all product");
		
		ProductDaoImp productdao= new ProductDaoImp();
		List<Product> listProduct = null;
		try {
			listProduct = productdao.selectAllProduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Product productlist:listProduct) {
			System.out.println("Product ID:" +productlist.getProductId());
			System.out.println("Product Name:" +productlist.getProductname());
			System.out.println("Product Quantity:" +productlist.getQuantity());
			System.out.println("Product Price:" +productlist.getMrp()+"\n\n");
			
		}
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher rd =this.getServletContext().getRequestDispatcher("/WEB-INF/views/productView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
