package com.github.torczuk.infrastructure.resource;

import com.github.torczuk.domain.model.Dictionary;
import com.github.torczuk.domain.service.DictionaryService;
import com.github.torczuk.infrastructure.utils.LastModifiedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;

import static com.github.torczuk.infrastructure.utils.LastModifiedUtils.lastModified;
import static org.springframework.http.HttpHeaders.CACHE_CONTROL;


@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryResource {

    @Autowired private DictionaryService dictionaryService;

    @RequestMapping(value = "city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cities(WebRequest request) {
        List<Dictionary> cities = dictionaryService.cities();

        String etag = Integer.toString(cities.hashCode());
        if (request.checkNotModified(etag)) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        return response(cities);
    }

    @RequestMapping(value = "currency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> currencies(WebRequest request) {
        List<Dictionary> currencies = dictionaryService.currencies();

        String etag = Integer.toString(currencies.hashCode());
        if (request.checkNotModified(etag)) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        return response(currencies);
    }

    private ResponseEntity<?> response(List<Dictionary> dictionaries) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.lastModified(lastModified(dictionaries).getTime());
        builder.header(CACHE_CONTROL, "max-age:86400", "public");
        return builder.body(dictionaries);
    }

}
