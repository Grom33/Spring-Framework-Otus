package ru.otus.gromov.domain;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
		@NamedQuery(name = Genre.GET_COUNT_NAMED_QUERY, query = "SELECT count(g) FROM Genre g"),
		@NamedQuery(name = Genre.GET_ALL_NAMED_QUERY, query = "SELECT g FROM Genre g"),
		@NamedQuery(name = Genre.GET_BY_NAME_NAMED_QUERY, query = "SELECT g FROM Genre g WHERE g.name=:name"),
})

@Entity
public class Genre {
	public static final String GET_COUNT_NAMED_QUERY = "Genre.getCount";
	public static final String GET_ALL_NAMED_QUERY = "Genre.getAll";
	public static final String GET_BY_NAME_NAMED_QUERY = "Genre.getByName";

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "genres", cascade =
			{CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Book> books;

	public Genre() {
	}

	public Genre(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isNew() {
		return this.id < 1;
	}

	@Override
	public String toString() {
		return "Genre{" +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Genre genre = (Genre) o;

		if (!id.equals(genre.id)) return false;
		return name.equals(genre.name);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
}
