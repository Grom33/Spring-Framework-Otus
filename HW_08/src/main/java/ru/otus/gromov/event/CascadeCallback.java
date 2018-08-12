package ru.otus.gromov.event;

import java.lang.reflect.Field;
import java.util.Set;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;
import ru.otus.gromov.util.exception.IllegalAnnotationException;


public class CascadeCallback implements ReflectionUtils.FieldCallback {
    private Object source;
    private MongoOperations mongoOperations;

    CascadeCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.setMongoOperations(mongoOperations);
    }

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSaveSetField.class)) {
            if (field.get(getSource()) instanceof Set) {
                final Set fieldValue = (Set) field.get(getSource());
                fieldValue.forEach(f -> {
                    if (f != null) {
                        final ReflectionUtils.FieldCallback callback = new FieldCallback();
                        ReflectionUtils.doWithFields(f.getClass(), callback);
                        getMongoOperations().save(f);
                    }
                });
            } else {
                throw new IllegalAnnotationException("Wrong annotation! Field must be Set!");
            }
        }
    }

    private Object getSource() {
        return source;
    }

    public void setSource(final Object source) {
        this.source = source;
    }

    private MongoOperations getMongoOperations() {
        return mongoOperations;
    }

    private void setMongoOperations(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
}
