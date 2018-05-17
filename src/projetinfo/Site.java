package projetinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Site {
	
	//attributs
	private String adresse; //adresse mail du site du collège
	
	
	//accesseur et mutateurs
	private String getAdresse() {
		return adresse;
	}
	private void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public Site(String adresse){
		this.adresse = adresse;
	}
	
	//methodes
	
	 //ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
    //recuperer l'id de l'étudiant dont on veut voir les notes
    public int getetBDD(String id){
        Connection conn = null;
try {
    String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
    //connecte la bdd
    conn = DriverManager.getConnection(url);
    String query = "SELECT ideleve FROM Code WHERE identifiant=";
    query =query+id;
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
    //........................................................................
    //renvoie l'identifiant du college à partir de l'id de l'élève 
    public int getidcollegeBDD(int id){
        Connection conn = null;
try {
    String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
    //connecte la bdd
    conn = DriverManager.getConnection(url);
    String query = "SELECT idCol FROM Etudiant WHERE id=";
    query =query+id;
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
    
    
    //.................................................................................
    
    public String getmdpBDD(int ideleve){
        Connection conn = null;
try {
    String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
    //connecte la bdd
    conn = DriverManager.getConnection(url);
    String query = "SELECT mdp FROM Eleve WHERE id = ";
    query =query+ideleve;
    System.out.println(query);

            Statement state = MyConnection.getinstance().createStatement();

            ResultSet result = state.executeQuery(query);
            String i = result.getString("mdp");
            state.close();
            return i;

} catch (SQLException e1) {
    System.out.println(e1.getMessage());
    return "erreur";
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
     //ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
	
	/**
	 * methode permettant de consulter la salle
	 */
	public String voirNote(){//..................................................................................
		//à completer
		Scanner scan = new Scanner(System.in);
		System.out.print("identifiant : "); 
		String ide = scan.nextLine(); // on récupère la commande entrée par l'utilisateur
		System.out.print("mot de passe : "); 
		String mdp = scan.nextLine();
		//mdp = mdp.toLowerCase(); // on passe tout en minuscule (réduit le risque de faute de frappes)
		System.out.print("matière : "); 
		String matiere = scan.nextLine();
		
		int ideleve = getetBDD(ide);
		String password = getmdpBDD(ideleve);
		 
		if (mdp!=password){
			return "mot de passe incorrect";
		}
		
		Connection conn = null;
		try {
		    String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db";
		    //connecte la bdd
		    conn = DriverManager.getConnection(url);
		    String query = "SELECT valeur FROM Note WHERE idEt = ";
		    query =query+ideleve;
		    System.out.println(query);

		            Statement state = MyConnection.getinstance().createStatement();

		            ResultSet result = state.executeQuery(query);
		            String note = result.getString("valeur");
		            
		            state.close();
		            return "Note de"+ matiere + " : "+ note;

		} catch (SQLException e1) {
		    System.out.println(e1.getMessage());
		    return "erreur";
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
	
	public String voirSalle(){//...................................................................................
		
		Scanner scan = new Scanner(System.in);
		System.out.print("identifiant : "); 
		String ide = scan.nextLine(); // on récupère la commande entrée par l'utilisateur
		System.out.print("mot de passe : "); 
		String mdp = scan.nextLine();
		//mdp = mdp.toLowerCase(); // on passe tout en minuscule (réduit le risque de faute de frappes)
		System.out.print("matière : "); 
		String matiere = scan.nextLine();
		
		int idmatiere = matiere.getidBDD();
	
		 Connection conn = null;
		        try {
		            String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db"; //adresse de la base
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);
		            
		            int ideleve = getetBDD(ide);
		    		String password = getmdpBDD(ideleve);
		    		 
		    		if (mdp!=password){
		    			return "mot de passe incorrect";
		    		}

		            String query = "SELECT id FROM Salle WHERE idmatiere =";
		            query =query+idmatiere;
		            
		            System.out.println(query);

		                    Statement state = MyConnection.getinstance().createStatement();

		                    ResultSet result = state.executeQuery(query);
		                    int i = result.getInt("id");
		                    String salle = Integer.toString(i);
		                    state.close();
		                    return salle;

		        } catch (SQLException e1) {
		            System.out.println(e1.getMessage());
		            return "erreur";
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
		                    




	
	
	
	public String voirMail(){

		Scanner scan = new Scanner(System.in);
		System.out.print("identifiant : "); 
		String ide = scan.nextLine(); // on récupère la commande entrée par l'utilisateur
		System.out.print("mot de passe : "); 
		String mdp = scan.nextLine();
		//mdp = mdp.toLowerCase(); // on passe tout en minuscule (réduit le risque de faute de frappes)
		System.out.print("nom du professeur : "); 
		String prof = scan.nextLine();
		
		
		 Connection conn = null;
		        try {
		            String url = "jdbc:sqlite:/home/prof/workspace/projet_java/bdd.db"; //adresse de la base
		            // create a connection to the database
		            conn = DriverManager.getConnection(url);
		            
		            int ideleve = getetBDD(ide);
		    		String password = getmdpBDD(ideleve);
		    		 
		    		if (mdp!=password){
		    			return "mot de passe incorrect";
		    		}
	

		            String query = "SELECT mail FROM Enseignant WHERE nom =";
		            query =query+prof;
		            
		            System.out.println(query);

		                    Statement state = MyConnection.getinstance().createStatement();

		                    ResultSet result = state.executeQuery(query);
		                    String mail = result.getString("mail");
		                    state.close();
		                    return mail;

		        } catch (SQLException e1) {
		            System.out.println(e1.getMessage());
		            return "erreur";
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
		        
		        
		       
	
	
	
	
	
	
	
	public static void main(String[] args){
		Etudiant eleve = new Etudiant();//................
		String note = eleve.voirNote();
		System.out.println(note);
		
	}
	

}