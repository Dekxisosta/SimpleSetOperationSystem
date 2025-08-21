package dev.dekxi.calculator.data.expressions;

record SetOperationResultStrings(String setLabelA,String setLabelB,String setOperationName,
		String[] outcome){
	
	//-->>
	public void print() {printSetOperationResult();}
	
	//-->>
	private void printSetOperationResult() {
		System.out.printf("%n%s OF %s and %s",setOperationName, setLabelA, setLabelB);
		System.out.print(": {");
		printSetValues(outcome);
		System.out.print("}");
	}
	//-->>
	private void printSetValues(String[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i != values.length - 1) 
            	System.out.print(", ");
        }
    }
}

record SetOperationResultBoolean(String setLabelA, String setLabelB, String setOperationName, boolean outcome){
	
	//-->>
	void print() {printIsResultOfAStatement();}
	
	//-->>
	private void printIsResultOfAStatement() {
		System.out.printf("%n%s a %s of %s?%n%s%n", 
				setLabelA, 
				setOperationName,
				setLabelB, 
				String.valueOf(outcome)
				);
	}
}

record PowerSetResult(int size, 
		String[][] subsets){
	
	//-->>
	void print() {printPowerSetResult();}
	
	//-->>
	private void printPowerSetResult() {
		printPowerSet(subsets);
		printSizeOfPowerSet(size);
	}
	
	//-->>
	private void printSizeOfPowerSet(int size) {
		System.out.printf("%nTHE POWER OF THE SET IS: %s", size);
	}
	
	//-->>
	private void printPowerSet(String[][] subsets) {
		int totalString = 0;
		System.out.print("\nTHE SUBSETS OF THE POWER OF THE SET\n");
		
		for(int i = 0; i<subsets.length;i++) {
			System.out.print("{");
			
			for(int j = 0; j<subsets[i].length;j++) {
				System.out.print(subsets[i][j]);
				totalString += subsets[i][j].length();
				
				if(j!=subsets[i].length-1) {
					System.out.print(",");
					totalString+= 2;
				}
			}
			System.out.print("}");
			totalString += 2;
			
			if(totalString>30) {
				totalString=0;
				if(i!=subsets.length-1)
					System.out.println();
			}else {
				if(i!=subsets.length-1)
					System.out.print(", ");
				totalString += 2;
			}
		}
		System.out.println();
	}
}

record LabeledSet (String setLabel, String[] set) {
	//-->>
	void print() {printLabeledSet();}
	
	//-->>
	private void printLabeledSet() {
		System.out.printf("%n%s", setLabel);
		System.out.print(": { ");
		printSetValues(set);
		System.out.print("}");
	}
	
	//-->>
	private void printSetValues(String[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i != values.length - 1) 
            	System.out.print(", ");
        }
    }
}