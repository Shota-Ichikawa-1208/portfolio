package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.AccountBeans;

public class AccountSerchDAO {
	//データベースへの接続情報
		private final String URL = "jdbc:postgresql://localhost:5432/postgres";
		private final String USER = "postgres";
		private final String PASS = "ff130317";
		
		Connection con = null;
		
		//DBへの接続メソッド
		public void connect() {
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(URL,USER,PASS);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public AccountBeans serch(String loginId,String password) {
			AccountBeans User = new AccountBeans(); 
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "SELECT loginId,password,name FROM ACCOUNT WHERE loginid = ? and password = ?";
			
			
			try {
				connect();
				ps = con.prepareStatement(sql);
				
				ps.setString(1,loginId);
				ps.setString(2,password);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					
					User.setLoginId(rs.getString("loginId"));
					User.setPassword(rs.getString("password"));
					User.setName(rs.getString("name"));
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
			return User;
		}

		 public void disconnect(){
			    try{
			      if(con != null) con.close();
			    } catch(Exception e){
			      e.printStackTrace();
			    }
			  }
}
