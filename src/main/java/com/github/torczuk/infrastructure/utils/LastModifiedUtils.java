package com.github.torczuk.infrastructure.utils;

import com.github.torczuk.domain.model.CreationDateAware;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class LastModifiedUtils {
    private static final Date BEGINNING_OF_THE_WORLD = new Date(0L);

    public static Date lastModified(List<? extends CreationDateAware> entities) {
        Optional<Date> min = entities.stream().map(CreationDateAware::creationDate).max(Date::compareTo);
        return min.orElse(BEGINNING_OF_THE_WORLD);
    }
}
