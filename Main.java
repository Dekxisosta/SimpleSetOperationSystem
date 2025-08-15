package dev.dekxi;
import java.util.Scanner;

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
	 * Also, map it on the actionMap method by adding an incremented index for the action
	 * you wish to add
 */

public class Main {
	static boolean continueProgram = true;
	
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
		registerActions();
		while(continueProgram) {
			Console.printBanner();
			int choice = Console.printMenu();
			if (!(choice==0)) {
				actionMap(choice);
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
		DataStore.addActionData("UNION OF SETS", () -> {
			String[] a = Console.getStringArray("SET A");
			String[] b = Console.getStringArray("SET B");
			Console.printStringArray("UNION", getUnion(a,b));
		});
		DataStore.addActionData("INTERSECTION OF SETS", () ->{
			String[] a = Console.getStringArray("SET A");
			String[] b = Console.getStringArray("SET B");
			Console.printStringArray("INTERSECTION", getIntersection(a,b));
		});
		DataStore.addActionData("SET DIFFERENCE OF SETS", () -> {
			String[] a = Console.getStringArray("SET A");
			String[] b = Console.getStringArray("SET B");
			Console.printStringArray("SET DIFFERENCE A TO B", getSetDifference(a,b));
			Console.printStringArray("SET DIFFERENCE B TO A", getSetDifference(b,a));
		});
		DataStore.addActionData("POWER OF A SET", () ->{
			String[] a = Console.getStringArray("the SET");
			Console.printPowerOfTheSet(getPowerOfTheSet(a));
			Console.printSubsetsOfTheSet(getSubsetsOfTheSet(a));
		});
	}
	/*
	 * Instead of a hash map, given that a fixed decision structure like switch-case is faster.
	 * This one is utilized. For addActionData to actually mirror changes in the console output,
	 * simply copy-paste the getActionFromIndex.run method and add an incremented index
	 */
	private static void actionMap(int choice) {
		switch(choice) {
		case 1:
			DataStore.getActionFromIndex(0).run();
			break;
		case 2:
			DataStore.getActionFromIndex(1).run();
			break;
		case 3:
			DataStore.getActionFromIndex(2).run();
			break;
		case 4:
			DataStore.getActionFromIndex(3).run();
		}
	}
	/*=================
	 * DEFAULT SET OPERATIONS
	 * =============*/
	private static String[] getUnion(String [] a, String[] b) {
		String[] temp = new String[a.length + b.length];
		int size = 0;
		
		for(String aVal: a) temp[size++] = aVal;
		
		for(String bVal: b) {
			boolean isInTemp = false;
			for(String tempVal: temp)
				if(bVal.equals(tempVal)) isInTemp = true;
			
			if(!isInTemp) temp[size++] = bVal;
		}
		
		String[] union = new String[size];
		for (int i=0;i<size;i++) union[i] = temp[i];
		
		return union;
	}
	private static String[] getIntersection(String[] a, String[] b) {
		String[] temp = new String[Math.min(a.length, b.length)];
		int size = 0;
		
		for(String aVal: a) {
			boolean isInB = false;
			for(String bVal: b)
				if(aVal.equals(bVal))
					isInB = true;
			if(isInB) {
				boolean isInTemp = false;
				for(String tempVal: temp)
					if(aVal.equals(tempVal))
						isInTemp = true;
				
				if (!isInTemp)
					temp[size++] = aVal;
			}
		}
		
		String[] intersection = new String[size];
		for(int i = 0;i<size;i++) intersection[i] = temp[i];
			
		return intersection;
	}
	private static String[] getSetDifference(String[] a, String[] b) {
		String[] temp = new String[a.length];
		int size = 0;
		
		for(String aVal: a) {
			boolean isInB = false;
			for(String bVal: b)
				if(aVal.equals(bVal))
					isInB = true;
			if(!isInB) {
				boolean isInTemp = false;
				for(String tempVal: temp)
					if(aVal.equals(tempVal))
						isInTemp = true;
				
				if (!isInTemp)
					temp[size++] = aVal;
			}
		}
		
		String[] setDifference = new String[size];
		for(int i = 0;i<size;i++) setDifference[i] = temp[i];
			
		return setDifference;
	}
	private static int getPowerOfTheSet(String[] a) {
		return 1 << a.length;
	}
	private static String[][] getSubsetsOfTheSet(String[] a){
       int n = a.length;
       int total = 1 << n;
       String[][] result = new String[total][];
      
       for (int mask = 0; mask < total; mask++) {
           int count = 0;
           for (int i = 0; i < n; i++) {
               if ((mask & (1 << i)) != 0) count++;
           }
           String[] subset = new String[count];
           int idx = 0;
           for (int i = 0; i < n; i++) {
               if ((mask & (1 << i)) != 0) {
                   subset[idx++] = String.valueOf(a[i]);
               }
           }
           result[mask] = subset;
       }
       return result;
	}
}	

/*
 * This acts as a centralized data structure for this program to run
 */
class DataStore{
	/*=================
	 * STATIC FIELDS / OBJECTS
	 * =============*/
	static Action[] actionData = new Action[0];
	
	/*=================
	 * EXPOSED API / PUBLIC FUNCTIONS
	 * =============*/
	public static void addActionData(String actionName, Runnable action) {
		incrementOperationTableSize();
		actionData[actionData.length-1] = new Action(actionName, action);
	}
	public static Action[] getActions() {
		return actionData;
	}
	public static Action getActionFromIndex(int num) {
		return actionData[num];
	}
	public static String[] getAllActionNames() {
		String[] actionNames = new String[actionData.length];
		int size =0;
		for(Action action: actionData)
			actionNames[size++] = action.getActionName();
		return actionNames;
	}
	/*=================
	 * UTILITY / HELPER FUNCTIONS
	 * =============*/
	private static void incrementOperationTableSize() {
		Action[] temp = new Action[actionData.length];
		for(int i=0;i<actionData.length; i ++)
			temp[i] = actionData[i];
		
		actionData = new Action[temp.length+1];
		for(int i=0;i<temp.length; i ++)
			actionData[i] = temp[i];
	}
}
/*
 * This acts mainly as a constructor for an Action with getter functions
 */
class Action{
	/*=================
	 * INSTANCE FIELDS
	 * =============*/
	String actionName;
	Runnable action;
	int index;
	
	/*=================
	 * CONSTRUCTOR
	 * =============*/
	public Action(String actionName, Runnable action) {
		this.actionName = actionName;
		this.action = action;
		this.index = (DataStore.getActions().length);
	}
	/*=================
	 * GETTERS / ACCESSORS
	 * =============*/
	public String getActionName() {
		return this.actionName;
	}
	/*=================
	 * ACTION METHODS
	 * =============*/
	public void run() {
		action.run();
	}
}
/*
 * The Console class allows for scanning and printing out of data in a specified format
 */
class Console{
	/*=================
	 * STATIC FIELDS / OBJECTS
	 * =============*/
	static Scanner scInt = new Scanner(System.in);
	static Scanner scLine = new Scanner(System.in);
	static final String SYSTEM_NAME = "Dev Dekxi's Set Operation System";
	
	/*=================
	 * EXPOSED API / PUBLIC FUNCTIONS
	 * =============*/
	public static int printMenu() {
		String[] actionNames = DataStore.getAllActionNames();
		
		for(int i=0;i<actionNames.length;i++)
			System.out.print("\n[" + (i+1) + "]" + " " + actionNames[i]);
		
		System.out.print("\n[0] Terminate Program");
		
		int choice = getValidInt(0,actionNames.length);
		
		return choice;
	}
	
	public static void printBanner() {
		System.out.printf("%s%s%s%n",
				"+++",
				"=".repeat(SYSTEM_NAME.length()),
				"+++"
				);
		System.out.printf("%s%n",
				"{| " + SYSTEM_NAME + " |}");
		System.out.printf("%s%s%s",
				"+++",
				"=".repeat(SYSTEM_NAME.length()),
				"+++"
				);
	}
	public static void printPowerOfTheSet(int a) {
		System.out.printf("%nTHE POWER OF THE SET IS: %s", a);
	}
	
	public static void printSubsetsOfTheSet(String[][] a) {
		int totalString = 0;
		System.out.print("\nTHE SUBSETS OF THE POWER OF THE SET\n");
		
		for(int i = 0; i<a.length;i++) {
			System.out.print("{");
			
			for(int j = 0; j<a[i].length;j++) {
				System.out.print(a[i][j]);
				totalString += a[i][j].length();
				
				if(j!=a[i].length-1) {
					System.out.print(",");
					totalString+= 2;
				}
			}
			System.out.print("}");
			totalString += 2;
			
			if(totalString>30) {
				totalString=0;
				if(i!=a.length-1)
					System.out.println();
			}else {
				if(i!=a.length-1)
					System.out.print(", ");
				totalString += 2;
			}
		}
		System.out.println();
	}
	public static void printStringArray(String operationUsed, String[] set) {
		System.out.print("\n" + operationUsed + "\n");
		System.out.print("{ ");
		for(String val: set) System.out.print(val + " ");
		System.out.print("}\n\n");
	}
	
	public static String[] getStringArray(String setName) {
		int numOfValues = getNumOfInputs(setName);
		String[] arr = getArrayOfInputs(numOfValues);
		
		return arr;
	}
	
	/*=================
	 * UTILITY / PRIVATE FUNCTIONS
	 * =============*/
	private static String[] getArrayOfInputs(int numOfValues) {
		String[] inputs = new String[numOfValues];
		int size = 0;
		
		while(size< numOfValues) {
			System.out.print("Enter [" + size + "]: ");
			String input = getStringInput();
			inputs[size++] = input;
		}
		
		return inputs;
	}
	private static int getNumOfInputs(String setName) {
		System.out.print("\nHow many inputs in " + setName + "? ");
		int choice = getValidInt(3,20);
		
		return choice;
		
	}
	private static int getValidInt(int min, int max) {
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
	private static void invalidIntegerFallback(int min, int max){
		System.out.println("[ERROR] Enter a valid integer from " + min + " to " + max + ": ");
		System.out.print("Enter: ");
	}
	private static String getStringInput() {
		String a;
		while(true) {
			try {
				a = scLine.nextLine();
				break;
			}catch(Exception e) {
				e.printStackTrace();
				scLine.next();
			}
		}
		return a;
	}
}

