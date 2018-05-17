
package projetinfo;

//imports 
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Enseignant {
	//attributs
	private final String nom;
	private final String prenom;	//on considère que le nom et le prénom ne peuvent changer
	private Adresse adresse;
	private String telephone;
	private final String mail;	//on considère que le mail ne peut pas changer
	private String date;
	private int identifiant; 		//identifiant du professeur
	private Matiere matiere;	//matière que le professeur enseigne
	private College collPrinc;	//college principal d'affectaton du professeur
	private College collSec;	//college secondaire d'affectaton du professeur (optionnel)
	
	
	//Accesseurs et mutateurs
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIndice(int identifiant) {
		this.identifiant = identifiant;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public College getCollPrinc() {
		return collPrinc;
	}
	public void setCollPrinc(College collPrinc) {
		this.collPrinc = collPrinc;
	}
	public College getCollSec() {
		return collSec;
	}
	public void setCollSec(College collSec) {
		this.collSec = collSec;
	}
	
	
	//constructeurs
	
	public Enseignant(String nom, String prenom,College CollPrinc, College CollSec, Adresse adresse,  String mail) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.date = date;//????
		this.identifiant = identifiant;
		this.matiere = matiere;
		this.collPrinc = collPrinc;
		this.collSec = collSec;
		
		int idCollPrinc = CollPrinc.getidBDD();
		int idCollSec = CollSec.getidBDD();
		int idAd = adresse.getidBDD();

		//constructeur particulier : crée le nouvel enseignant dans la base de donnée
		Connection conn = null;
	    try {
	        String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
	        // connecte la bdd
	        conn = DriverManager.getConnection(url);
	        Statement st = conn.createStatement(); 
	        st.executeUpdate("INSERT INTO Enseignant " + 
	            "VALUES (1+(SELECT MAX (id) FROM Enseignant),'"+prenom+"','"+nom+"','"+idCollPrinc+","+idCollSec+","+idAd+",'"+mail+"')"); 

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
	//revoie l'id matière d'une matiere d'un collège
	public int getidmatiere(String matiere, int idcollege){
		Connection conn = null;
		try {
		    String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
		    //connecte la bdd
		    conn = DriverManager.getConnection(url);
		    String query = "SELECT M.id FROM Matiere M JOIN Departement D ON D.id = M.iddepartement WHERE idCollege = ";
		    query =query+idcollege+" AND nom = " + matiere;
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
	
	
	
	
	public int getidBDD (){
        Connection conn = null;
        try {
        	// db parameters
        	String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
        	//create a connection to the database
        	conn = DriverManager.getConnection(url);

        	int col = this.getCollPrinc().getidBDD();
        	String query = "SELECT * FROM Enseignant WHERE nom = '";
        	query =query+this.getNom()+"' AND prenom ='"+this.getPrenom()+"' AND idCol ="+ col;
       


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

		
		public void demenager(Adresse adresse){
			setAdresse(adresse);
		}
	
	/**
	 * écrit les éléments constitutifs de la fiche de l'enseignant dans un fichier
	 */
	public void fiche(){
        String filePath = "//home/prof/Images/"+this.identifiant+".txt";
        Path logFile = Paths.get(filePath);
        if (!Files.exists(logFile)) {
                try {
                        Files.createFile(logFile);
        }
                catch (IOException e) {
                        e.printStackTrace();
        } }

        try (BufferedWriter writer =
Files.newBufferedWriter(logFile,StandardCharsets.UTF_8,
StandardOpenOption.WRITE)) {
                writer.write(this.nom+" "+this.prenom+" / "+this.mail);
                        writer.close();
                }
        catch (IOException e) {
                        e.printStackTrace();
}


}
	
	/**
	 * permet de changer le collège d'affectation d'un professeur 
	 * @param college
	 * @param caractere
	 */
	public void changerCollege(College college, Caractere caractere){
		if (caractere.toString() == "principal"){
			setCollPrinc(college);	
		}
		setCollSec(college);
	}
	
	public void quitterCollege(){
		
	}
	
	
   
   // /**
     //* @param args the command line arguments
     //*/
    public static void main(String[] args) {
    	Adresse adresse = new Adresse(450.21f,54.165f,6,"rue du metagabro","Ekaterinbourg",78500);
    	Adresse adressebis = new Adresse(562.52f,62.25f,5,"place de la sylphide","irkoutsk",95100);
    	College collPrinc = new College(adresse,"Ormesson4", 63);
    	College collSec = new College(adressebis,"Leroygouran4",8);
    	//Enseignant enseignant = new Enseignant(4,"yvesbis", "piolet",collPrinc,collSec,adresse,"mail");
    	//enseignant.fiche();
    }

	

}