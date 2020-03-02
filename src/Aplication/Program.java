package Aplication;

import java.util.List;

import demo.dao.DaoFactory;
import demo.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main (String[] args) {
		
		
		
			
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println(" \n=============== TESTE 1: Seller FindById =================");
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		System.out.println();
		
		System.out.println(" \n=============== TESTE 2: Seller FindByDepatmet =================");
		
		Department department = new Department(2,null);
		
		List<Seller> lista = sellerDao.findDepartment(department);
		
		for (Seller sell : lista) {
			
			System.out.println(sell.toString());
			
		}
		
		
		
		
	}

}
