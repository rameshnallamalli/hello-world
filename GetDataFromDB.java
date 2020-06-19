package com.orbi.lending;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GetDataFromDB {

	public static void main(String[] args) {

		String ls_query = "select ? colName ,nvl(to_char(mpaia_validity_date,'DD-MON-YYYY'),'') ,mpaia_restructure_type "
				+ "from M_PROJ_ADDTL_INFO_ADV  where  mpaia_cust_id =?  and mpaia_con_id = ? and mpaia_proj_id =?";

		String desc = null;
		ArrayList al = new ArrayList();
		String val = "1 OR 1=1";
		
		HashMap hm = new HashMap();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.11.16.43:1521/SIR16842", "EPIC_LMS",
					"EPIC_LMS");
			System.out.println("connection is obtained " + con);
			
			PreparedStatement psmt = con.prepareStatement(ls_query);
			psmt.setString(1, "mpaia_cust_id");
			psmt.setString(2, "0000000003");
			psmt.setString(3, "001");
			psmt.setString(4, "0026");
			
			ResultSet resultSet = psmt.executeQuery();
			int count = 0;
			if (resultSet.next()) {
				desc  = resultSet.getString(1);
			}
			System.out.println(desc);

		} catch (SQLException e) {
			System.out.println("exception in getting the connnection");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("class not found");
			e.printStackTrace();
		}
	}

}
