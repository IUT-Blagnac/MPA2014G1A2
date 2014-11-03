import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;
import junit.framework.TestSuite;


/***
 * Programme de test du programme MakeOPTIweb. Il a pour but de :<br/>
 *    - générer le fichier OPTIweb.html en lancer le programme MakeOPTIweb<br/>
 *    - tester que le fichier à bien été créé<br/>
 *    - tester que le fichier créé à la structure demandée et expliquée dans la documentation technique (partie entete, acceuil, projet, sujet, etudiant, intervenant, credits et fin du fichier HTML)<br/>
 * <br/>
 * Les seules valeurs non testées par ce programme de test sont les valeurs obtenues à partir des fichiers CSV.<br/>
 */

public class MakeOPTIwebTest extends TestCase {
	  Process executionProgrammeATester ;
	  BufferedReader ecranProgrammeATester ;
	  static String programmeATester = "MakeOPTIweb" ;
	  String[] finalStr;

	  String finDeLigne = System.getProperty("line.separator") ;

	  public static void main(String[] args) {
	    if ( args.length > 0 ) { programmeATester = args[0] ; }
	  	
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("java -cp . MakeOPTIweb");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long actTemps = System.currentTimeMillis();
		while(System.currentTimeMillis()<actTemps+1500){}
		
	    System.out.println("Tests du programme : " + programmeATester);
	    junit.textui.TestRunner.run(new TestSuite(MakeOPTIwebTest.class));
	    
	  }


	  protected void setUp() throws IOException {
			
		  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("OPTIweb.html"), "utf-8"));
			
		  String line = null;
		  StringBuilder sb = new StringBuilder();
		  String lineSeparator = System.getProperty("line.separator");
		
		  try {
			  while((line = br.readLine()) != null){
				  sb.append(line);
				  sb.append(lineSeparator);
			  }
			
			  finalStr =  (sb.toString()).split(lineSeparator);
			
		  } catch (IOException e) {
			  // TODO Auto-generated catch block
			  throw e;
		  }
		  br.close();
		  
	  }
	  
	  public void testFichierCree() {
		  
		 File file = new File("OPTIweb.html");
		 if(file.exists()){
			 System.out.println("Verification fichier créé --> PASSE");
			 assertTrue(true);
		 }
		 else{
			 System.out.println("Verification fichier créé --> NON PASSE");
			 assertFalse(true);
		 }
		  
	  }
	  
	  public void testStructureEnteteCree() {
		  
		  if(finalStr[0].equals("<!DOCTYPE html>"
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
				"</head><body>")){
			  
			  System.out.println("Verification structure entete HTML créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification structure entete HTML créé --> NON PASSE");
			  assertFalse(true);
		  }
	  }
	  
	  public void testStructureAcceuilCree() {
		  
		  if(finalStr[1].equals("<!-- DEBUT page accueil -->" +
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
					"<!-- FIN page accueil -->")){
			  
			  System.out.println("Verification structure HTML acceuil créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification structure HTML acceuil créé --> NON PASSE");
			  assertFalse(true);
		  }
	  }
	  
	  public void testStructureProjetCree() {
		  
		  int open=-1;
		  int close=-1;
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("<!-- DEBUT page projets -->" +
						"<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">" +
						"<div data-role=\"header\" data-add-back-btn=\"true\">" +
						"<h1>Projets 2014-2015</h1>" +
						"</div>" +
						"<div data-role=\"content\">" +
						"<form class=\"ui-filterable\">" +
						"<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\">   <!-- 1 -->" +
						"</form>" +
						"<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">")){
				  open = i;
			  }
		  }
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("</ol>" +
						"</div>" +
						"<div data-role=\"footer\">" +
						"<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>" +
						"</div>" +
						"</div>" +
						"<!-- FIN page projets -->")){
				  close = i;
			  }
		  }
		  
		  if(open!=-1 && close !=-1){
			  System.out.println("Verification structure HTML projet créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification structure HTML projet créé --> NON PASSE");
			  assertFalse(true);
		  }
		  
	  }

	  public void testStructureSujetCree() {
		  
		  int open=-1;
		  int close=-1;
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("<!-- DEBUT page sujets -->\""
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
						+ "</li>")){
				  open = i;
			  }
		  }
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("</ol>"
						+ "</div>"
						+ "<div data-role=\"footer\">"
						+ "<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>"
						+ "</div>"
						+ "</div>"
						+ "<!-- FIN page sujets -->\"")){
				  close = i;
			  }
		  }
		  
		  if(open!=-1 && close !=-1){
			  System.out.println("Verification structure HTML sujet créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification structure HTML sujet créé --> NON PASSE");
			  assertFalse(true);
		  }
		  
	  }

	  public void testStructureEtudiantCree() {
		  
		  int open=-1;
		  int close=-1;
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("<!-- DEBUT page etudiants -->"
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
						+ "</li>")){
				  open = i;
			  }
		  }
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("</ol>"
						+ "</div>"
						+ "<div data-role=\"footer\">"
						+ "<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>"
						+ "</div>"
						+ "</div>"
						+ "<!-- FIN page etudiants -->")){
				  close = i;
			  }
		  }
		  
		  if(open!=-1 && close !=-1){
			  System.out.println("Verification structure HTML etudiant créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification structure HTML etudiant créé --> NON PASSE");
			  assertFalse(true);
		  }
		  
	  }
	  
	  public void testStructureIntervenantCree() {
		  
		  int open=-1;
		  int close=-1;
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("<!-- DEBUT page intervenants -->"
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
						+ "</li>")){
				  open = i;
			  }
		  }
		  
		  for(int i=0; i<finalStr.length; i++){
			  if(finalStr[i].equals("</ul>"
						+ "</div>"
						+ "<div data-role=\"footer\">"
						+ "<h4>OPTIweb <span class=\"landscape\">Version </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>"
						+ "</div>"
						+ "</div>"
						+ "<!-- FIN page intervenants -->")){
				  close = i;
			  }
		  }
		  
		  if(open!=-1 && close !=-1){
			  System.out.println("Verification structure HTML intervenant créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification structure HTML intervenant créé --> NON PASSE");
			  assertFalse(true);
		  }
		  
	  }
	  
	  public void testStructureCreditsCree() {
		  
		  if(finalStr[finalStr.length-2].equals("<!-- DEBUT page credits -->"
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
					+ "<!-- FIN page credits -->")){
			  
			  System.out.println("Verification structure HTML credits créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification structure HTML credits créé --> NON PASSE");
			  assertFalse(true);
		  }
	  }
	  
	  public void testStructureFinHTMLCree() {
		  
		  if(finalStr[finalStr.length-1].equals("<script>" +
					"$( 'li[data-find]' ).on( 'click',function(event){" +
					"$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');" +
					"});" +
					"</script>" +
					"</body>" +
					"</html>")){
			  
			  System.out.println("Verification fin structure HTML créé --> PASSE");
			  assertTrue(true);
		  }
		  else{
			  System.out.println("Verification fin structure HTML créé --> NON PASSE");
			  assertFalse(true);
		  }
	  }
	
}