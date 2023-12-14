package DB_Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.My_OrderBeans;

public class My_OrderDAO extends DB_Connect{
	//全注文を格納するリスト
	ArrayList My_Order = new ArrayList<My_OrderBeans>();
	//DBから注文履歴をとってくるメソッド
	public ArrayList getMyOrder(String User_name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String loginId = User_name;
		//必要情報の取得SQL文
		String sql = "SELECT order_no,product_name,size,quantity,order_time FROM order_table WHERE loginId = ? ORDER BY order_time";
		
		try {
			connect();
			ps = con.prepareStatement(sql);
			ps.setString(1,loginId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				My_OrderBeans MyOrder = new My_OrderBeans();
				
				MyOrder.setOrderId(rs.getInt("order_no"));
				MyOrder.setProductName(rs.getString("product_name"));
				MyOrder.setSize(rs.getString("size"));
				MyOrder.setQuantity(rs.getInt("quantity"));
				MyOrder.setOrderDate(rs.getDate("order_time"));
				//1件分の注文をリストに格納
				My_Order.add(MyOrder);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(ps != null)ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		disconnect();
		
		//注文が入ったリストを返す
		return My_Order;
	}
}
