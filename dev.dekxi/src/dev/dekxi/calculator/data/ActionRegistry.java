package dev.dekxi.calculator.data;

/*
 * This acts as a centralized data structure for this program to run
 */
class ActionRegistry{
	/*=================
	 * STATIC FIELDS / OBJECTS
	 * =============*/
	static RegisteredAction[] actionData = new RegisteredAction[0];
	
	/*=================
	 * PUBLIC METHODS
	 * =============*/
	public static String[] getAllActionNames() {
		String[] actionNames = new String[actionData.length];
		int size =0;
		for(RegisteredAction registeredAction: actionData)
			actionNames[size++] = registeredAction.getActionName();
		return actionNames;
	}
	
	/*=================
	 * PACKAGE - PRIVATE METHODS
	 * =============*/
	static void addActionData(String actionName, Runnable action) {
		incrementOperationTableSize();
		actionData[actionData.length-1] = new RegisteredAction(actionName, action);
	}
	static RegisteredAction[] getActions() {
		return actionData;
	}
	static int getActionCount() {
		return actionData.length;
	}
	static RegisteredAction getActionFromIndex(int num) {
		return actionData[num];
	}
	
	/*=================
	 * UTILITY / HELPER METHODS
	 * =============*/
	
	private static void incrementOperationTableSize() {
		RegisteredAction[] temp = new RegisteredAction[actionData.length];
		for(int i=0;i<actionData.length; i ++)
			temp[i] = actionData[i];
		
		actionData = new RegisteredAction[temp.length+1];
		for(int i=0;i<temp.length; i ++)
			actionData[i] = temp[i];
	}
}