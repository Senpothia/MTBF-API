package com.michel.reliability.utils;

public class Auxiliary {

	public static StringBuilder getSourceDirectory(String scriptName) {

		StringBuilder directory = new StringBuilder(Constants.SOURCE_PREFIXE);
		directory.append(Constants.START_CMDE);
		directory.append(Constants.QUOTE);
		directory.append(Constants.SCRIPTS);
		directory.append(scriptName);
		directory.append(Constants.QUOTE);
		directory.append(Constants.END_CMDE);
		return directory;
	}

	public static StringBuilder getFunctionString(String functionName) {

		StringBuilder function = new StringBuilder(functionName);
		return function;
	}

}
