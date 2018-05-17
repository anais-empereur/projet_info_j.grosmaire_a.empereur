
package projetinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class College {
	//attributs
	private String nom;
	private int num_ac;
	private Adresse adresse;
	
	//accesseurs et mutateurs
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNum_ac() {
		return num_ac;
	}
	public void setNum_ac(int num_ac) {
		this.num_ac = num_ac;
	}
	public void setAdresse(Adresse adresse){
		this.adresse = adresse;
	}
	public Adresse getAdresse(){
		return adresse;
	}
	
	
	//constructeur
	public College(Adresse adresse, String nom, int num_ac){
		super();
		this.nom = nom;
		this.num_ac = num_ac;
		
		int idAd = adresse.getidBDD();
		Connection conn = null;
	    try {
	        String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
	        // connecte la bdd
	        conn = DriverManager.getConnection(url);
	        Statement st = conn.createStatement(); 	        
	        st.executeUpdate("INSERT INTO College " + 
	            //"VALUES ("+id+","+idAd+",'"+nom+"',"+num_ac+")"); 
	          "VALUES (1+(SELECT MAX (id) FROM College ),"+idAd+",'"+nom+"',"+num_ac+")"); 
	       
	    } catch (SQLException e1) {
	        System.out.println(e1.getMessage());
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }   
	    }
	}
	
	
	public int getidBDD(){
        Connection conn = null;
try {
    String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
    //connect la bdd
    conn = DriverManager.getConnection(url);
    String query = "SELECT * FROM College WHERE nom='";
    query =query+this.nom +"'";

    System.out.println(query);

            Statement state = MyConnection.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            int i = result.getInt("id");
            state.close();
            return i;

} catch (SQLException e1) {
    System.out.println(e1.getMessage());
    return (0);
} finally {
    try {
        if (conn != null) {
            conn.close();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

}
	

}
