package dev.dekxi.calculator.operations;

import dev.dekxi.calculator.CalculatorApp;
import dev.dekxi.calculator.consoleio.ConsolePrinter;

class SetOperations{
	/*=================
	 * PACKAGE-PRIVATE METHODS
	 * =============*/
	static void createCollectionOfSets() {
	    CalculatorApp.setAllSets(ConsolePrinter.getCollectionOfSets());
	}
	
	static String[] getUnion(String [] a, String[] b) {
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
	static String[] getIntersection(String[] a, String[] b) {
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
	static String[] getSetDifference(String[] a, String[] b) {
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
	static int getSizeOfThePowerset(String[] a) {
		return 1 << a.length;
	}
	static String[][] getPowerset(String[] a){
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
	
	static boolean isSubsetOf(String[] a, String[] b) {
		for(String aVal: a) {
			boolean isInB = false;
			for(String bVal: b) {
				if(aVal.equals(bVal)) {
					isInB = true;
					break;
				}
			}
			if(!isInB)
				return false;
		}
		return true;		
	}
	static boolean isSuperSetOf(String[] a, String[] b) {
		return isSubsetOf(b,a);
	}
	static boolean isDisjoint(String[] a, String[] b) {
		return getIntersection(a,b).length == 0;
	}
	
	
}