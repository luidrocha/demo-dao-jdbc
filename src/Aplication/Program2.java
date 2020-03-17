package Aplication;

import db.DB;
import demo.dao.DaoFactory;
import demo.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		//

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department dep = new Department(null, "DBA");
		
		departmentDao.insert(dep);
		
		System.out.println("============== Department Insert ==========");
		
		System.out.println(dep.getId());
		

	}

}
