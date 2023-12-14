package order_confirmation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Order_ProductBeans;

public class Order_ConfimationDAO {
	//データベースへの接続情報
			private final String URL = "jdbc:postgresql://localhost:5432/postgres";
			private final String USER = "postgres";
			private final String PASS = "ff130317";
			
			Connection con = null;
			PreparedStatement Order_ps = null;
			PreparedStatement Stock_ps = null;
			ResultSet rs = null;
			
			int target_product_stock = 0;
			int result = 0;
			int totalResult = 0;
			//DBへの接続メソッド
			public void connect() {
				try {
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection(URL,USER,PASS);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			//注文情報のDBへの登録SQL文
			public int insert (String loginId,ArrayList<Order_ProductBeans> cart_list ) {
				//Order_table表の更新SQL文
				String Order_sql = "INSERT INTO Order_table (loginId,Product_Name,Size,Quantity) VALUES (?,?,?,?)";
				//Product表から注文件数ぶん在庫を減らすSQL文
				String stock_sql = "UPDATE product SET STOCK = ? WHERE Product_Name = ?";
				 int result = executeSql(Order_sql,stock_sql,loginId,cart_list);
				 return result;
				
			}
			
			//SQL文の実行
			@SuppressWarnings("resource")
			private int executeSql(String Order_sql,String Stock_sql,String loginId,ArrayList<Order_ProductBeans> cart_list) {
				//PreparedStatement Order_ps = null;
				//PreparedStatement Stock_ps = null;
				//ResultSet rs = null;
//				int target_product_stock = 0;
//				int result = 0;
//				int totalResult = 0;
				try {
					connect();
					//トランザクション開始
					con.setAutoCommit(false);
					Order_ps = con.prepareStatement(Order_sql);
				
					//cart_listから注文を一件ずつ取り出し登録
					for(Order_ProductBeans one_order : cart_list) {
						String Product_Name = one_order.getProduct_color();
						String Size = one_order.getSize();
						int Quantity = one_order.getQuantity();
						
						//Order_tableへ登録
						Order_ps.setString(1, loginId);
						Order_ps.setString(2, Product_Name);
						Order_ps.setString(3, Size);
						Order_ps.setInt(4, Quantity);
						result = Order_ps.executeUpdate();
						totalResult += result;
						
						//product表から対象の商品在庫をとってくるSQL文
						Stock_ps = con.prepareStatement("SELECT stock FROM product WHERE Product_Name = ?");
						//減らしたい商品名をセット
						Stock_ps.setString(1,Product_Name );
						rs =Stock_ps.executeQuery();
						//在庫を取得
						if(rs.next()) {
						target_product_stock = rs.getInt("stock");
						//Product表から在庫を減らすSQL文をセット
						// 在庫の利用可能性を確認
				        if (target_product_stock < Quantity) {
				            // 在庫が不足している場合、エラーメッセージを設定して-1を返す
				            totalResult = -1;
				            setErrorMsg("商品「" + Product_Name + "」の在庫が不足しています");
				            return totalResult;
				        }
				      //在庫があった場合Product表から在庫を減らすSQL文をセット
//						Stock_ps = con.prepareStatement(Stock_sql);
//						Stock_ps.setInt(1,target_product_stock-Quantity);
//						Stock_ps.setString(2,Product_Name);
//						result = Stock_ps.executeUpdate();
				        result = InventoryControl(Stock_sql,Quantity,Product_Name);
						}
					}
					//コミット
					con.commit();
				}catch(Exception e) {
					try {
					 con.rollback();
					}catch(SQLException f) {
						f.printStackTrace();
					}
					e.printStackTrace();
					totalResult = -1;
				}finally {
					try {
						if(rs != null)rs.close();
						if(Stock_ps != null)Stock_ps.close();
						if(Order_ps != null)Order_ps.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
						
					
				}
				disconnect();
				//カートの中身をクリア
				cart_list.clear();
				
				return totalResult;
			}
			
			 public void disconnect(){
				    try{
				      if(con != null) con.close();
				    } catch(Exception e){
				      e.printStackTrace();
				    }
				  }
			 
			 //在庫がなかった場合のエラーメッセージの処理
			 private String errorMsg;

			 public void setErrorMsg(String errorMsg) {
			     this.errorMsg = errorMsg;
			 }

			 public String getErrorMsg() {
			     return errorMsg;
			 }
			 
			 //在庫があった場合のメソッド
			 private int InventoryControl(String stock_sql,int Quantity,String Product_Name) throws Exception {
				//在庫があった場合Product表から在庫を減らすSQL文をセット
					Stock_ps = con.prepareStatement(stock_sql);
					Stock_ps.setInt(1,target_product_stock-Quantity);
					Stock_ps.setString(2,Product_Name);
					result = Stock_ps.executeUpdate();
					return result;
			 }
}
