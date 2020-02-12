package demo.dao;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		
		return new SellerDaoJDBC();
		
	}

}
