
public class MovingElectron {
 	    
	   double cof,dt; 
	   double x,y,z,vx,vy,vz,E, y0, x0, w1x, w1y, vya, vxa, vy0, vx0, x1a, y1a, w2x, w2y, u1x, u1y, u2x, u2y;
	   double u3x, u3y, w3x, w3y, u4x, u4y, w4x, w4y, r1x, r1y, r1z; 
	   double k = 1 / (2 - Math.pow(2, 1/3));
	   double w,theta;
	   double sin = Math.sin(theta);
	   double cos = Math.cos(theta);
	   double Bx,By,Bz,vbx,vby;
	   double t;
	   private double ax,ay,r,r2;      
	   private double s,fs,bs;

       public MovingElectron(){System.out.println("A new moving object is created.");					      
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
	   public void positionstep(double cof){
	    			      x = x+vx*dt*cof; 
	    			      y = y+vy*dt*cof; 
	   }
	   public void velocitystep(double cof){
	                      accel();
	    			      vx = vx+ax*dt*cof; 
	    			      vy = vy+ay*dt*cof; 
	   }
	   public void magveloctiystep(double cof){ // what is the difference between vb and v?
						   double vx1 = vx;
						   double vy1 = vy;
						   r1x = -Bz*vy; // x-component for B cross v
						   r1y = Bz*vx; // y-component for B cross v
						   r1z = 0.; // z-component for B cross v
						   vx = (Math.sin((dt*cof)/(x*x)) * (-vy) + (Math.cos((dt*cof)/(x*x)))*(vx));
						   vy = (Math.sin((dt*cof)/(x*x)) * vx1 + Math.cos((dt*cof)/(x*x))*(vy));
	   }
	   public void mag2step(double cof){  // new electron 2nd order algo
						   positionstep(0.5*cof);
						   magveloctiystep(1.0*cof);
						   positionstep(0.5*cof);
						   
	   }
	   public void sym1bstep(double cof){
						positionstep(cof);
						velocitystep(cof);
	   }
	   public void sym1astep(double cof){
						velocitystep(cof);
						positionstep(cof);
	   }
	   public void sym2astep(double cof){
						velocitystep(0.5*cof);
						positionstep(1.0*cof);
						velocitystep(0.5*cof);
	   }
	   public void mag2bstep(double cof){
						positionstep(0.5*cof);
						magveloctiystep(1.0*cof); 
						positionstep(0.5*cof);
		}		
		public void sym2bstep(double cof){
						positionstep(0.5*cof);
						velocitystep(1.0*cof); 
						positionstep(0.5*cof);
		}
	   public void RK2step(){
						u1x = x;
						u1y = y;
						w1x = vx;
						w1y = vy;
							sym1astep(1.0);
						u2x = x;
						u2y = y;
						w2x = vx;
						w2y = vy;
						
						x = u1x;
						y = u1y;
						vx = w1x;
						vy = w1y;
							sym1bstep(1.0);

						x = 0.5*(x+u2x);
						y = 0.5*(y+u2y);
						vx = 0.5*(vx+w2x);
						vy = 0.5*(vy+w2y);
						
	   }
	   public void electronFR4(){
		   				double s = Math.pow(2.0, (1.0/3.0));
						mag2bstep(dt/(2.0-s));
						mag2bstep((-s*dt)/(2.0-s));
						mag2bstep(dt/(2.0-s));
}

		public void RKN4(double cof){
						u1x = x;
						u1y = y;
						w1x = vx;
						w1y = vy;
							sym2astep(0.5 * cof);
						// u2x = x;
						// u2y = y;
						// w2x = vx;
						// w2y = vy;

						// u1x = x;
						// u1y = y;
						// w1x = vx;
						// w1y = vy;
							sym2astep(0.5 * cof);
						u2x = x;
						u2y = y;
						w2x = vx;
						w2y = vy;

						u1x = x;
						u1y = y;
						w1x = vx;
						w1y = vy;
							sym2astep(1.0 * cof);
				
						
						x = ((4 / 3) * u2x - (1 / 3) * x);
						y = ((4 / 3) * u2y - (1 / 3) * y);
						vx = ((4 / 3) * w2x - (1 / 3) * vx);
						vy = ((4 / 3) * w2y - (1 / 3) * vy);
		}
						
	   
}
