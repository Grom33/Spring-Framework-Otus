package ru.otus.gromov.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(isGetterVisibility = NONE)
@Document()
@Data
public class Genre {
    @Id
    private String id;
    private String name;

    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
