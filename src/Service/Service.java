package Service;

import java.sql.ResultSet;
import java.util.HashMap;

import DBConect.DbConect;

public class Service {
	private static final String usr = "test";
	private static final String pwd = "test123";
	private static final String url = "jdbc:mysql://localhost/iknow";
	
	public static boolean erstelleEintrag(String name, String inhalt){
		try{
			DbConect db = new DbConect(url, usr, pwd);
			db.open();
			int i = -1;
			if((name != null || name != "")&&(inhalt != null || inhalt != ""))
				i =db.exeUp("INSERT INTO iknow_know SET thema='"+name+"', contex='"+inhalt+"'");
			db.close();
			if(i == 1)
				return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public static HashMap<String,String> sucheEintrag(String suchwort){
		try{
			DbConect db = new DbConect(url, usr, pwd);
			db.open();
			HashMap<String,String> m = new HashMap<String,String>();
			ResultSet r = db.exeQu("SELECT * FROM iknow_know WHERE thema='*" + suchwort + "*'");
			while(r.next()){
				m.put(r.getString("thema"), r.getString("contex"));
			}
			r.close();
			db.close();
			return m;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
