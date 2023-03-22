package com.michel.reliability.service.statisics;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michel.reliability.models.Rconnector;
import com.michel.reliability.utils.Auxiliary;

@Service
public class TestService {

	@Autowired
	private Rconnector connectorToR;

	private RConnection connectionToR = null;

	public void activer() { // Fonctionnel - tracé d'un graphe Cairo en lignes de commande

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */

			connectionToR = connectorToR.getRConnection();

			// Définition des valeurs
			String vector = "c(1,2,3,4)";
			connectionToR.eval("sumVal=sum(" + vector + ")");

			// Graphique en pdf
			connectionToR.eval("pdf(file=\"/home/miguel/R/plots/histogram1.pdf\")");

			connectionToR.eval("hist(airquality$Temp)");
			connectionToR.eval("dev.off()");

			// Méthode lignes de commande
			connectionToR.eval("library(\"Cairo\")");
			connectionToR.eval(
					"Cairo(file=\"/home/miguel/R/plots/testfile2.png\",type=\"png\",bg=\"white\",units=\"px\", width=400, height=300, pointsize=12, dpi=\"auto\")");
			connectionToR.eval("hist(airquality$Temp)");
			connectionToR.eval("dev.off()");

			// Acquisition de résultats divers
			double mean = connectionToR.eval("sumVal").asDouble();
			System.out.println("The sum is=" + mean);
			String nm[] = connectionToR.eval("rnorm(100,0,1)").asStrings();

			for (short t = 0; t < nm.length; t++) {
				System.out.println(t + " = " + nm[t]);
			}
			connectorToR.closeRconnection(connectionToR);

		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connectionToR.close();
		}
	}

	public void activer2() { // Appel à une fonction définie dans une script R - génération graphes en lignes
								// de commandes

		try {

			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */

			connectionToR = connectorToR.getRConnection();
			// Définition des valeurs
			String vector = "c(1,2,3,4)";
			connectionToR.eval("sumVal=sum(" + vector + ")");

			// Graphique en pdf
			connectionToR.eval("pdf(file=\"/home/miguel/R/plots/histogram1.pdf\")");
			connectionToR.eval("hist(airquality$Temp)");
			connectionToR.eval("dev.off()");

			// Acquisition de résultats divers
			double mean = connectionToR.eval("sumVal").asDouble();
			System.out.println("The sum is=" + mean);
			String nm[] = connectionToR.eval("rnorm(100,0,1)").asStrings();

			for (short t = 0; t < nm.length; t++) {
				System.out.println(t + " = " + nm[t]);
			}

			// appel à une fonction issue d'un script
			connectionToR.eval("source(\"/home/miguel/R/utils/graph.r\")");
			REXP is_aba_palindrome = connectionToR.eval("palindrome(20)");
			System.out.println("resultat script: " + is_aba_palindrome.asInteger());
			connectorToR.closeRconnection(connectionToR);

		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connectionToR.close();
		}
	}

	public void activer21() { // Tracé graphe Cairo via script R - non fonctionnel

		// RConnection connection = null;

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */

			connectionToR = connectorToR.getRConnection();

			// appel à une fonction issue d'un script
			connectionToR.eval("source(\"/home/miguel/R/utils/graph.r\")");
			REXP is_aba_palindrome = connectionToR.eval("palindrome(20)");
			System.out.println("resultat script: " + is_aba_palindrome.asInteger());

			// Méthode de script - tracé de graph
			connectionToR.eval("source(\"/home/miguel/R/utils/graph21.r\")");
			connectionToR.eval("graph()");
			connectorToR.closeRconnection(connectionToR);

		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connectionToR.close();
		}
	}

	public void regression2() { // Tracé graphe Cairo via script R - non fonctionnel

		try {

			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */

			connectionToR = connectorToR.getRConnection();
			connectionToR.eval("source(\"/home/miguel/R/utils/reg4.r\")");
			connectionToR.eval("graph()");
			connectorToR.closeRconnection(connectionToR);

		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void activer3() { // Non fonctionnel - Tracé graphe Cairo avec script contenu dans une string -
								// non fonctionnel

		try {

			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			connectionToR = connectorToR.getRConnection();

			// Définition des valeurs
			String vector = "c(1,2,3,4)";
			connectionToR.eval("sumVal=sum(" + vector + ")");

			// Graphique en pdf
			connectionToR.eval("pdf(file=\"/home/miguel/R/plots/histogram1.pdf\")");
			connectionToR.eval("hist(airquality$Temp)");
			connectionToR.eval("dev.off()");

			// Méthode script R sur une ligne - méthode 2

			connectionToR.eval(
					"library(Cairo) Cairo(file=\"testfile2.jpg\", type=\"png\", units=\"px\", width=400,height=300, pointsize=12, dpi=\"auto\") hist(airquality$Temp) dev.off()");

			// Acquisition de résultats divers
			double mean = connectionToR.eval("sumVal").asDouble();
			System.out.println("The sum is=" + mean);
			String nm[] = connectionToR.eval("rnorm(100,0,1)").asStrings();

			for (short t = 0; t < nm.length; t++) {
				System.out.println(t + " = " + nm[t]);
			}

			// appel à une fonction issue d'un script
			connectionToR.eval("source(\"/home/miguel/R/utils/graph.r\")");
			REXP is_aba_palindrome = connectionToR.eval("palindrome(20)");
			System.out.println("resultat script: " + is_aba_palindrome.asInteger());
			connectorToR.closeRconnection(connectionToR);

		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} finally {
			connectionToR.close();
		}
	}

	public void regression() {

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */

			connectionToR = connectorToR.getRConnection();

			connectionToR.eval("Obs<-1:15");
			connectionToR.eval("Age<-c(2,2,2,3,3,3,4,4,4,5,5,5,6,6,6)");
			connectionToR.eval("TxDDT<-c(0.2,0.25,0.18,0.19,0.29,0.28,0.31,0.33,0.36,0.71,0.38,0.47,1.1,0.87,0.83)");
			connectionToR.eval("broches<-data.frame(Obs, Age, TxDDT)");
			connectionToR.voidEval("model<-lm(TxDDT~Age)");
			try {
				double[] coeff = connectionToR.eval("coefficients(model)").asDoubles();
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectorToR.closeRconnection(connectionToR);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void regCoefficients() {

		try {

			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			connectionToR = connectorToR.getRConnection();
			connectionToR.eval("Obs<-1:15");
			connectionToR.eval("Age<-c(2,2,2,3,3,3,4,4,4,5,5,5,6,6,6)");
			connectionToR.eval("TxDDT<-c(0.2,0.25,0.18,0.19,0.29,0.28,0.31,0.33,0.36,0.71,0.38,0.47,1.1,0.87,0.83)");
			connectionToR.eval("broches<-data.frame(Obs, Age, TxDDT)");
			connectionToR.voidEval("model<-lm(TxDDT~Age)");
			try {
				double[] coeff = connectionToR.eval("coefficients(model)").asDoubles();
				for (int i = 0; i < coeff.length; i++) {

					System.out.println("Coef" + i + ": " + coeff[i]);
				}
				connectorToR.closeRconnection(connectionToR);
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

		try {
			/*
			 * Create a connection to Rserve instance running on default port 6311
			 */
			connectionToR = connectorToR.getRConnection();
			connectionToR.eval("source(\"/home/miguel/R/utils/reg2.r\")");
			connectionToR.eval("graph()");
			connectorToR.closeRconnection(connectionToR);

		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printSource() {

		String scriptName = "scriptTestName";
		StringBuilder cmde = Auxiliary.getSourceDirectory(scriptName);
		System.out.println("Commande; " + cmde);
	}

	public void regression4() {

		try {
			connectionToR = connectorToR.getRConnection();
			connectorToR.provideSource("reg2.r", connectionToR);
			connectorToR.callFunction("graph()", connectionToR);
			connectorToR.closeRconnection(connectionToR);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
