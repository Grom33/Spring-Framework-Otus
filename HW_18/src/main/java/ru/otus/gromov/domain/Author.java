package ru.otus.gromov.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
		@NamedQuery(name = Author.GET_COUNT_NAMED_QUERY, query = "SELECT count(a) FROM Author a"),
		@NamedQuery(name = Author.GET_ALL_NAMED_QUERY, query = "SELECT a FROM Author a"),
		@NamedQuery(name = Author.GET_BY_NAME_NAMED_QUERY, query = "SELECT a FROM Author a WHERE a.name=:name"),
})

@Entity
@Table(name = "Author")
public class Author {
	public static final String GET_COUNT_NAMED_QUERY = "Author.getCount";
	public static final String GET_ALL_NAMED_QUERY = "Author.getAll";
	public static final String GET_BY_NAME_NAMED_QUERY = "Author.getByName";

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@NotBlank
	@Size(max = 100)
	private String name;

	@ManyToMany(mappedBy = "authors")
	private Set<Book> books;

	public Author() {
	}

	public Author(Long id, String name) {
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
		return "Author{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Author author = (Author) o;

		if (!id.equals(author.id)) return false;
		return name.equals(author.name);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
}
