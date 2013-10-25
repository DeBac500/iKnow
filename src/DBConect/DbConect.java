package DBConect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConect {
	private Connection con;
	private Statement st;
	private String url, usr, pwd;
	private boolean open = false;
	
	public DbConect(String url, String usr, String pwd){
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
		this.open = false;
	}
	public void open() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, usr, pwd);
		st = con.createStatement();
		this.open = true;
	}
	public void close() throws SQLException{
		if(st != null)
			st.close();
		if(con != null)
		con.close();
		this.open = false;
	}
	public ResultSet exeQu(String qu) throws Exception{
		if(open)
			return st.executeQuery(qu);
		else
			return null;
	}
	public int exeUp(String qu) throws Exception{
		if(open)
			return st.executeUpdate(qu);
		else
			return -1;
	}
}
