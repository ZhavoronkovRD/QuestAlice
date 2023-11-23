package ru.rsreu.zhavoronkov.base.resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelMapParser {
	
	@SuppressWarnings("resource")
	public static List<List<Integer>> parseCSV(String filePath, String separator) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = null;
			
			while((line = br.readLine()) != null) {
				String[] elements = line.split(separator);
				List<Integer> numberLine = convertDataToNumber(elements);
				result.add(numberLine);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = null;
		} catch (IOException e) {
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}
	
	public static void writeToSCV(List<List<Integer>> elements, String filePath, String separator) {
		try {
			FileWriter writer = new FileWriter(filePath);
			
			for(List<Integer> line: elements) {
				writeLine(writer, line, separator);
			}
			
	        writer.flush();
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeLine(FileWriter writer, List<Integer> values, String separator) throws IOException {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (Integer value : values) {
            if (!first) {
                sb.append(separator);
            }
            sb.append(value);
            first = false;
        }
        sb.append("\n");
        writer.append(sb.toString());
	}
	
	private static List<Integer> convertDataToNumber(String[] elements) {
		List<Integer> els = new ArrayList<Integer>();
		
		for(String el: elements) {
			int number = Integer.valueOf(el);
			els.add(number);
		}
		
		return els;
	}

}
