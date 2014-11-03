import java.util.ArrayList;



/**
 * Represente un sujet, défini par un identifiant et contenant le nom et le titre du sujet.
 * @author Groupe1A2
 *
 */
public class Sujet {

	int id;
	String nom;
	String titre;
	public int getId() {
		return id;
	}
	/***
	 * Retourne le nom du projet
	 * @return nom du projet
	 */
	public String getNom() {
		return nom;
	}
	/***
	 * Retourne le titre du projet
	 * @return titre du projet
	 */
	public String getTitre() {
		return titre;
	}

	/***
	 * Constructeur de l'objet Sujet
	 * @param id identifiant du sujet
	 * @param nom nom du sujet
	 * @param titre titre du sujet
	 */
	public Sujet(int id, String nom, String titre) {
		super();
		this.id = id;
		this.nom = nom;
		this.titre = titre;
	}
	
	/***
	 * Permet de recuperer un sujet à partir de son identifiant
	 * @param dbSujets ArrayList de sujet dans laquelle le sujet est cherché
	 * @param id identifiant du sujet
	 * @return sujet recherché
	 */
	public static Sujet getSujetFromID(ArrayList<Sujet> dbSujets, int id){
		
		for(Sujet sjt : dbSujets){
			if(sjt.getId()==id){
				return sjt;
			}
		}
		return null;
		
	}
	
}
