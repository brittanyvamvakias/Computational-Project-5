import java.io.*;

public class Problem1 {
  public static void main(String[] args) {
	    double x0 = 1.;   
	    double vx0 = 0.;  
	    double y0 = 0.;   
	    double vy0 = 0.5;  
        double t=0.;
        double dt=0.1;

	    MovingElectron electron =new MovingObject();
	  	    
	    electron.dt = dt;
	    electron.x  = x0;
	    electron.vx = vx0;
	    electron.y  = y0;
		electron.vy = vy0;
		electron.Bx = 0.;
		electron.By = 0.;
		electron.Bz = 1.0;

try{
	FileWriter wtraject = new FileWriter("electrontrajectdt_1.txt");
	BufferedWriter trajectout = new BufferedWriter(wtraject);

  	  for(int n = 0;n<160;n++) {
		
		electron.electronFR4();

		trajectout.write(electron.x+ " " + electron.y);
		trajectout.newLine();

		}// end of the for-loop
	trajectout.close(); 
    
	}catch (Exception e){//Catch exception if any
	System.err.println("Error: " + e.getMessage());
					   }
	System.out.println("  ");					      
	System.out.println("All done!");
  }

}

