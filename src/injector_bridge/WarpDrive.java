package injector_bridge;
import java.util.ArrayList;


public class WarpDrive {

        public WarpDrive() {


        }
        //Method 1: same number of instances of grime for every grime type
        private static int[] setGrimeInst(int onlyOne){
                int [] instances = new int [6];
                
                instances[0]=onlyOne;
                instances[1]=onlyOne;
                instances[2]=onlyOne;
                instances[3]=onlyOne;
                instances[4]=onlyOne;
                instances[5]=onlyOne;

                return instances;
        }
        
        //Method 2: custom number of grime instances to inject
        private static int[] setGrimeInst(int peeg, int teeg,  int peag, int teag, int pig, int tig){
                int [] instances = new int [6];
                
                instances[0]=peeg;
                instances[1]=teeg;
                instances[2]=peag;
                instances[3]=teag;
                instances[4]=pig;
                instances[5]=tig;

                return instances;
        }
        
        /*======================================================
         * Method 1:
         *  Each Grime Type has the same number of injections
         *  is intended to repeat the experiment the specified number of times
         * ===================================================== */
        public void inject(int versions, int times, int instances, ArrayList<String> patterns, ArrayList<String> notpatterns) throws Exception{
                int i;
                for (i=0; i< times; i++){
                        InjectorLogic federation  = new InjectorLogic(versions, patterns, notpatterns, setGrimeInst(instances), i);//,         1,              1,              0,      0,              0);
                        federation.engage();    

                }
        }

        
        /*======================================================
         * Method 2:
        *  Each Grime Type has custom number of injections
        *  is intended to repeat the experiment the specified number of times
         * ===================================================== */
         public void inject(int versions, int times, int peag, int peeg, int pig, int teag, int teeg, int tig, ArrayList<String> patterns, ArrayList<String> notpatterns) throws Exception{
               	int i;
               	for (i=1; i< times+1; i++){
               		InjectorLogic federation  = new InjectorLogic(versions, patterns, notpatterns, setGrimeInst(peeg, teeg, peag, teag, pig, tig), i);//,         1,              1,              0,      0,              0);
               		federation.engage();    

               	}
                	
                	
        }
        
        
}