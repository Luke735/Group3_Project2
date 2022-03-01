package team3InfixParser;

import java.io.*;
import java.util.*;

public class team3InfixParser {

	public static void main(String[] args) {
		
		//creates a file in the eclipse project src folder
		File myFile = new File("src/testCaseFile.txt");
		//using this code to test file function for project
//		try {
//		myFile.createNewFile();
//		BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
//		
//		//TEST INFIX EXPRESSIONS GO HERE
//	    writer.write("2^6\n");
//	    writer.write("2-3*((30-10)/5)\n");
//	    writer.write("1 || 2 + 5\n");
//	    writer.write("2 - 3 * ( ( 2 - 1 ) / 5 )\n");
//	    writer.write("5 ^ 2\n");
//	    writer.write("9 / 2\n");
//	    writer.write("(4>=4) && 0\n");
//	    writer.write("2%2+2^2-5*(3^2)\n");
//	    writer.write("6 * 8 * 2 + 13\n");
//	    writer.write("9 >= 2\n");
//	    writer.write("10 < 2\n");
//	    writer.write("5 ^ 2 % 7 && (4 - 4)\n");
//	    writer.close();
//		}
//		catch (IOException e) {
//			System.out.println("IO exception");
//		}
		
		
		//puts each line inside of the text file into the project's methods
		//and prints their outputs in a user-friendly way
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(myFile));
			String lineStr = reader.readLine();
			while (lineStr != null) {
				System.out.println("INFIX:");
				System.out.println("	  " + lineStr);
				System.out.println("POSTFIX:");
				String str = inFToPostF.infixToPostfix(lineStr + " ");
				StringBuilder e = new StringBuilder();
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == '#') { e.append(">="); }
					else if (str.charAt(i) == '$') { e.append("<="); }
					else if (str.charAt(i) == '=') { e.append("=="); }
					else if (str.charAt(i) == '!') { e.append("!="); }
					else if (str.charAt(i) == '&') { e.append("&&"); }
					else if (str.charAt(i) == '|') { e.append("||"); }
					else { e.append(str.charAt(i)); }
				}
				System.out.println("	  " + e.toString());
				System.out.println("EVALUATED:");
				System.out.println("	  " + evalPostF.evaluate(inFToPostF.infixToPostfix(lineStr + " ")));
				System.out.println();
				
				lineStr = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}//end main

}//end class