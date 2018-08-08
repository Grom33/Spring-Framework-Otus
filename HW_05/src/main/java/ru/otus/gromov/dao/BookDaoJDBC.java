package ru.otus.gromov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.util.SetUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Repository
public class BookDaoJDBC implements BookDao {
    private final JdbcOperations jdbc;

    @Autowired
    public BookDaoJDBC(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("SELECT count(*) FROM BOOK", Integer.class);
    }

    @Override
    public boolean insert(Book book) {
        return jdbc.update("INSERT INTO BOOK (`NAME`, `GENRES`,`AUTHORS`) values (?, ?, ?)",
                book.getName(),
                SetUtil.getStringFromSet(book.getGenres()),
                SetUtil.getStringFromSet(book.getAuthors())) != 0;
    }

    @Override
    public boolean update(Book book) {
        return jdbc.update("UPDATE BOOK SET BOOK.NAME = ?, BOOK.GENRES = ?, BOOK.AUTHORS = ? WHERE BOOK.ID = ?",
                book.getName(),
                SetUtil.getStringFromSet(book.getGenres()),
                SetUtil.getStringFromSet(book.getAuthors()),
                book.getId()) != 0;
    }

    @Override
    public Book getById(int id) {
        List<Book> result = getBooksFromQueryRows(jdbc.queryForList("SELECT * FROM BOOK WHERE BOOK.ID = ?", id));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Book getByName(String name) {
        List<Book> result = getBooksFromQueryRows(jdbc.queryForList("SELECT * FROM BOOK WHERE name = ?", name));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Book> getAll() {
        return getBooksFromQueryRows(jdbc.queryForList("SELECT * FROM BOOK"));
    }

    @Override
    public boolean delete(int id) {
        return jdbc.update("DELETE FROM BOOK WHERE BOOK.ID = ?", id) != 0;
    }

    private List<Book> getBooksFromQueryRows(List<Map<String, Object>> rows) {
        List<Book> result = new ArrayList<>();
        for (Map row : rows) {
            Book book = new Book((int) row.get("ID"), (String) row.get("NAME"));
            book.setAuthors(SetUtil.getSetFromString((String) row.get("AUTHORS")));
            book.setGenres(SetUtil.getSetFromString((String) row.get("GENRES")));
            result.add(book);
        }
        return result;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Set<Integer> genres = SetUtil.getSetFromString(resultSet.getString("genre"));
            Set<Integer> author = SetUtil.getSetFromString(resultSet.getString("authors"));
            return new Book(id, name, genres, author);
        }

    }

}
