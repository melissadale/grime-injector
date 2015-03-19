package injector_bridge;
/*
 * This is a beautiful class that injects Modular Grime instances
 * INPUT: the number of iterations, 2 arrays of pattern class names and non pattern class names, the number of grime instances
 * OUTPUT: write copies of project classes to a new directory for the given run directory. 
 * 
 * CREATOR: Melissa Dale
 * DATE: 17 MARCH 2013
 */

import javassist.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class InjectorLogic {
	ClassPool pool = ClassPool.getDefault();
	
	ArrayList<String> patterns = new ArrayList<String>();
	ArrayList<String> notpatterns = new ArrayList<String>();
	
	ArrayList<CtClass> injected_p = new ArrayList<CtClass>(); //arraylist of injected pattern class's bytecode
	ArrayList<CtClass> injected_n = new ArrayList<CtClass>(); //arraylist of injected not_pattern class's bytecode
	
	ArrayList<ArrayList<CtClass>> monsterArray = new ArrayList<ArrayList<CtClass>>();

	int counter, version, current_version, repetitions; //bean counters
	int peeg, teeg, teag, peag, tig, pig; //number of instances of grime to inject
	
	
/*==================================================================CONSTRUCTORS============================= */

public InjectorLogic(int versions, ArrayList<String> pattern, ArrayList<String> nonpattern, int[] grimeInst, int repeats){

	patterns = pattern; 
	notpatterns = nonpattern;
	
	current_version=1;
	version = versions;
	counter = 0;
	repetitions=repeats;

	peeg = grimeInst[0];
	teeg = grimeInst[1];
	peag = grimeInst[2];
	teag = grimeInst[3];
	pig = grimeInst[4];
	tig = grimeInst[5];
}

/*==================================================================CONSTRUCTORS============================= */

public void engage() throws Exception{
	setup();
	for(int i = 0; i<version; i++){ 
		inject();
		current_version++;
	}
}
private void setup() throws CannotCompileException, NotFoundException, IOException{
/*======================================================
 * Get classes and make copies to manipulate
 * Keep list of modified byte code
 * ALSO insert a string variable for each class for the temporary coupling.
 * =====================================================
 */	
	//make large data structure so that individual grime types may be separated into different results
	ArrayList<ArrayList<CtClass>> borg = new ArrayList<ArrayList<CtClass>>();
	ArrayList<ArrayList<CtClass>> mini = new ArrayList<ArrayList<CtClass>>();
	
	//PEEG 
	mini = initialize("peeg");
	borg.add(mini.get(0));//0
	borg.add(mini.get(1));//1
	//TEEG 
	mini = initialize("teeg");
	borg.add(mini.get(0));//0
	borg.add(mini.get(1));//1
	
	//PEAG 
	mini = initialize("peag");
	borg.add(mini.get(0));//0
	borg.add(mini.get(1));//1
	
	//TEAG 
	mini = initialize("teag");
	borg.add(mini.get(0));//0
	borg.add(mini.get(1));//1
	
	//PIG
	mini = initialize("pig");
	borg.add(mini.get(0));//0
	borg.add(mini.get(1));//1
	
	//TIG
	mini = initialize("tig");
	borg.add(mini.get(0));//0
	borg.add(mini.get(1));//1
	
	monsterArray = borg;
}
private ArrayList<ArrayList<CtClass>> initialize(String gType) throws CannotCompileException, NotFoundException, IOException{
/*======================================================
 * Get classes and make copies to manipulate
 * Keep list of modified byte code
 * ALSO insert a string variable for each class for the temporary coupling.
 * =====================================================
 */		
	ArrayList<ArrayList<CtClass>> ret_set = new ArrayList<ArrayList<CtClass>>();
	ArrayList<CtClass> copy_pattern = new ArrayList<CtClass>();
	ArrayList<CtClass> copy_not_pattern = new ArrayList<CtClass>();

	Iterator<String> iterator = patterns.iterator();
	while(iterator.hasNext()){

		String name = "analyze_this."+iterator.next();
		CtClass cc = pool.get(name);
		cc.setName(gType + "_"+cc.getName());
		
		copy_pattern.add(cc);

		CtField f1= CtField.make("static public int tribbles = 13;", cc);
		cc.addField(f1);
		
		handleCon(cc);

	}//while loop
	
	Iterator<String> iterator2 = notpatterns.iterator();
	while(iterator2.hasNext()){

		String name = "analyze_this."+iterator2.next();
		CtClass cc2 = pool.get(name);
		cc2.setName(gType + "_"+cc2.getName());
		
		copy_not_pattern.add(cc2);

		CtField f2= CtField.make("public int tribbles = 13;", cc2);
		cc2.addField(f2);

		handleCon(cc2);
	}//while loop
	
	ret_set.add(copy_pattern);
	ret_set.add(copy_not_pattern);

	return ret_set;
}


private void handleCon(CtClass cc) throws CannotCompileException{
/*=====================================================
 * This method handles classes that do not have an empty 
 * constructor by inserting a blank constructor into them 
  =====================================================*/
	try{
		CtConstructor blank = new CtConstructor(null, cc);
		cc.addConstructor(blank);	

	}catch(Exception e){
		//don't do anything
	}
}

private void inject() throws Exception{
/*======================================================
 * This method loops through each type of grime the number of times indicated 
 * by variables passed through the constructor
 * ===================================================== */
	int i;
	System.out.println("Version: "+ current_version);
	System.out.print(Integer.toString(tig)+"_TIG, ");
	for(i = 0; i<tig; i++){
		couple(monsterArray.get(10), monsterArray.get(10), 't'); // (index 10 *TIG*:  pattern to pattern)
		wrapup("tig", monsterArray.get(10), monsterArray.get(11));
	}

	System.out.print(Integer.toString(pig)+"_PIG, ");
	for(i = 0; i<pig; i++){
		couple(monsterArray.get(8), monsterArray.get(8), 'p');// (index 8 *PIG*:  pattern to pattern)
		wrapup("pig", monsterArray.get(8), monsterArray.get(9));
	}

	System.out.print(Integer.toString(teeg)+"_TEEG, ");
	for(i = 0; i<teeg; i++){
		couple(monsterArray.get(2), monsterArray.get(3), 't');
		wrapup("teeg", monsterArray.get(2), monsterArray.get(3));

	}

	System.out.print(Integer.toString(peeg)+"_PEEG, ");
	for(i = 0; i<peeg; i++){
		couple(monsterArray.get(0), monsterArray.get(1), 'p');
		wrapup("peeg", monsterArray.get(0), monsterArray.get(1));

	}

	System.out.print(Integer.toString(teag)+"_TEAG, ");
	for(i = 0; i<teag; i++){
		couple(monsterArray.get(6), monsterArray.get(7), 't');
		wrapup("teag", monsterArray.get(6), monsterArray.get(7));
	}
	
	System.out.print(Integer.toString(peag)+"_PEAG, ");
	for(i = 0; i<peag; i++){
		couple(monsterArray.get(4), monsterArray.get(5), 'p');
		wrapup("peag", monsterArray.get(4), monsterArray.get(5));
	}
	System.out.println(" ");
	
}


private void defrost() throws NotFoundException, IOException, CannotCompileException{
	
	int i; 
	for(i=0; i<12; i++){
		Iterator<CtClass> iterator = monsterArray.get(i).iterator();
		while(iterator.hasNext()){
			CtClass thawMe = iterator.next();
			thawMe.defrost();
		}//while loop
	}//for loop 

}

private void couple(ArrayList <CtClass> to2, ArrayList <CtClass> from2, char strength) throws CannotCompileException, NotFoundException, IOException{
/*===========================================================
 * This method couples classes by inserting either the "from" class into the "to" class, 
 * or a string from "from" class into the "to" class. The strength char determines coupling strength:
 * p-persistent t-temporary 
 * =====================================================*/
	
	Random generator = new Random();
	int randomIndex = generator.nextInt(from2.size());
	CtClass from = from2.get(randomIndex);
	
	Random generator2 = new Random();
	int randomIndex2 = generator2.nextInt(to2.size());
	CtClass to = to2.get(randomIndex2);
	
	String from_class = from.getName();
	//Persistent Coupling
	if(strength == 'P' || strength == 'p'){
		try{
			String varname = " r"+version+"grimed"+Integer.toString(counter);
			
			String toBeAdded = from_class + varname+" = new " + from_class+"();";
			CtField f = CtField.make(toBeAdded, to);
			to.addField(f);
		}
		//Hopefully this will catch cases where constructors are not empty and insert a dummy instructor 
		catch(Exception e){
			CtConstructor blank = new CtConstructor(null, from);
			from.addConstructor(blank);	
			
			String varname = " r"+version+"grimed"+Integer.toString(counter);
			
			String toBeAdded = from_class + varname+" = new " + from_class+"();";
			CtField f = CtField.make(toBeAdded, to);
			to.addField(f);
		}
	}
	
	//Temporary Grime
	if (strength == 't' || strength == 'T'){
		String toBeAddedT = "public int r"+version +"tribble"+ counter + " =" +from.getName()+".tribbles;";
		CtField fT = CtField.make(toBeAddedT, to);
		to.addField(fT);
		
	}
	counter ++;

}

private void wrapup(String gType, ArrayList <CtClass>pattern, ArrayList <CtClass> not_pattern) throws Exception{
/*=====================================================
 * to wrap up, write out modified byte code to new 
 * class files in an output directory, add Sonar Config 
 * files
 * =====================================================*/

		//make folders to put manipulated classes
		String results = "Results";
		String dirRun = "/Run" + Integer.toString(repetitions);
		String subDirVersion = "";
		if(version > 1){
			subDirVersion = "/version" + Integer.toString(current_version);
		}
		String subSubDirGrime = "/"+gType.toUpperCase();
		
		String total = results+dirRun+subDirVersion+subSubDirGrime+"/src/";
		new File(total).mkdirs();
	
		print_results_to_file(pattern, total, gType);
		print_results_to_file(not_pattern, total, gType);
		
		//Add Sonar Config files
		String sub_dir = "";
		if (total.endsWith("src/")) {
			  sub_dir = total.substring(0, total.length() - 4);
		}

		SonarConfigalizer configs = new SonarConfigalizer(sub_dir);
		configs.write_root(Integer.toString(repetitions), Integer.toString(current_version), gType.toUpperCase());
		
	defrost();
}



private void print_results_to_file(ArrayList<CtClass> given, String dir, String gType) throws CannotCompileException, IOException{
	/*=====================================================
	 * Given an arraylist and a directory name, write each 
	 * arraylist object to file
	 * =====================================================*/
	
	if(given.size() > 0){
		Iterator<CtClass> iterator = given.iterator();
		while (iterator.hasNext()){
			CtClass temp = iterator.next();
			temp.writeFile(dir);
		}
	}

	
}

}
