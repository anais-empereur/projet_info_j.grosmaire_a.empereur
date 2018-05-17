
package projetinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adresse {
	private float coordX;	//coordonnées géographiques en x d'une adresse 
	private float coordY;	//coordonnées géographiques en y d'une adresse 
	private int numero;
	private String rue;
	private String ville;
	private int codePost;
	
//Accesseurs et mutateurs
	public float getCoordX() {
		return coordX;
	}
	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}
	public float getCoordY() {
		return coordY;
	}
	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCodePost() {
		return codePost;
	}
	public void setCodePost(int codePost) {
		this.codePost = codePost;
	}

	//constructeur
	public Adresse(float coordX, float coordY, int numero, String rue, String ville, int codePost) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePost = codePost;
		
		Connection conn = null;
	    try {
	        String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
	        // connecte la bdd
	        conn = DriverManager.getConnection(url);		
	        Statement st = conn.createStatement(); 	        
	        st.executeUpdate("INSERT INTO Adresse " + 
	            "VALUES (1+(SELECT MAX (id) FROM Adresse),"+coordX+","+coordY+","+numero+",'"+ville+"','"+rue+"',"+codePost+")"); 

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
	
	//methodes
	/**
	 * methode renvoyant la distance entre deux points
	 * @param ad
	 * @return double 
	 */
	private double calcule_dist(Adresse ad){
		return Math.sqrt(Math.pow(this.coordX-ad.coordX, 2.0)+Math.pow(this.coordY-ad.coordY, 2.0));
	}
	
	
	
	public int getidBDD(){
        Connection conn = null;
try {

    String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
    //connecte la bdd
    conn = DriverManager.getConnection(url);
    String query = "SELECT * FROM Adresse WHERE coordX = ";
    query =query+this.coordX+" AND coordY = "
    		+this.coordY;
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

