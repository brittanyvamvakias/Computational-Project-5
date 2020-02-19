
public class MovingOsc {
 	    
       double cof,dt,v,a,t,x,y,vx,vy,E;  
       double q1,q2,v1,v2;
	   double k = 1 / (2 - Math.pow(2, 1/3));
       double w0, theta, lamda, q0, v0;
       double w = Math.sqrt(w0*w0-lamda*lamda);
	   double sin = Math.sin(w*t);
	   double cos = Math.cos(w*t);
	   double e = Math.exp(-lamda*t);
	   double q = e * (q0*cos+ ((v0+lamda*q0)/w)*sin);
	   private double ax,ay,r,r2;      
	   private double s,fs,bs;

       public MovingOsc(){System.out.println("A new moving object is created.");					      
       }		     	      
//------------------object properties       
	   public void energy(){
			E=0.5*(vx*vx+vy*vy)-1./Math.sqrt(x*x+y*y);
	   }
	   public void accel(){
			r2 = x*x+y*y;
			r  = Math.sqrt(r2);
			ax = -x/r/r2;
			ay = -y/r/r2;
	   }
//------------------object motion       
	//    public void positionstep(double cof){
	//     			      x = x+vx*dt*cof; 
	//     			      y = y+vy*dt*cof; 
	//    }
	//    public void velocitystep(double cof){
	//                       accel();
	//     			      vx = vx+ax*dt*cof; 
	//     			      vy = vy+ay*dt*cof; 
	//    }
       public void oscillatorpositionstep(double cof){ // oscillator position step
						q = q+v*dt*cof;
       }
       public void oscillatorvelocitystep(double cof){ // oscillator velocity step
                        v = v - w0*w0*q*dt*cof;
       }
       public void oscillatorvelocitystep2(double cof){ // oscillator velocity step
                        double e = Math.exp(-lamda*2*dt*cof);
                        v = v * e;
}
       public void oscillatoraccel(){ // oscillator acceleration
                        a = - w0*w0*q-2*lamda*v;
       }
       public void oscillatorposition(double cof){
                    double e = Math.exp(-lamda*t);
                    q = e * (q0*Math.cos(w*t)+ ((v0+lamda*q0)/w)*Math.sin(w*t));
                    t = t+dt;
       }   
       public void oscillator1storder(double cof){
                    oscillatorpositionstep(1.0*cof);
                    oscillatorvelocitystep(1.0*cof);
                    oscillatorvelocitystep2(1.0*cof);
       }
       public void oscillator2ndorder(double cof){
					oscillatorpositionstep(0.5*cof);
                    oscillatorvelocitystep(1.0*cof);
                    oscillatorvelocitystep2(1.0*cof);
					oscillatorpositionstep(0.5*cof);
       }	
       public void oscillator2A(double cof){
                    oscillatorvelocitystep(0.5*cof);
                    oscillatorvelocitystep2(0.5*cof);
                    oscillatorpositionstep(1.0*cof);
                    oscillatorvelocitystep(0.5*cof);
                    oscillatorvelocitystep2(0.5*cof);
}

	    public void oscillatorFR4(double cof){
	 						oscillator2ndorder(cof * k);
	 						oscillator2ndorder(- Math.pow(2., 1./3.) * cof * k);
						    oscillator2ndorder(cof * k);			
         }
         public void oscillatorRKN4(double cof){
                    q1 = q;
                    v1 = v;
                    oscillator2A(1.0*cof);
                    q2 = q;
                    v2 = v;
                    q1 = q;
                    v1 = v;
                    oscillator2A(1.0*cof);
                    q = ((4 / 3) * q2 - (1 / 3) * q);
                    v = ((4 / 3) * v2 - (1 / 3) * v);
         }

// 		public void RKN4(double cof){
// 						u1x = x;
// 						u1y = y;
// 						w1x = vx;
// 						w1y = vy;
// 							sym2astep(0.5 * cof);
// 						// u2x = x;
// 						// u2y = y;
// 						// w2x = vx;
// 						// w2y = vy;

// 						// u1x = x;
// 						// u1y = y;
// 						// w1x = vx;
// 						// w1y = vy;
// 							sym2astep(0.5 * cof);
// 						u2x = x;
// 						u2y = y;
// 						w2x = vx;
// 						w2y = vy;

// 						u1x = x;
// 						u1y = y;
// 						w1x = vx;
// 						w1y = vy;
// 							sym2astep(1.0 * cof);
				
						
// 						x = ((4 / 3) * u2x - (1 / 3) * x);
// 						y = ((4 / 3) * u2y - (1 / 3) * y);
// 						vx = ((4 / 3) * w2x - (1 / 3) * vx);
// 						vy = ((4 / 3) * w2y - (1 / 3) * vy);
// 		}
						
	   
}
