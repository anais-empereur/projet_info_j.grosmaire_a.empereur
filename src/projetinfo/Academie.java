
package projetinfo;

import java.util.Scanner;

public class Academie {

	public void interpreteur(){
		Scanner scan = new Scanner(System.in);
		System.out.print("que voulez vous faire : "); 
		System.out.print("vous connecter au site (taper a)");
		System.out.print("calculer une distance (taper b)");
		System.out.print("gerer la BDD (taper c) : ");
		String choix = scan.nextLine(); // on récupère la commande entrée par l'utilisateur
		scan.close();
		
		if (choix=="a"){// si l'utilisateur veut se connecter au site
			System.out.print("entrez le nom de votre collège : ");
			String college = scan.nextLine();
			int idcollege = college.getidBDD();
			String adresse = "test";
			Site site = new Site(adresse);
			
			
			System.out.print("Vous désirez : ");
			System.out.print("consulter vos notes (taper a) :");
			System.out.print("obtenir le mail d'un professeur (taper b) :");
			System.out.print("voir une salle (taper c) : ");
			String clic = scan.nextLine();
			scan.close();
			
			if (clic=="a"){
				site.voirNote();
			}
			if (clic=="b"){
				site.voirMail();
			}
			if (clic=="c"){
				site.voirSalle();
			}
			
			
			
			
		}
		if (choix=="b"){// si l'utilisateur veux calculer une distance
			System.out.print("saisir l'identifiant de l'enseignant pour lequel vous vouler calculer une distance : ");
			String prof = scan.nextLine();
			System.out.print("saisir l'affectation du collège pour lequel vous vouler calculer une distance : ");
			System.out.print("principal (taper a) :");
			System.out.print("secondaire (taper b) :");
			String affectation = scan.nextLine();
			scan.close();
			
			//rajouter une fonction dans enseignant : récupère Adresse adresse à partir de idprof
			Adresse adresse = prof.getadresse() ;
			
			if (affectation=="a"){
				;
			}
			if (affectation=="b"){
				;
		
		
		}
		if (choix=="c"){//si l'utilisateur veux gerer la base de donnée
			;
		}
		
		
		
		//mdp = mdp.toLowerCase(); // on passe tout en minuscule (réduit le risque de faute de frappes)
		System.out.print("nom du professeur : "); 
		String prof = scan.nextLine();
		scan.close();
	}
	}
	
	public static void main(String[] args) {
		
		
		
	}
}
