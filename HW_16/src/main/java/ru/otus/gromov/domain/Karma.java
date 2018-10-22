package ru.otus.gromov.domain;

public enum Karma {
	SINNER("Sinner soul"),
	SAINT("Saint soul");

	private String soul;

	Karma(String soul) {
		this.soul = soul;
	}
}
