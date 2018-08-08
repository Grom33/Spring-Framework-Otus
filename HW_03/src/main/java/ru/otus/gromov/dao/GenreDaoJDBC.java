package ru.otus.gromov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class GenreDaoJDBC implements GenreDao {
    private final JdbcOperations jdbc;

    @Autowired
    public GenreDaoJDBC(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("SELECT count(*) FROM GENRE", Integer.class);
    }

    @Override
    public boolean insert(Genre genre) {
        return jdbc.update("INSERT INTO GENRE (`NAME`) values (?)", genre.getName()) != 0;
    }

    @Override
    public Genre getById(int id) {
        List<Genre> result = getGenresFromQueryRows(jdbc.queryForList("SELECT * FROM GENRE WHERE GENRE.ID = ?", id));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Genre getByName(String name) {
        List<Genre> result = getGenresFromQueryRows(jdbc.queryForList("SELECT * FROM GENRE WHERE GENRE.NAME = ?", name));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Genre> getAll() {
        return getGenresFromQueryRows(jdbc.queryForList("SELECT * FROM GENRE"));
    }

    @Override
    public boolean delete(int id) {
        return jdbc.update("DELETE FROM GENRE WHERE GENRE.ID = ?", id) != 0;
    }

    @Override
    public List<Genre> getByArrayID(Integer[] arrayOfId) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", Arrays.asList(arrayOfId));
        return getGenresFromQueryRows(namedParameterJdbcTemplate.queryForList("SELECT * FROM GENRE WHERE GENRE.ID IN (:ids)", parameters));

    }

    private List<Genre> getGenresFromQueryRows(List<Map<String, Object>> rows) {
        List<Genre> result = new ArrayList<>();
        for (Map row : rows) {
            Genre genre = new Genre((int) row.get("ID"), (String) row.get("NAME"));
            result.add(genre);
        }
        return result;
    }


    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
