package com.github.torczuk.infrastructure.resource;

import com.github.torczuk.domain.model.Dictionary;
import com.github.torczuk.infrastructure.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryResource {

    @Autowired private DictionaryService dictionaryService;

    @RequestMapping(value = "city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dictionary> cities(HttpServletResponse response) {
        return dictionaryService.cities();
    }

    @RequestMapping(value = "currency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dictionary> currencies(HttpServletResponse response) {
        return dictionaryService.currencies();
    }

}
