package Aplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import demo.dao.DaoFactory;
import demo.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main (String[] args) {
		
		
		
		Department obj = new Department(1,"Books");
		
		System.out.println(obj);
		
		Seller sl  =new Seller(1, "Jose","luidrocha@yahoo.com.br", new Date(),obj, 3000.0 );
		
		System.out.println(sl);
		
		SellerDao selerDao = DaoFactory.createSellerDao();
		
	}

}
