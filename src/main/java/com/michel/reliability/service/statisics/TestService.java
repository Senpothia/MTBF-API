package com.michel.reliability.service.statisics;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	public void activer() {  // Fonctionnel - tracé d'un graphe Cairo en lignes de commande

		RConnection connection = null;

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			connection = new RConnection("127.0.0.1", 6311);
			// connection = new RConnection("192.46.239.178", 6311);
			
			// Définition des valeurs
			String vector = "c(1,2,3,4)";
			connection.eval("sumVal=sum(" + vector + ")");
			
			// Graphique en pdf 
			connection.eval("pdf(file=\"/home/miguel/R/plots/histogram1.pdf\")");
			
			connection.eval("hist(airquality$Temp)");
			connection.eval("dev.off()");
			
			// Méthode lignes de commande
			connection.eval("library(\"Cairo\")");
			connection.eval(
					"Cairo(file=\"/home/miguel/R/plots/testfile2.png\",type=\"png\",bg=\"white\",units=\"px\", width=400, height=300, pointsize=12, dpi=\"auto\")");
			connection.eval("hist(airquality$Temp)");
			connection.eval("dev.off()");
			
			// Acquisition de résultats divers
			double mean = connection.eval("sumVal").asDouble();
			System.out.println("The sum is=" + mean);
			String nm[] = connection.eval("rnorm(100,0,1)").asStrings();

			for (short t = 0; t < nm.length; t++) {
				System.out.println(t + " = " + nm[t]);
			}
			
			

		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	
	public void activer2() {  // Appel à une fonction définie dans une script R - génération graphes en lignes de commandes

		RConnection connection = null;

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			connection = new RConnection("127.0.0.1", 6311);
			// connection = new RConnection("192.46.239.178", 6311);
			
			// Définition des valeurs
			String vector = "c(1,2,3,4)";
			connection.eval("sumVal=sum(" + vector + ")");
			
			// Graphique en pdf
			connection.eval("pdf(file=\"/home/miguel/R/plots/histogram1.pdf\")");
			connection.eval("hist(airquality$Temp)");
			connection.eval("dev.off()");
		
			
			// Acquisition de résultats divers
			double mean = connection.eval("sumVal").asDouble();
			System.out.println("The sum is=" + mean);
			String nm[] = connection.eval("rnorm(100,0,1)").asStrings();

			for (short t = 0; t < nm.length; t++) {
				System.out.println(t + " = " + nm[t]);
			}
			
			// appel à une fonction issue d'un script
			connection.eval("source(\"/home/miguel/R/utils/graph.r\")");
			REXP is_aba_palindrome = connection.eval("palindrome(20)");
			System.out.println("resultat script: " + is_aba_palindrome.asInteger());
			
			
		
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void activer21() {  // Tracé graphe Cairo via script R - non fonctionnel

		RConnection connection = null;

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			connection = new RConnection("127.0.0.1", 6311);
			// connection = new RConnection("192.46.239.178", 6311);
			
		
			// appel à une fonction issue d'un script
			connection.eval("source(\"/home/miguel/R/utils/graph.r\")");
			REXP is_aba_palindrome = connection.eval("palindrome(20)");
			System.out.println("resultat script: " + is_aba_palindrome.asInteger());
			
			// Méthode de script - tracé de graph
			connection.eval("source(\"/home/miguel/R/utils/graph21.r\")");
			connection.eval("graph()");
		
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void regression2() {  // Tracé graphe Cairo via script R - non fonctionnel

		RConnection connection = null;

	
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			try {
				connection = new RConnection("127.0.0.1", 6311);
				connection.eval("source(\"/home/miguel/R/utils/reg4.r\")");
				connection.eval("graph()");
	
			} catch (RserveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
	}

	
	public void activer3() {  // Non fonctionnel  - Tracé graphe Cairo avec script contenu dans une string - non fonctionnel

		RConnection connection = null;

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			connection = new RConnection("127.0.0.1", 6311);
			// connection = new RConnection("192.46.239.178", 6311);
			
			// Définition des valeurs
			String vector = "c(1,2,3,4)";
			connection.eval("sumVal=sum(" + vector + ")");
			
			// Graphique en pdf
			connection.eval("pdf(file=\"/home/miguel/R/plots/histogram1.pdf\")");
			connection.eval("hist(airquality$Temp)");
			connection.eval("dev.off()");
			
			
			
			// Méthode script R sur une ligne - méthode 2
			
			connection.eval("library(Cairo) Cairo(file=\"testfile2.jpg\", type=\"png\", units=\"px\", width=400,height=300, pointsize=12, dpi=\"auto\") hist(airquality$Temp) dev.off()");
			
			// Acquisition de résultats divers
			double mean = connection.eval("sumVal").asDouble();
			System.out.println("The sum is=" + mean);
			String nm[] = connection.eval("rnorm(100,0,1)").asStrings();

			for (short t = 0; t < nm.length; t++) {
				System.out.println(t + " = " + nm[t]);
			}
			
			// appel à une fonction issue d'un script
			connection.eval("source(\"/home/miguel/R/utils/graph.r\")");
			REXP is_aba_palindrome = connection.eval("palindrome(20)");
			System.out.println("resultat script: " + is_aba_palindrome.asInteger());
			
			

		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}


	public void regression() {
		RConnection connection = null;
		
		try {
			connection = new RConnection("127.0.0.1", 6311);
			
			connection.eval("Obs<-1:15");
			connection.eval("Age<-c(2,2,2,3,3,3,4,4,4,5,5,5,6,6,6)");
			connection.eval("TxDDT<-c(0.2,0.25,0.18,0.19,0.29,0.28,0.31,0.33,0.36,0.71,0.38,0.47,1.1,0.87,0.83)");
			connection.eval("broches<-data.frame(Obs, Age, TxDDT)");
			connection.voidEval("model<-lm(TxDDT~Age)");
			try {
				double [] coeff = connection.eval("coefficients(model)").asDoubles();
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void regCoefficients() {
		RConnection connection = null;
		
		try {
			connection = new RConnection("127.0.0.1", 6311);
			
			
			connection.eval("Obs<-1:15");
			connection.eval("Age<-c(2,2,2,3,3,3,4,4,4,5,5,5,6,6,6)");
			connection.eval("TxDDT<-c(0.2,0.25,0.18,0.19,0.29,0.28,0.31,0.33,0.36,0.71,0.38,0.47,1.1,0.87,0.83)");
			connection.eval("broches<-data.frame(Obs, Age, TxDDT)");
			connection.voidEval("model<-lm(TxDDT~Age)");
			try {
				double [] coeff = connection.eval("coefficients(model)").asDoubles();
				for(int i = 0; i<coeff.length; i++) {
					
					System.out.println("Coef" + i +": " + coeff[i]);
				}
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void regression3() {
		
		RConnection connection = null;

		
		/*
		 * Create a connection to Rserve instance running on default port 6311
		 */
		try {
			connection = new RConnection("127.0.0.1", 6311);
			connection.eval("source(\"/home/miguel/R/utils/reg2.r\")");
			connection.eval("graph()");

		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
