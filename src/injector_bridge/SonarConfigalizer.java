package injector_bridge;
import java.io.File;
import java.io.FileOutputStream;


/*
 * This class creates the sonar-properties.properties file. It automatically generates this when it is outputting
 * the modified bytecode. 
 * 
 * INPUT: filepath
 * OUTPUT: sonar-properties.properties file
 * 
 * CREATOR: Melissa Dale
 * DATE: 17 MARCH 2013
 */





public class SonarConfigalizer {
	private String the_path;
	
	public SonarConfigalizer(String dir_path){
		the_path = dir_path;
	}
	
/*	=====================================================Create Root Sonar config */
public void write_root(String run, String version, String g_type){
	try {
        // Open property file
		File file = new File(the_path + "sonar-project.properties");
        FileOutputStream fstream = new FileOutputStream(file);
        if (!file.exists()) {
			file.createNewFile();
		}
        String fileContent = "";
        
        //Read File Line By Line
        fileContent ="# Required Meta Data \n";
        //fileContent = fileContent + "sonar.projectKey=grime:injector\n";

        fileContent = fileContent + "sonar.projectKey="+g_type+":Run"+run + ":version"+version+"\n";
        fileContent = fileContent + "sonar.projectName="+run+"-"+g_type + "\n";
        fileContent = fileContent + "sonar.projectVersion=" + version + "\n\n";
        
        
        fileContent = fileContent + "sonar.sources=src\n\n";
        
        fileContent = fileContent + "# The value of the property must be the key of the language.\n";
        fileContent = fileContent + "sonar.language=java\n\n";
        
        fileContent = fileContent + "# Encoding of the source code\n";
        fileContent = fileContent + "sonar.sourceEncoding=UTF-8\n\n";
        
        
        fileContent = fileContent + "#Additional parameters\n";
        fileContent = fileContent + "sonar.my.property=value\n\n";
        
        fileContent = fileContent + "\n";                		
      
        
     // get the content in bytes
     	byte[] contentInBytes = fileContent.getBytes();
        
        fstream.write(contentInBytes);
        fstream.close();
        fstream.flush();
        
    } catch (Exception e) {//Catch exception if any
        System.err.println("Error: " + e.getMessage());
    }
}

	
	
	
}
