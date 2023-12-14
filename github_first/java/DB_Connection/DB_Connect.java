package DB_Connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connect {
	//データベースへの接続情報
		private final String URL = "jdbc:postgresql://localhost:5432/postgres";
		private final String USER = "postgres";
		private final String PASS = "ff130317";
		
		public Connection con = null;
		
		public void connect() {
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(URL,USER,PASS);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void disconnect() {
			try {
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}
