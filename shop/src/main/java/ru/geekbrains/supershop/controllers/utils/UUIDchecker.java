package ru.geekbrains.supershop.controllers.utils;

import java.util.regex.Pattern;

public class UUIDchecker {

	public static void check(String id) throws IllegalArgumentException {
		Pattern pattern = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");

		if (!pattern.matcher(id).matches()) {
			throw new IllegalArgumentException();
		}
	}
}
