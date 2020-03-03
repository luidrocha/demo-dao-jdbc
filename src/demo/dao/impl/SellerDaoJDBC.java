package demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import demo.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

// Guarda toda implementacao herdada pela interface 

public class SellerDaoJDBC implements SellerDao {

	// seta a connexao

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {

		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					" SELECT seller.*, department.Name  as DepName " + " FROM seller INNER JOIN department "
							+ " ON seller.departmentId = department.Id " + " WHERE seller.Id = ? ");

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) { // testa se rs retornou diferente de nullo

				Department dep = IntantiateDep(rs);

				Seller seller = InstantiateSeller(rs, dep);

				return seller;
			}

			return null;

		}

		catch (SQLException e) {

			throw new DbException(e.getMessage());

		}

		finally {

			DB.closeResultSet(rs);
			DB.closeStatemant(st);
		}

	}

	// Reaproveitamento de codigo. Instancia Seller

	private Seller InstantiateSeller(ResultSet rs, Department dep) throws SQLException {

		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("birthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(dep);

		return seller;

	}
	// Reaproveitamento de codigo. Instancia Departamaneto

	private Department IntantiateDep(ResultSet rs) throws SQLException {

		Department dep = new Department();

		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));

		return dep;
	}

	@Override
	public List<Seller> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			if (conn != null) {

				st = conn.prepareStatement("SELECT Seller.*, Department.Name as DepName "
						+ " FROM Seller INNER JOIN Department ON  Seller.DepartmentId= Department.Id ORDER BY NAME");

				rs = st.executeQuery();
				
				Map<Integer, Department> map = new HashMap<>();
				List<Seller> seller = new ArrayList<>();
				
				Department dep = new Department();
				
				while (rs.next()) {

					dep=map.get(rs.getInt("DepartmentId"));

					if (dep==null) {
						
					 dep = IntantiateDep(rs);
				}
					Seller obj = InstantiateSeller(rs, dep);
					map.put(rs.getInt("DepartmentId"), dep);
					seller.add(obj);

				}

				return seller;

			}

			return null;

		}

		catch (SQLException e) {

			throw new DbException(e.getMessage());
		}

		finally {
			DB.closeResultSet(rs);
			DB.closeStatemant(st);
		}
	}

	@Override
	public List<Seller> findDepartment(Department department) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			if (conn != null) {

				st = conn.prepareStatement(
						" SELECT Seller.*,Department.Name as DepName FROM Seller " + " INNER JOIN Department "
								+ " ON Seller.DepartmentId = Department.Id WHERE DepartmentId= ?" + " ORDER BY name");

				st.setInt(1, department.getId());

				rs = st.executeQuery();

				Map<Integer, Department> map = new HashMap<>();
				
				List<Seller> lista = new ArrayList<>();

				

				while (rs.next()) {

					Department dep = map.get(rs.getInt("DepartmentId"));

					if (dep == null) {

						dep = IntantiateDep(rs);
						map.put(rs.getInt("DepartmentId"), dep);

					}
					Seller seller = InstantiateSeller(rs, dep);

					lista.add(seller);

				}
				return lista;
			}

			return null;
		}

		catch (SQLException e) {

			throw new DbException(e.getMessage());

		}

		finally {
			DB.closeResultSet(rs);
			DB.closeStatemant(st);
		}
	}

}
