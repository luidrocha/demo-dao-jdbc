package Aplication;

import java.util.ArrayList;
import java.util.List;

import demo.dao.DaoFactory;
import demo.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		//

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department dep1 = new Department(8, "Eletrica");
//		
//		departmentDao.insert(dep);
//		
//		System.out.println("============== Department Insert ==========");
		
	//	System.out.println(dep.getId());
		
		System.out.println("/n/n============== Department Update ==========/n/n");
		
		departmentDao.update(dep1);
		
		System.out.println("/n/n============== Department Delete ==========/n/n");
		
		departmentDao.deleteById(14);
		
		System.out.println("/n/n============== Department FindByID ==========/n/n");
		
		
					
		Department dep2 = departmentDao.findById(6);
		
		if (dep2==null) {
			
			System.out.println("Codigo do departmento nao encontrado");
			
		}
		
		else {					
			
			System.out.println("Department: #" + dep2.getId());
			System.out.println("Name: #" + dep2.getName());
			
		}
		
		System.out.println("/n/n============== Department FindAll ==========/n/n");
		
		List<Department> lista = new ArrayList<>();
		
		lista = departmentDao.findAll();
		
		for (Department dp: lista) {
			
			System.out.println(dp);
			
		}
		
	}

}
