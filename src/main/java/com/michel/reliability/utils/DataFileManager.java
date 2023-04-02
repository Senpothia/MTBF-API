package com.michel.reliability.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.SubstringFunction;

import com.opencsv.CSVWriter;

public class DataFileManager {

	public static void saveTable(String receptedTable) {
		
		String fileName = getFileName(receptedTable);
		List<String[]> table = getLines(receptedTable);
		
		CSVWriter csvWriter;
		try {

			csvWriter = new CSVWriter(new FileWriter(Constants.DATA_FOLDER + fileName + ".csv"));
			for (String[] ligne : table) {

				csvWriter.writeNext(ligne);

			}

			
			csvWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getFileName(String receptedTable) {
		
		String[] records = receptedTable.split(";");
		String fileName = records[1];
		return fileName;
	}

	private static List<String[]> getLines(String receptedTable) {
		
		receptedTable = receptedTable.substring(1, receptedTable.length()-1);
		System.out.println("Table reçue: " + receptedTable); 
		List<String[]> table = new ArrayList<>();
		String[] records = receptedTable.split(";");
		int colonnes = Integer.parseInt(records[0])+2;
		String[] entetes = new String[colonnes];
		for (int i = 2; i < colonnes + 2; i++) {

			entetes[i-2] = records[i];
		}
		table.add(entetes);

		for (int i = colonnes + 2;i < records.length - 1; i=i+colonnes){
			
			String[] ligne  = new String[colonnes];
			for(int j=0; j<colonnes; j++) {
				
				ligne[j] = records[i+j];
			}
			
			table.add(ligne);
		}

		return table;
	}

}
