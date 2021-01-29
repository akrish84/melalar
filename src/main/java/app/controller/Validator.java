package app.controller;

public class Validator {
	private boolean isValid;
	private String errorMessage;

	
	public Validator() {
		isValid = true;
		errorMessage = "";
	}
	
	/**
	 * Sets isValid flag to false if object is null and sets errorMessage. else sets
	 * isValid to true
	 * 
	 * @param object
	 * @param errorMessage
	 * @return instance of Validator
	 */
	public Validator addNullCheck(Object object, String errorMessage) {
		if (object == null) {
			isValid = false;
			this.errorMessage = errorMessage;
		} else {
			isValid = true;
		}
		return this;
	}

	/**
	 * Sets isValid flag to false if object is null or object is empty and sets
	 * errorMessage. else sets isValid to true
	 * 
	 * @param object
	 * @param errorMessage
	 * @return instance of Validator
	 */
	public Validator addEmptyCheck(String object, String errorMessage) {
		if (object == null || object.isEmpty()) {
			isValid = false;
			this.errorMessage = errorMessage;
		} else {
			isValid = true;
		}
		return this;
	}

	/**
	 * Sets isValid flag to false if number is null or has default value 0 and sets
	 * errorMessage. else sets isValid to true
	 * 
	 * @param number
	 * @param errorMessage
	 * @return
	 */
	public Validator addDefaultValueCheck(Integer number, String errorMessage) {
		if (number == null || number == 0) {
			isValid = false;
			this.errorMessage = errorMessage;
		} else {
			isValid = true;
		}
		return this;
	}

	/**
	 * Sets isValid flag to false if number is null or has default value 0 and sets
	 * errorMessage. else sets isValid to true
	 * 
	 * @param number
	 * @param errorMessage
	 * @return
	 */
	public Validator addDefaultValueCheck(Long number, String errorMessage) {
		if (number == null || number == 0) {
			isValid = false;
			this.errorMessage = errorMessage;
		} else {
			isValid = true;
		}
		return this;
	}

	/**
	 * Sets isValid flag to false if object cannot be parsed as integer and sets
	 * errorMessage. else sets isValid to true
	 * 
	 * @param object
	 * @param errorMessage
	 * @return
	 */
	public Validator addIntInstanceCheck(String object, String errorMessage) {
		try {
			Integer.parseInt(object);
			isValid = true;
		} catch (NumberFormatException e) {
			isValid = false;
			this.errorMessage = errorMessage;
		}
		return this;
	}

	/**
	 * Sets isValid flag to false if object cannot be parsed as long and sets
	 * errorMessage. else sets isValid to true
	 * 
	 * @param object
	 * @param errorMessage
	 * @return
	 */
	public Validator addLongInstanceCheck(String object, String errorMessage) {
		try {
			Long.parseLong(object);
			isValid = true;
		} catch (NumberFormatException e) {
			isValid = false;
			this.errorMessage = errorMessage;
		}
		return this;
	}

	/**
	 * Sets isValid flag to false if userId does not match logged in user's id and
	 * sets error Message. else sets isValid to true
	 * 
	 * @param userId
	 * @param errorMessage
	 * @return
	 */
	public Validator addLoggedInUserCheck(long userId, String errorMessage) {
		// Temporary fix for testing until authentication module is completed.
		isValid = true;
		return this;
	}

	/**
	 * 
	 * Returns true or false based on the validations
	 * 
	 * @return True if all validations were true, else returns False
	 */
	public boolean isValid() {
		return this.isValid;
	}

	/**
	 * 
	 * Returns the error message set when a validation fails.
	 * 
	 * @return errorMessage set during failed validation.
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
