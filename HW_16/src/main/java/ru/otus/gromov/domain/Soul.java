package ru.otus.gromov.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Soul")
public class Soul {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String name;

	int sins;

	public Soul(String name, int sins) {
		this.name = name;
		this.sins = sins;
	}
}

