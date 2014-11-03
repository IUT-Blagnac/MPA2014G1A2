import java.util.ArrayList;



/**
 * Represente un projet, permettant d'associer un groupe à un sujet et défini par un identifiant.
 * @author Groupe1A2
 *
 */
public class Projet {
	
	int id;
	Groupe groupe;
	Sujet sujet;
	
	/***
	 * Retourne l'identifiant du projet
	 * @return entier : identifiant
	 */
	public int getId(){
		return id;
	}
	/***
	 * Retourne le groupe associé au projet
	 * @return Groupe
	 */
	public Groupe getGroupe() {
		return groupe;
	}
	
	/***
	 * Retourne le sujet associé au projet
	 * @return Sujet
	 */
	public Sujet getSujet() {
		return sujet;
	}

	/***
	 * Constructeur de l'objet Projet
	 * @param id identifiant du projet
	 * @param groupe groupe du projet
	 * @param sujet sujet du projet
	 */
	public Projet(int id,Groupe groupe, Sujet sujet) {
		super();
		this.id=id;
		this.groupe = groupe;
		this.sujet = sujet;
	}

}
