package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import utility.ConnectionManager;

public class ProductDaoImp implements ProductDaoInterface {
	
	final private  String INSERT_PRODUCT_QUERY = "INSERT INTO PRODUCT_PRODUCT (productid, productName, quantity,mrp) VALUES (seq_PRODUCT_PRODUCT.nextval, ?, ?, ?)";
//	final private  String INSERT_PRODUCT_QUERY = "INSERT INTO PRODUCT_PRODUCT (productid, productName, quantity,mrp) VALUES (?, ?, ?, ?)";
	  final String SELECT_ALL_PRODUCT ="SELECT * FROM PRODUCT_PRODUCT";
	  final String  DELETE_PRODUCT_ID="DELETE FROM PRODUCT_PRODUCT WHERE productid=?";
	 

	@Override
	public boolean deleteProduct(int id) throws SQLException, Exception {
		System.out.println(id);
		boolean rowDeleted;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_PRODUCT_ID);
		ps.setInt(1, id);
		rowDeleted= ps.executeUpdate()>0;
		
		return rowDeleted;
	}

	@Override
	public List<Product> selectAllProduct() throws Exception {
		Product product= null;
		List <Product> productlist = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_PRODUCT);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int Id = rs.getInt("productid");
			String name = rs.getString("productName");
			int quantity = rs.getInt("quantity");
			int mrp = rs.getInt("mrp");
			
			product = new Product();
			
			product.setProductId(Id);
			product.setProductname(name);
			product.setQuantity(quantity);
			product.setMrp(mrp);
			
			productlist.add(product);
		}
		return productlist;
	}

	@Override
	public void insertProduct(Product product) throws SQLException, Exception {

        System.out.println(INSERT_PRODUCT_QUERY);
try{
	Connection con = ConnectionManager.getConnection();
	PreparedStatement ps= con.prepareStatement(INSERT_PRODUCT_QUERY);
//	ps.setInt(1, blog.getBlogId());
	//ps.setInt(1, product.getProductId());
	ps.setString(1, product.getProductname());
	ps.setInt(2, product.getQuantity());
	ps.setInt(3, product.getMrp());
	
	System.out.println(ps);
	ps.executeUpdate();
	con.close();
}
catch(SQLException | ClassNotFoundException e) 
{
System.out.println(e);
}

		
	}
	@Override
	public Product selectProduct(int id) throws Exception {
		//Product pro=new Product();
		Product product= new Product();
		ConnectionManager cm=new ConnectionManager();
		Connection con=cm.getConnection();
		Statement stmt=null;
		stmt=con.createStatement();
		String sql="select * FROM PRODUCT_PRODUCT WHERE ProductID="+id;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
	    while(rs.next()){
	    	int Id = rs.getInt("productid");
			String name = rs.getString("productName");
			int quantity = rs.getInt("quantity");
			int mrp = rs.getInt("mrp");
						
			product.setProductId(Id);
			product.setProductname(name);
			product.setQuantity(quantity);
			product.setMrp(mrp);
			
			//productlist.add(product);
	    	//pro.setProductId(rs.getInt("productid"));
	    	//pro.setProductname(rs.getString("productname"));
	    	//pro.setQuantity(rs.getInt("quantity"));
	    	//pro.setMrp(rs.getInt("mrp"));
	       //  pro.setBlogId(rs.getInt("id"));
	       //  pro.setBlogDescription(rs.getString("description"));
	       //  pro.setBlogTitle(rs.getString("title"));
	       //  java.time.LocalDate localDate = rs.getDate("posted_On").toLocalDate();
	       //  pro.setPostedOn(localDate);
	    }
	    rs.close();
		stmt.close();
		con.close();
		return product;
	}
	@Override
	public boolean updateProduct(Product product) throws SQLException, Exception {
		String sqlQuery = "UPDATE PRODUCT_PRODUCT SET productName = ?, quantity= ?, mrp = ? WHERE productid = ?";
		ConnectionManager cm=new ConnectionManager();
		Connection con=cm.getConnection();
		PreparedStatement ps = con.prepareStatement(sqlQuery);
//		ps.setInt(1, blog.getBlogId());
		ps.setString(1, product.getProductname());
		ps.setInt(2, product.getQuantity());
		ps.setInt(3, product.getMrp());
		ps.setInt(4,product.getProductId());
		System.out.println(ps);
		ps.executeUpdate();
		con.close();
		return false;
	}

	@Override
	public boolean updateProduct(int id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
