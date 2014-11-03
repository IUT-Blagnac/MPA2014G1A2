import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Permet de generer l'application web OPTIweb à partir de fichiers CSV<br/>
 * -Tout les fichiers CSV doivent être placé dans un répertoire nommé data, placé à coté du créateur d'HTML (MakeOPTIweb.class ou MakeOPTIweb.jar)<br/>
 * -Les fichier suivants doivent être présent dans le repertoire data:<br/>
 * --etudiants.csv<br/>
 * --intervenants.csv<br/>
 * --sujets.csv<br/>
 * --projets.csv<br/>
 * @author Groupe1A2
 *
 */
public class MakeOPTIweb {
	
	public static void main(String[] args) throws Exception{
		
		try{
		
			ArrayList<Groupe> dbGroupes = new ArrayList<Groupe>();
			ArrayList<Etudiant> dbEtudiants = LibCSV.importEtuFromCSV(new File("data/etudiants.csv"), dbGroupes);
			ArrayList<Intervenant> dbIntervenants = LibCSV.importIntervenantFromCSV(new File("data/intervenants.csv"));
			ArrayList<Sujet> dbSujets = LibCSV.importSujetFromCSV(new File("data/sujets.csv"));
			ArrayList<Encadrer> dbEncadrer = new ArrayList<Encadrer>();
			ArrayList<Projet> dbProjets = LibCSV.importProjetFromCSV(new File("data/projets.csv"), dbGroupes, dbSujets, dbIntervenants, dbEncadrer);
			
			String strHTML = genererEntete();
			
			strHTML += genererPageAcceuil();
			
			strHTML += genererPageProjets(dbProjets, dbEncadrer);
			
			strHTML += genererPageSujets(dbSujets, dbProjets);
			
			strHTML += genererPageEtudiants(dbEtudiants);
			
			strHTML += genererPageIntervenants(dbIntervenants, dbEncadrer);
			
			strHTML += genererPageCredits();
			
			strHTML += genererFinHTML();
			
			
			PrintWriter fw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OPTIweb.html"),"utf8")));
			
			fw.print(strHTML);
			
			fw.close();
			
		}catch (Exception e){
			if(e instanceof FileNotFoundException){
				System.out.println(e.getMessage());
				System.out.println("Les fichier requis sont introuvables.\n"
						+ "N'oubliez pas de placer les fichiers etudiants.csv, intervenants.csv, sujets.csv, et projets.csv dans un repertoire data adjacent au lanceur.");
			}
		}
		
	}
	
	
	private static String genererEntete(){
		
		//Ajout de l'entete
		return("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
				"<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />" +
				"<title>0.1 - V0.1</title>" +
				"<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />" +
				"<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />" +
				"<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>    <!-- 3 -->" +
				"<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>" +
				"<style type='text/css'>" +
				"@media all and (orientation:portrait) { .landscape {display: none;} }" +
				"@media all and (orientation:landscape) { .landscape {display: inline;} }" +
				"</style>" +
				"</head><body>\n");
		
	}
	
	private static String genererPageAcceuil(){
		
		return("<!-- DEBUT page accueil -->" +
				"<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">" +
				"<div data-role=\"header\" data-add-back-btn=\"true\">" +
				"<h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>" +
				"<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>   <!-- 1 -->" +
				"</div>" +
				"<div data-role=\"content\">" +
				"<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">" +
				"<li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>   <!-- 1 -->" +
				"<li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>   <!-- 1 -->" +
				"<li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>   <!-- 1 -->" +
				"<li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>   <!-- 1 -->" +
				"</ul>" +
				"</div>" +
				"<div data-role=\"footer\">" +
				" <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>" +
				"</div>" +
				"</div>" +
				"<!-- FIN page accueil -->\n");
		
	}

	private static String genererPageProjets(ArrayList<Projet> dbProjet,ArrayList<Encadrer> dbEnc){
		
		String str =("<!-- DEBUT page projets -->" +
				"<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">" +
				"<div data-role=\"header\" data-add-back-btn=\"true\">" +
				"<h1>Projets 2014-2015</h1>" +
				"</div>" +
				"<div data-role=\"content\">" +
				"<form class=\"ui-filterable\">" +
				"<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\">   <!-- 1 -->" +
				"</form>" +
				"<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">\n");

		
		
		for(Projet proj : dbProjet){
			
			str+="<li>" +
					"<p>" +
					"<b>["+proj.getSujet().getNom()+"]</b> "+proj.getSujet().getTitre()+"" +
					"</p>" +
					"<p>" +
					"<b>Client :</b> "+Encadrer.getInterFromProjetRole(dbEnc, proj, Role.Client).getNom()+" "+Encadrer.getInterFromProjetRole(dbEnc, proj, Role.Client).getPrenom()+"" +
					"</p>" +
					"<p>" +
					"<b>Superviseur :</b> "+Encadrer.getInterFromProjetRole(dbEnc, proj, Role.Superviseur).getNom()+" "+Encadrer.getInterFromProjetRole(dbEnc, proj, Role.Superviseur).getPrenom()+"" +
					"</p>" +
					"<p>" +
					"<b>Groupe "+proj.getGroupe().getLibelle()+" :</b>";
			
			for(Etudiant etu : proj.getGroupe().getMembres()){
				str+=" " + etu.getPrenom() + " " + etu.getNom() + " -";
			}
			
			str+="</p>" +
			"</li>\n";
			
		}
		
	str+=("</ol>" +
			"</div>" +
			"<div data-role=\"footer\">" +
			"<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>" +
			"</div>" +
			"</div>" +
			"<!-- FIN page projets -->\n");

	return str;
		
	}
	
	private static String genererPageSujets(ArrayList<Sujet> dbSujet, ArrayList<Projet> dbProjet){

		String str =("<!-- DEBUT page sujets -->\""
			+ "<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">"
			+ "<div data-role=\"header\" data-add-back-btn=\"true\">"
			+ "<h1>Sujets 2014-2015</h1>"
			+ "</div>"
			+ "<div data-role=\"content\">"
			+ "<form class=\"ui-filterable\">"
			+ "<input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\">    <!-- 2 -->"
			+ "</form>"
			+ "<ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\">"
			+ "<li data-role=\"list-divider\">    <!-- 3 -->"
			+ "Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>"
			+ "</li>\n");
		
		for(Sujet sjt : dbSujet){
			
			str+=("<li data-find=\"["+sjt.getNom()+"]\">"
					+ "<a href=\"#projets\">["+sjt.getNom()+"] <br/>"
					+ "<div style=\"white-space:normal;\">"
					+ "<span><b>"+sjt.getTitre()+"</b>"
					+ "</span><span class=\"ui-li-count\">");
			
			for(Projet pjt : dbProjet){
				if(pjt.getSujet()==sjt){
					str+=pjt.getGroupe().getLibelle()+" ";
				}
			}
			
			str+="</span>"
				+ "</div>"
				+ "</a>"
				+ "</li>\n";
			
		}
		
		str+=("</ol>"
			+ "</div>"
			+ "<div data-role=\"footer\">"
			+ "<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>"
			+ "</div>"
			+ "</div>"
			+ "<!-- FIN page sujets -->\"\n");

		return str;
		
	}
	
	private static String genererPageEtudiants(ArrayList<Etudiant> dbEtu){
		
		String str = ("<!-- DEBUT page etudiants -->"
			+ "<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">"
			+ "<div data-role=\"header\" data-add-back-btn=\"true\">    <!-- 1 -->"
			+ "<h1>Etudiants 2014-2015</h1>"
			+ "</div>"
			+ "<div data-role=\"content\">"
			+ "<form class=\"ui-filterable\">"
			+ "<input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\">    <!-- 2 -->"
			+ "</form>"
			+ "<ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\">"
			+ "<li data-role=\"list-divider\">"
			+ "Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>"
			+ "</li>\n");
		
		for(Etudiant etu : dbEtu){
			
			str+="<li data-find=\""+etu.getPrenom() + " " + etu.getNom()+"\">"
				+ "<a href=\"#projets\">"+etu.getNom()+" "+etu.getPrenom()+"<span class=\"ui-li-count\" title=\"Groupe\">Groupe "+etu.getGroupe().getLibelle()+"</span>"
				+ "</a>"
				+ "</li>\n";
			
		}

		str+=("</ol>"
			+ "</div>"
			+ "<div data-role=\"footer\">"
			+ "<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>"
			+ "</div>"
			+ "</div>"
			+ "<!-- FIN page etudiants -->\n");
		
		return str;
		
	}
	
	private static String genererPageIntervenants(ArrayList<Intervenant> dbInter, ArrayList<Encadrer> dbEnc){
		
		String str = ("<!-- DEBUT page intervenants -->"
			+ "<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">"
			+ "<div data-role=\"header\" data-add-back-btn=\"true\">"
			+ "<h1>Intervenants 2014-2015</h1>"
			+ "</div>"
			+ "<div data-role=\"content\">"
			+ "<form class=\"ui-filterable\">"
			+ "<input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\">    <!-- 2 -->"
			+ "</form>"
			+ "<ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\">"
			+ "<li data-role=\"list-divider\">    <!-- 3 -->"
			+ "Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span>"
			+ "</li>\n");

		
		for(Intervenant inter : dbInter){
			
			str+="<li data-find=\""+inter.getNom()+" "+inter.getPrenom()+"\">"
			+ "<a href=\"#projets\">"
			+ inter.getNom()+" "+inter.getPrenom();
			
			int client = 0;
			int superviseur = 0;
			
			for(Encadrer enc : dbEnc){
				if(enc.getIntervenant()==inter){
					if(enc.role==Role.Client){
						client++;
					}
					if(enc.role==Role.Superviseur){
						superviseur++;
					}
				}
			}
			
			str+="<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">"+client+"</span>"
			+ "<span class=\"ui-li-count\" title=\"Superviseur\">"+superviseur+"</span>"
			+ "</a>"
			+ "</li>\n";
			
		}
		
		str += ("</ul>"
		+ "</div>"
		+ "<div data-role=\"footer\">"
		+ "<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>"
		+ "</div>"
		+ "</div>"
		+ "<!-- FIN page intervenants -->\n");
		
		return str;
		
	}
	
	private static String genererPageCredits(){
		
		String str = ("<!-- DEBUT page credits -->"
		+ "<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Crédits\">"
		+ "<div data-role=\"header\" data-add-back-btn=\"true\">    <!-- 1 -->"
		+ "<h1>Crédits</h1>"
		+ "</div>"
		+ "<div data-role=\"content\">"
		+ "<p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p>"
		+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">"
		+ "<li data-role=\"list-divider\">Product Owner</li>"
		+ "<li>André PÉNINOU</li>"
		+ "<li>Université Toulouse 2 - IUT de Blagnac"
		+ "<br/>Département INFORMATIQUE</li>"
		+ "</ul>"
		+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">"
		+ "<li data-role=\"list-divider\">Membres de l'équipe enseignante</li>"
		+ "<li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>André PÉNINOU</li><li>Olivier ROQUES</li>"
		+ "</ul>"

		+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listeetudiant\" data-theme=\"a\" data-divider-theme=\"b\">"
		+ "<li data-role=\"list-divider\">Membres de l'équipe étudiante</li>"
		+ "<li>Adrien ANDUZE</li><li>Kristen VIGUIER</li><li>Arnauld ALEX</li><li>Guillaume FOURNET</li><li>Mael AUBERT</li>"
		+ "</ul>"

		+ "<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\">"
		+ "<li data-role=\"list-divider\">Propulsé par</li>"
		+ "<li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>"
		+ "<li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>"
		+ "</ul>"
		+ "</div>"
		+ "<div data-role=\"footer\">"
		+ "<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>"
		+ "</div>"
		+ "</div>"
		+ "<!-- FIN page credits -->\n");
		
		return str;
		
	}
	
	private static String genererFinHTML(){
		//Ajout de la fin de la page HTML
		return("<script>" +
				"$( 'li[data-find]' ).on( 'click',function(event){" +
				"$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');" +
				"});" +
				"</script>" +
				"</body>" +
				"</html>\n");
	}

}
