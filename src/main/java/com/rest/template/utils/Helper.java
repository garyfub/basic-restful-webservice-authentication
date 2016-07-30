package com.rest.template.utils;

public final class Helper {
	
	public static String removeSpecialCharacters(String input) {
		return input.replaceAll("[^a-zA-Z0-9_-]", "");
	}

}
