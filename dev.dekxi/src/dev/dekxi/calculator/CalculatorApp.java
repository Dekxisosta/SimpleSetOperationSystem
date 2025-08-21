package dev.dekxi.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import dev.dekxi.calculator.consoleio.ConfigData;
import dev.dekxi.calculator.consoleio.ConsolePrinter;
import dev.dekxi.calculator.datae.ActionRegistry;
import dev.dekxi.calculator.datae.LabeledSet;
import dev.dekxi.calculator.datae.PowerSetResult;
import dev.dekxi.calculator.datae.SetOperationResultStrings;
import dev.dekxi.calculator.operations.SetOperations;

/*
 * ====================================
 * # DEV DEKXI'S SET OPERATION SYSTEM
 * ====================================
 	* Github Account: Dekxisosta
 	* Date Created: 2025 / 08 / 15
 	* 
 *  PROGRAM DESCRIPTION:
	 * This program uses a modular approach. If you wish to add an action to the program,
	 * simply utilize the addActionData method by putting the action name and its
	 * appropriate runnable function
	 * 
 */

public class CalculatorApp {
	private static String[][]sets;
	
	static void setAllSets(String[][] a) { sets = a;}
	
	private static boolean continueProgram = true;
	private static Map<Integer, Runnable> actionMap;
	
	/*=================
	 * PROGRAM ENTRY POINT
	 * =============*/
	public static void main(String[] args) {
		try {
			app(args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void app(String[] args) {
		//PROGRAM INITIALIZATION
		registerActions();
		actionMap = createActionMap();
		
		//INITIAL ASKING OF INPUTS
		ConsolePrinter.printInitialGreeting();
		ConsolePrinter.printBanner(ConfigData.APP_NAME);
		actionMap.get(1).run();
		
		//LOOPING PROGRAM
		while(continueProgram) {
			ConsolePrinter.printMenu();
			int choice = ConsolePrinter.getMenuChoice();
			if (!(choice==0)) {
				runActionBasedOnChoice(actionMap, choice);
			}else {
				continueProgram = false;
				System.out.println("Thank you for using my program! - dekxi");
			}
		}
	}
	/*=================
	 * INITIALIZER
	 * =============*/
	private static void registerActions() {
		//Creation of sets must always be on top as method app has a dependency on it
		/*
		 * The String value may seem counter intuitive, but it merely acts as an
		 * operation title for the menu later on
		 */
		String operationPhrase = " OF SETS";
		
		ActionRegistry.addActionData("CHANGING" + operationPhrase, SetOperations::createCollectionOfSets);
		
		ActionRegistry.addActionData("PRINTOUT" + operationPhrase, () -> {
			ConsolePrinter.printBanner("PRINTOUT" + operationPhrase);
			for(int i=0;i<sets.length;i++)
				new LabeledSet("SET " + (char)(65 + i),sets[i]).print();
		});
		
		ActionRegistry.addActionData("UNION" + operationPhrase, () -> {
			ConsolePrinter.printBanner("UNION" + operationPhrase);
			printPairwiseOperations(sets, SetOperations::getUnion, "UNION");
		});
		
		ActionRegistry.addActionData("INTERSECTION" + operationPhrase, () ->{
			ConsolePrinter.printBanner("INTERSECTION" + operationPhrase);
			printPairwiseOperations(sets, SetOperations::getIntersection, "INTERSECTION");
		});
		
		ActionRegistry.addActionData("SET DIFFERENCE" + operationPhrase, () -> {
			ConsolePrinter.printBanner("SET DIFFERENCE" + operationPhrase);
			printReversedOperations(sets, SetOperations::getSetDifference, "SET DIFFERENCE");
		});
		
		ActionRegistry.addActionData("POWERSET AND ITS SIZE", () -> {
			ConsolePrinter.printBanner("POWERSET AND ITS SIZE");
			for(int i=0; i<sets.length;i++)
				new PowerSetResult(
						SetOperations.getSizeOfThePowerset(sets[i]),
						SetOperations.getPowerset(sets[i])
						);
			
		});
//		ActionRegistry.addActionData("SYMMETRIC DIFFERENCE OF SETS", () -> {
//			ConsoleIO.printStringArray("SYMMETRIC DIFFERENCE OF SETS", 
//					SetOperations.getSetDifference(
//							SetOperations.getUnion(sets[0], sets[1]), 
//							SetOperations.getIntersection(sets[0], sets[1])));
//		});
//		ActionRegistry.addActionData("IS SET A SUBSET OF THE OTHER", () -> {
//			actionMap.get(2).run();
//			for(int i=0;i<sets.length;i++)
//				ConsoleIO.printIsSubsetStatement("SET A", "SET B", SetOperations.isSubsetOf(sets[0+i], sets[1-i]));
//		});
		
	}
	/*
	 * I used a hash map for a more dynamic approach in registering action data to indices
	 */
	
	private static Map<Integer, Runnable> createActionMap() {
		Map<Integer, Runnable> actionMap = new HashMap<>();
		for (int i = 0; i < ActionRegistry.getActionCount(); i++) {
			int index = i;
			actionMap.put(i + 1, () -> ActionRegistry.getActionFromIndex(index).run());
	    }
		return actionMap;
	}
	
	private static void runActionBasedOnChoice(Map<Integer, Runnable> actionMap, int choice) {
	    actionMap.get(choice).run();
	}
	
	private static void printPairwiseOperations(String[][] sets, BiFunction<String[], String[], String[]> operation, String operationName) {
        for (int i = 0; i < sets.length; i++) {
            for (int j = i + 1; j < sets.length; j++) {
                new SetOperationResultStrings(
                    "SET " + (char)(65 + i),
                    "SET " + (char)(65 + j),
                    operationName,
                    operation.apply(sets[i], sets[j])
                ).print();
            }
        }
        System.out.println();
    }
	
	private static void printReversedOperations(String[][] sets, BiFunction<String[], String[], String[]> operation, String operationName) {
        for (int i = 0; i < sets.length; i++) {
            for (int j = 0; j < sets.length; j++) {
            	if(i!=j)
		            new SetOperationResultStrings(
		                "SET " + (char)(65 + i),
		                "SET " + (char)(65 + j),
		                operationName,
		                operation.apply(sets[i], sets[j])
		            ).print();
            }
        }
        System.out.println();
    }
}