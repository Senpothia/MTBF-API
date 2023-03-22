package com.michel.reliability.models;

import java.lang.invoke.ConstantCallSite;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Service;

import com.michel.reliability.utils.Auxiliary;
import com.michel.reliability.utils.Constants;

@Service
public class Rconnector {

	public RConnection getRConnection() throws RserveException {

		RConnection connection = new RConnection(Constants.HOST, Constants.PORT);
		return connection;
	}

	public RConnection getRConnection(String host, int port) throws RserveException {

		RConnection connection = new RConnection(host, port);
		return connection;
	}

	public void closeRconnection(RConnection connection) {

		connection.close();
	}

	public void provideSource(String source, RConnection connection) {

		StringBuilder commande = Auxiliary.getSourceDirectory(source);
		System.out.println(commande.toString());
		try {
			connection.eval(commande.toString());
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void callFunction(String functionName, RConnection connection) {

		StringBuilder commande = Auxiliary.getFunctionString(functionName);
		System.out.println(commande.toString());
		try {
			connection.eval(commande.toString());
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
