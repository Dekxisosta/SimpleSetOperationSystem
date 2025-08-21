package dev.dekxi.calculator.consoleio;

import java.util.Scanner;
import java.util.concurrent.Callable;

class ConsoleValidator{
	static Scanner scInt = new Scanner(System.in);
	static Scanner scLine = new Scanner(System.in);
	/*=================
	 * PACKAGE - PRIVATE FUNCTIONS
	 * =============*/
	//-->>
	static String[][] getCollectionOfSets(){
		int numOfArrays = getNumOfArrays();
		String[][] arr = new String [numOfArrays][];
		for (int i=0;i<numOfArrays;i++) {
			String setName = "SET " + String.valueOf((char) (65 + i));
			arr[i] = getStringArray(setName);
		}
		return arr;
	}
	//-->>
	static String[] getStringArray(String setName) {
		int numOfValues = getNumOfInputs(setName);
		String[] arr = getArrayOfInputs(numOfValues);
		
		return arr;
	}
	
	//-->>
	static String[] getArrayOfInputs(int numOfValues) {
		String[] inputs = new String[numOfValues];
		int size = 0;
		
		while(size< numOfValues) {
			System.out.print("Enter [" + size + "]: ");
			String input = getStringInput();
			inputs[size++] = input;
		}
		
		return inputs;
	}
	
	//-->>
	static int getNumOfArrays() {
		System.out.print("\n[PROMPT] How many arrays for the program? "
				+ "\nNOTE: Only limited to 2-5");
		int choice = getValidInt(2,5);
		
		return choice;
	}
	
	//-->>
	private static int getNumOfInputs(String setName) {
		System.out.print("\n[PROMPT] How many inputs in " + setName + "? ");
		int choice = getValidInt(3,20);
		
		return choice;
	}
	
	//-->>
	static int getValidInt(int min, int max) {
		int a;
		System.out.print("\nEnter: ");
		while(true) {
			try {
				a = scInt.nextInt();
				if((a<=max&&a>=min)) {
					break;
				}
				invalidIntegerFallback(min,max);
			}catch(Exception e) {
				invalidIntegerFallback(min,max);
				scInt.next();
			}
		}
		return a;
	}
	
	//-->>
	private static String getStringInput() {
		String a;
		while(true) {
			try {
				a = scLine.nextLine();
				break;
			}catch(Exception e) {
				invalidStringFallback();
				
			}
		}
		return a;
	}
	
	private static <T> catchException(Callable<T> sex) {
		
		return sex;
	}
	
	//-->>
	private static void invalidIntegerFallback(int min, int max){
		System.out.println("[ERROR] Enter a valid integer from " + min + " to " + max + ": ");
		displayEnterNewPrompt();
	}
	
	//-->>
	private static void invalidStringFallback(){
		System.out.println("[ERROR] Enter a valid string");
		displayEnterNewPrompt();
	}
	//-->>
	private static void displayEnterNewPrompt() {
		System.out.print("Enter: ");
		scLine.next();
	}
}