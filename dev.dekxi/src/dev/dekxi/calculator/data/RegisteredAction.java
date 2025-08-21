package dev.dekxi.calculator.data;

/*
 * This acts mainly as a constructor for an Action with getter functions
 */
class RegisteredAction{
	/*=================
	 * INSTANCE FIELDS
	 * =============*/
	String actionName;
	Runnable action;
	int index;
	
	/*=================
	 * CONSTRUCTOR
	 * =============*/
	RegisteredAction(String actionName, Runnable action) {
		this.actionName = actionName;
		this.action = action;
		this.index = (ActionRegistry.getActions().length);
	}
	/*=================
	 * GETTERS / ACCESSORS
	 * =============*/
	String getActionName() {
		return this.actionName;
	}
	/*=================
	 * ACTION METHODS
	 * =============*/
	void run() {
		action.run();
	}
}