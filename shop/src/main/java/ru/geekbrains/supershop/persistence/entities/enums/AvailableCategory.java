package ru.geekbrains.supershop.persistence.entities.enums;


import lombok.Getter;

public enum  AvailableCategory {
	AVAILABLE ("В наличии"),
	NOT_AVAILABLE ("Нет в наличии");

	@Getter
	private String name;

	AvailableCategory(String name) {
		this.name = name;
	}

}
