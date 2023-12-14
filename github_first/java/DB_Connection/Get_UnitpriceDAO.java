package DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Get_UnitpriceDAO extends DB_Connect{
	
	public int getUnitPrice(String product) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int product_price = 0;
		
		String sql = "SELECT price FROM product WHERE product_name = ?";
		
		try {
			connect();
			ps = con.prepareStatement(sql);
			ps.setString(1, product);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				product_price = rs.getInt("Price");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		disconnect();
		return product_price;
	}
	
}
