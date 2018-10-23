package ru.otus.gromov.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	transient Soul soul;

	public Person(Soul soul) {
		this.soul = soul;
	}
}
