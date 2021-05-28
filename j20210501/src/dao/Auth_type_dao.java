package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Auth_type_dao {
	private static Auth_type_dao instance;

	private Auth_type_dao() {

	}

	public static Auth_type_dao getInstance() {
		if (instance == null)
			instance = new Auth_type_dao();

		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public Auth_type showAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Auth_type auth_type = new Auth_type();

		String sql = "SELECT * FROM auth_type";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				auth_type.setAuth_no(rs.getInt("auth_no"));
				auth_type.setAuth_name(rs.getString("auth_name"));
				auth_type.setAuth_enroll(rs.getString("auth_enroll"));
				auth_type.setAuth_modify(rs.getString("auth_modify"));
				auth_type.setAuth_delete(rs.getString("auth_delete"));
				auth_type.setAuth_inquiry(rs.getString("auth_inquiry"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return auth_type;
	}
}
