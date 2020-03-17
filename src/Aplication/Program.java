package Aplication;

import java.util.List;
import java.util.Date;

import demo.dao.DaoFactory;
import demo.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println(" \n=============== TESTE 1: Seller FindById =================");
		Seller seller = sellerDao.findById(3);

		System.out.println(seller);

		System.out.println();

		System.out.println(" \n=============== TESTE 2: Seller FindByDepatmet =================");

		Department department = new Department(3, null);

		List<Seller> lista = sellerDao.findDepartment(department);

		for (Seller sell : lista) {

			System.out.println(sell.toString());

		}

		System.out.println(" \n\n =============== TESTE 3: Seller FindAll =================");

		List<Seller> listf = sellerDao.findAll();

		for (Seller sell : listf) {

			System.out.println(sell);
		}

		System.out.println(" \n\n =============== TESTE 4: Seller Insert =================");
		
		Seller newSeller = new Seller(null, "Jhon Rambo", "Rambo@gmail.com", new Date(),  department, 2500.0 );
		sellerDao.insert(newSeller);
		System.out.println("Id criado " + newSeller.getId());
		
		

		System.out.println(" \n\n =============== TESTE 5 : Seller Update =================");

		seller = sellerDao.findById(11);
		
		seller.setName("Joana Killer");
	
		sellerDao.update(seller);
		
		System.out.println(" \n\n =============== TESTE 6 : Seller Delete =================");

		
		sellerDao.deleteById(11);
		
		System.out.println(" Eliminado !");

		
		
		
	}

}
