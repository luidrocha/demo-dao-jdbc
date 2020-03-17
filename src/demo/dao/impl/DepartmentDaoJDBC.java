package demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import demo.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {

		this.conn = conn;

	}

	@Override
	public void insert(Department obj) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("INSERT INTO Department (Name)  VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {

					int Id = rs.getInt(1);
					obj.setId(Id);
					DB.closeResultSet(rs);

				}
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		}

		finally {

			DB.closeStatemant(st);
		}

	}

	@Override
	public void update(Department obj) {
		//

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("UPDATE Department SET Name=?  WHERE id = ?");

			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {

				System.out.println(" Registros afetados: " + linhasAfetadas);
				System.out.println("Departamento Atualizado: " + obj.getId());

			} else {System.out.println("Nenhuma linha atualizada !");
			
				System.out.println("Departamento Inexistente: " + obj.getId());
			}
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatemant(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
