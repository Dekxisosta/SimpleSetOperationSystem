package dev.dekxi.calculator.consoleio;

/*
 * The Console class allows for scanning and printing out of data in a specified format
 */

import java.util.Scanner;

import dev.dekxi.calculator.datae.ActionRegistry;

class ConsolePrinter{
	/*=================
	 * STATIC FIELDS / OBJECTS
	 * =============*/
	
	static String[] actionNames = ActionRegistry.getAllActionNames();
	
	/*=================
	 * PACKAGE-PRIVATE FUNCTIONS
	 * =============*/
	
	//-->>
	static void printInitialGreeting() {
		System.out.println("FINALIZED PROGRAM ON: 2025 / 08 / 17");
	}
	
	//-->>
	static void printMenu() {
		printBanner(ConfigData.APP_NAME);
		printMenuOptions();
	}
	
	//-->>
	private static void printMenuOptions() {
		for(int i=0;i<actionNames.length;i++)
			System.out.print("\n[" + (i+1) + "]" + " " + actionNames[i]);
		System.out.print("\n[0] Terminate Program");
	}
	
	//-->>
	public static int getMenuChoice() {
		int choice = ConsoleValidator.getValidInt(0,actionNames.length);
		
		return choice;
	}
	
	//-->>
	private static void printBanner(String header) {
		printBorder(header.length());
		System.out.printf("%n%s",
				"{| " + header + " |}");
		printBorder(header.length());
	}
		
	//-->>
	private static void printBorder(int length) {
		System.out.printf("%n%s%s%s",
				"+++",
				"=".repeat(length),
				"+++"
				);
	}
	
	

	
	
}