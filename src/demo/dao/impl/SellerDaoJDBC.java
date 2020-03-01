package demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
							+ " ON seller.departmentId = department.Id "
							+ " WHERE seller.Id = ? ");

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
		// TODO Auto-generated method stub
		return null;
	}

}
