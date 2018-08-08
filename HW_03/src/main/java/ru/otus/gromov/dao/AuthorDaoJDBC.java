package ru.otus.gromov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AuthorDaoJDBC implements AuthorDao {

    private final JdbcOperations jdbc;

    @Autowired
    public AuthorDaoJDBC(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("SELECT count(*) FROM AUTHOR", Integer.class);
    }

    @Override
    public boolean insert(Author author) {
        return jdbc.update("INSERT INTO AUTHOR (`NAME`) values (?)", author.getName()) != 0;
    }

    @Override
    public Author getById(int id) {
        List<Author> result = getAuthorsFromQueryRows(jdbc.queryForList("SELECT * FROM AUTHOR WHERE AUTHOR.ID = ?", id));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Author> getAll() {
        return getAuthorsFromQueryRows(jdbc.queryForList("SELECT * FROM AUTHOR"));
    }

    @Override

    public Author getByName(String name) {
        List<Author> result = getAuthorsFromQueryRows(jdbc.queryForList("SELECT * FROM author WHERE name = ?", name));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public boolean delete(int id) {
        return jdbc.update("DELETE FROM AUTHOR WHERE AUTHOR.ID = ?", id) != 0;
    }

    @Override
    public List<Author> getByArrayID(Integer[] arrayOfId) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", Arrays.asList(arrayOfId));
        return getAuthorsFromQueryRows(
                namedParameterJdbcTemplate.queryForList("SELECT * FROM AUTHOR WHERE AUTHOR.ID IN ( :ids )", parameters));
    }

    private List<Author> getAuthorsFromQueryRows(List<Map<String, Object>> rows) {
        List<Author> result = new ArrayList<>();
        for (Map row : rows) {
            Author author = new Author();
            author.setId((int) row.get("ID"));
            author.setName((String) row.get("NAME"));
            result.add(author);
        }
        return result;
    }

    public static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

}
