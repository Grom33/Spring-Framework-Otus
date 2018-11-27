package ru.otus.gromov.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
		@NamedQuery(name = Book.GET_COUNT_NAMED_QUERY, query = "SELECT count(b) FROM Book b"),
		@NamedQuery(name = Book.GET_ALL_NAMED_QUERY, query = "SELECT b FROM Book b"),
		@NamedQuery(name = Book.GET_BY_NAME_NAMED_QUERY, query = "SELECT b FROM Book b WHERE b.name=:name"),
})

@Entity
@Table(name = "Book")
public class Book {

	public static final String GET_COUNT_NAMED_QUERY = "Book.getCount";
	public static final String GET_ALL_NAMED_QUERY = "Book.getAll";
	public static final String GET_BY_NAME_NAMED_QUERY = "Book.getByName";

	@Version
	private Long version;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	@Column(name = "name", nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade =
			{CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_genre",
			joinColumns = {@JoinColumn(name = "book_id")},
			inverseJoinColumns = {@JoinColumn(name = "genre_id")})
	@Column(name = "genres")
	private Set<Genre> genres;

	@ManyToMany(fetch = FetchType.EAGER, cascade =
			{CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_author",
			joinColumns = {@JoinColumn(name = "book_id")},
			inverseJoinColumns = {@JoinColumn(name = "author_id")})
	@Column(name = "authors")
	private Set<Author> authors;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
			mappedBy = "book", orphanRemoval = true)
	private List<Comment> comments;

	public Book() {
	}

	public Book(Long id, String name, Set<Genre> genres, Set<Author> authors, List<Comment> comments) {
		this.id = id;
		this.name = name;
		this.genres = genres;
		this.authors = authors;
		this.comments = comments;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public Set<Genre> getGenres() {
		return this.genres;
	}

	public Set<Author> getAuthors() {
		return this.authors;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addAuthor(Author author) {
		this.authors.add(author);
	}

	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public boolean isNew() {
		return !(this.id > 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (!id.equals(book.id)) return false;
		if (!name.equals(book.name)) return false;
		if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
		return authors != null ? authors.equals(book.authors) : book.authors == null;
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + (genres != null ? genres.hashCode() : 0);
		result = 31 * result + (authors != null ? authors.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Book{" +
				", id=" + id +
				", name='" + name + '\'' +
				", genres=" + genres +
				", authors=" + authors +
				'}';
	}
}
