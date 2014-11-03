import java.util.ArrayList;



/**
 * Represente l'association Projet-Intervenant, spécifiant pour l'intervenant son rôle
 * @author Groupe 1A2
 *
 */
public class Encadrer {
	
	Intervenant intervenant;
	Projet projet;
	Role role;
	
	/***
	 * Retourne l'intervenant associé
	 * @return Objet Intervenant associé
	 */
	public Intervenant getIntervenant() {
		return intervenant;
	}

	/***
	 * Permet de recuperer le projet associé
	 * @return Objet Projet associé
	 */
	public Projet getProjet() {
		return projet;
	}

	/***
	 * Permet de recuperer le role associé à l'intervenant
	 * @return Valeur du role
	 */
	public Role getRole() {
		return role;
	}
	
	
	/**
	 * Constructeur d'un objet Encadrer.
	 * @param intervenant Objet Intervenant representant l'intervenant encadrant le projet.
	 * @param projet Objet Projet representant le projet étant encadré.
	 * @param role Valeur de l'énumeration Role, représente le rôle de l'intervenant affecté.
	 */
	public Encadrer(Intervenant intervenant, Projet projet, Role role) {
		super();
		this.intervenant = intervenant;
		this.projet = projet;
		this.role = role;
	}
	
	/***
	 * Permet de recuperer un objet Intervenant à partir du projet et du rôle de celui-ci.
	 * @param dbEnc ArrayList d'objet Encadrer dans laquelle on cherche l'intervenant.
	 * @param projet Projet, critère de recherche
	 * @param role Role, critère de recherche
	 * @return intervenant recherché
	 */
	public static Intervenant getInterFromProjetRole(ArrayList<Encadrer> dbEnc, Projet projet, Role role){
		
		for(Encadrer encadrer : dbEnc){
			
			if(encadrer.getProjet()==projet && encadrer.getRole()==role){
				return encadrer.getIntervenant();
			}
			
		}
		return null;
		
	}

}
