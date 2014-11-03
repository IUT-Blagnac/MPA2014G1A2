import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;


/**
 * Librairie fournissant diverses fonctions pour manipuler des fichiers CSV
 * @author Groupe1A2
 *
 */
public class LibCSV {
	
	/***
	 * 
	 * @param file Objet de type File pointant le fichier etudiants.csv
	 * @param dbGroupe ArrayList de Groupe representant la base de données de Groupes prealablement remplie
	 * @return ArrayList d'Etudiants representant la base de données d'Etudiant remplie
	 * @throws Exception
	 */
	public static ArrayList<Etudiant> importEtuFromCSV(File file, ArrayList<Groupe> dbGroupe) throws Exception{
		
		String stringCSV = getStrFromCSVFile(file);
		if(!stringCSV.equals("")){
			ArrayList<Etudiant> etuList = getEtuFromStr(stringCSV,dbGroupe);
			return etuList;
		}
		else{
			return null;
		}

		
	}
	
	private static ArrayList<Etudiant> getEtuFromStr(String str, ArrayList<Groupe> dbGroupes) throws Exception{
		String strLine[];
		strLine = str.split(System.getProperty("line.separator"));
		
		ArrayList<Etudiant> etuList = new ArrayList<Etudiant>();
		try{
		String strDataSplit[];
		for(int i=1; i<strLine.length; i++){
			
				strDataSplit = strLine[i].split(";");

				if(strDataSplit.length!=4){
					throw new Error();
				}
				if(!strDataSplit[0].equals("")){
					if(Groupe.getGroupFromId(dbGroupes, strDataSplit[0])==null){
						dbGroupes.add(new Groupe(strDataSplit[0], new ArrayList<Etudiant>()));
					}
					Etudiant etu = new Etudiant(Integer.parseInt(strDataSplit[1]), strDataSplit[2], 
										strDataSplit[3], 
										Groupe.getGroupFromId(dbGroupes, strDataSplit[0]));
					etuList.add(etu);
					Groupe.getGroupFromId(dbGroupes, strDataSplit[0]).getMembres().add(etu);
				}
				else{
					etuList.add(new Etudiant(Integer.parseInt(strDataSplit[1]), strDataSplit[2], strDataSplit[3]));
				}

		}
		}catch(Exception e){}
		
		boolean trie = false;
		
		while(!trie){
			trie=true;
			
			for(int i=1;i<etuList.size();i++){
				if(etuList.get(i).getNom().compareTo(etuList.get(i-1).getNom())<0){
					Etudiant temp = etuList.get(i-1);
					etuList.set(i-1, etuList.get(i));
					etuList.set(i, temp);
					trie = false;
				}
			}
			
		}
		
		return etuList;
		
	}

	
	/***
	 * 
	 * @param file Objet de type File pointant le fichier intervenants.csv
	 * @return ArrayList d'Intervenants representant la base de données d'Intervenants remplie
	 * @throws Exception
	 */
	public static ArrayList<Intervenant> importIntervenantFromCSV(File file) throws Exception{
		
		String stringCSV = getStrFromCSVFile(file);
		if(!stringCSV.equals("")){
			ArrayList<Intervenant> interList = getInterFromStr(stringCSV);
			return interList;
		}
		else{
			return null;
		}

		
	}
	
	private static ArrayList<Intervenant> getInterFromStr(String str) throws Exception{
		String strLine[];
		strLine = str.split(System.getProperty("line.separator"));
		
		ArrayList<Intervenant> interList = new ArrayList<Intervenant>();
		try{
		String strDataSplit[];
		for(int i=1; i<strLine.length; i++){
			
			strDataSplit = strLine[i].split(";");

				if(strDataSplit.length!=3){
					throw new Error();
				}
				if(strDataSplit.length==3){
					interList.add(new Intervenant(Integer.parseInt(strDataSplit[0]),strDataSplit[2], 
										strDataSplit[1]));
				}

		}
		}catch(Exception e){}
		
		boolean trie = false;
		
		while(!trie){
			trie=true;
			
			for(int i=1;i<interList.size();i++){
				if(interList.get(i).getNom().compareTo(interList.get(i-1).getNom())<0){
					Intervenant temp = interList.get(i-1);
					interList.set(i-1, interList.get(i));
					interList.set(i, temp);
					trie = false;
				}
			}
			
		}
		
		return interList;
		
	}
	
	
	/***
	 * 
	 * @param file Objet de type File pointant le fichier etudiants.csv
	 * @param dbGroupe ArrayList de Groupe representant la base de données de Groupes prealablement remplie
	 * @return ArrayList d'Etudiants representant la base de données d'Etudiant remplie
	 * @throws Exception
	 */
	
	/***
	 * 
	 * @param file Objet de type File pointant le fichier projets.csv
	 * @param dbGroupe ArrayList de Groupe representant la base de données de Groupes prealablement remplie
	 * @param dbSujets ArrayList de Sujet representant la base de données de Sujets prealablement remplie
	 * @param dbInter ArrayList d'Intervenant representant la base de données d'intervenants prealablement remplie
	 * @param dbEncadrer ArrayList d'association Encadrer representant la base de données des assiciations de ce type prealablement remplie
	 * @return ArrayList de Projets representant la base de données de Projets remplie
	 * @throws Exception
	 */
	public static ArrayList<Projet> importProjetFromCSV(File file, ArrayList<Groupe> dbGroupe, ArrayList<Sujet> dbSujets, ArrayList<Intervenant> dbInter, ArrayList<Encadrer> dbEncadrer) throws Exception{

		String stringCSV = getStrFromCSVFile(file);
		if(!stringCSV.equals("")){
			ArrayList<Projet> projetList = getProjetFromStr(stringCSV, dbGroupe, dbSujets, dbInter, dbEncadrer);
			return projetList;
		}
		else{
			return null;
		}

		
	}
	
	private static ArrayList<Projet> getProjetFromStr(String str, ArrayList<Groupe> dbGroupe, ArrayList<Sujet> dbSujets, ArrayList<Intervenant> dbInter, ArrayList<Encadrer> dbEncadrer) throws Exception{

		String strLine[];
		strLine = str.split(System.getProperty("line.separator"));

		ArrayList<Projet> projetList = new ArrayList<Projet>();
		String strDataSplit[];
		for(int i=1; i<strLine.length; i++){

			strDataSplit = strLine[i].split(";");

			if(strDataSplit.length!=6 && strDataSplit.length!=5){
				throw new Error();
			}
			
			Projet prjt = new Projet(Integer.parseInt(strDataSplit[0]),Groupe.getGroupFromId(dbGroupe, strDataSplit[1]),Sujet.getSujetFromID(dbSujets, Integer.parseInt(strDataSplit[2])));

			projetList.add(prjt);

			dbEncadrer.add(new Encadrer(Intervenant.getInterFromId(dbInter, Integer.parseInt(strDataSplit[3])), prjt, Role.Client));
			dbEncadrer.add(new Encadrer(Intervenant.getInterFromId(dbInter, Integer.parseInt(strDataSplit[4])), prjt, Role.Superviseur));
			if(strDataSplit.length==6){
				dbEncadrer.add(new Encadrer(Intervenant.getInterFromId(dbInter, Integer.parseInt(strDataSplit[5])), prjt, Role.Support_Technique));
			}

		}
		
		return projetList;
		
	}

	/***
	 * 
	 * @param file Objet de type File pointant le fichier sujets.csv
	 * @return ArrayList de Sujets representant la base de données de sujets remplie
	 * @throws Exception
	 */
	public static ArrayList<Sujet> importSujetFromCSV(File file) throws Exception{
		
		String stringCSV = getStrFromCSVFile(file);
		if(!stringCSV.equals("")){
			ArrayList<Sujet> sujetList = getSujetFromStr(stringCSV);
			return sujetList;
		}
		else{
			return null;
		}

		
	}
	
	private static ArrayList<Sujet> getSujetFromStr(String str) throws Exception{
		String strLine[];
		strLine = str.split(System.getProperty("line.separator"));
		
		ArrayList<Sujet> sujetList = new ArrayList<Sujet>();
		try{
		String strDataSplit[];
		for(int i=1; i<strLine.length; i++){

			strDataSplit = strLine[i].split(";");

				if(strDataSplit.length!=3){
					throw new Error();
				}

				sujetList.add(new Sujet(Integer.parseInt(strDataSplit[0]), 
									strDataSplit[1], 
									strDataSplit[2]));
		}
		}catch(Exception e){}
		
		return sujetList;
		
	}
	

	
	private static String getStrFromCSVFile(File file) throws Exception{
		
		String finalStr = "";
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			String lineSeparator = System.getProperty("line.separator");
			
			try {
				while((line = br.readLine()) != null){
					
					sb.append(line);
					sb.append(lineSeparator);
					
				}
				
			finalStr =  sb.toString();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw e;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		throw e;
		}


		
		return finalStr;

	}
	



	
}
