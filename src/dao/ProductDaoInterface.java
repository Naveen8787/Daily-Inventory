package dao;

import java.sql.SQLException;
import java.util.List;
import model.Product;



public interface ProductDaoInterface {
	
	boolean  deleteProduct (int id) throws SQLException, Exception;
	List<Product> selectAllProduct() throws Exception;
	void insertProduct(Product product)throws SQLException, Exception;
	boolean updateProduct(Product product) throws SQLException, Exception;
	Product selectProduct(int productid) throws Exception;
	boolean updateProduct(int id) throws SQLException, Exception;

}
