package app.controller;

import app.exceptions.BadRequestException;

public class Validator {

	public static void nullCheck(Object object, String paramName) throws BadRequestException {
		if (object == null) {
			throw new BadRequestException(paramName + " cannot be null");
		}
	}

	public static void emptyValueCheck(String object, String paramName) throws BadRequestException {
		if (object == null || object.isEmpty()) {
			throw new BadRequestException(paramName + " cannot be empty");
		}
	}

	public static void defaultValueCheck(Integer number, String paramName) throws BadRequestException {
		if (number == null || number == 0) {
			throw new BadRequestException(paramName + " cannot have value 0");
		}
	}

	public static void defaultValueCheck(Long number, String paramName) throws BadRequestException {
		if (number == null || number == 0) {
			throw new BadRequestException(paramName + " cannot have value 0");
		}
	}

	public static void loggedInUserCheck(long userId) throws BadRequestException {
		// Temporary fix for testing until authentication module is completed.
//		if(userId != loggedInUser()) {
//			throw new UnauthorizedException("User with id: " + userId + " is not loggedin.");
//		}
		return;
	}

}
