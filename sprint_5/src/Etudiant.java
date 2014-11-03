

/**
 * Represente un étudiant, pouvant être assigné à un groupe et défini par son identifiant, son nom et prénom
 * @author Groupe 1A2
 *
 */
public class Etudiant {
	
	private int id;
	private String nom;
	private String prenom;
	private Groupe groupe;
	
	/***
	 * Retourne l'identifiant de l'étudiant
	 * @return entier : identifiant
	 */
	public int getId(){
		return id;
	}
	
	/***
	 * Retourne le nom de l'étudiant
	 * @return String
	 */
	public String getNom() {
		return nom;
	}
	/***
	 * Retourne le prenom de l'étudiant
	 * @return String
	 */
	public String getPrenom() {
		return prenom;
	}
	/***
	 * Retourne le groupe de l'étudiant
	 * @return Objet Groupe
	 */
	public Groupe getGroupe() {
		return groupe;
	}
	
	/***
	 * Constructeur de l'objet Etudiant
	 * @param id identifiant de l'etudiant
	 * @param nom nom de l'étudiant
	 * @param prenom prenom de l'étudiant
	 */
	public Etudiant(int id,String nom, String prenom){
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.groupe = null;
	}
	
	/***
	 * Constructeur de l'objet Etudiant (mais affecté à un groupe)
	 * @param id identifiant de l'etudiant
	 * @param nom nom de l'étudiant
	 * @param prenom prenom de l'étudiant
	 * @param groupe groupe de l'étudiant
	 */
	public Etudiant(int id,String nom, String prenom, Groupe groupe) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.groupe = groupe;
	}

}
