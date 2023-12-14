package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import beans.AccountBeans;

public class AccountRegisterDAO {
	//データベースへの接続情報
	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER = "postgres";
	private final String PASS = "ff130317";
	
	Connection con = null;
	
	 public void connect(){
		    try{
		    	Class.forName("org.postgresql.Driver");
		      con = DriverManager.getConnection(URL,USER,PASS);
		    } catch(Exception e){
		      e.printStackTrace();
		    }
		  }
	
	//アカウント情報の登録SQL文作成
	public int insert (AccountBeans newAccount) {
		String sql = "INSERT INTO ACCOUNT (loginId,password,name) VALUES (?,?,?)";
		 int result = executeSql(sql,newAccount);
		 return result;
		
	}
	
	public int executeSql(String sql,AccountBeans newAccount) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			connect();
		ps = con.prepareStatement(sql);
		
		//データをset
		ps.setString(1, newAccount.getLoginId());
		ps.setString(2, newAccount.getPassword());
		ps.setString(3, newAccount.getName());
		
		 result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				if(ps != null) ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		disconnect();
		return result;
	}
	
	 public void disconnect(){
		    try{
		      if(con != null) con.close();
		    } catch(Exception e){
		      e.printStackTrace();
		    }
		  }
}
