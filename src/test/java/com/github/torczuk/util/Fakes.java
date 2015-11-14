package com.github.torczuk.util;

import com.github.torczuk.domain.model.Address;
import com.github.torczuk.domain.model.Dictionary;
import com.github.torczuk.domain.model.User;

import java.util.List;

import static java.util.Arrays.asList;

public class Fakes {
    public static final Address ADDRESS = new Address("Cracow", "Main Square 2", "20-001");
    public static final User USER_1 = new User("name", "surname", "rty", "sample@email.com", ADDRESS);
    public static final Dictionary CITY_1 = new Dictionary("city", "KRK", "Cracow");
    public static final Dictionary CITY_2 = new Dictionary("city", "BER", "Berlin");

    public static final Dictionary CURRENCY_1 = new Dictionary("currency", "PLN", "Polish Zloty");
    public static final Dictionary CURRENCY_2 = new Dictionary("currency", "EUR", "Euro");

    public static List<Dictionary> cities() {
        return asList(CITY_1, CITY_2);
    }

    public static List<Dictionary> dictionaries() {
        return asList(CURRENCY_1, CURRENCY_2);
    }

    public static String CURRENCY_LAYER_RESPONSE = "{\n" +
            "    \"timestamp\": 1447516809,\n" +
            "    \"source\": \"USD\",\n" +
            "    \"quotes\": {\n" +
            "        \"USDPLN\": 3.94005,\n" +
            "        \"USDEUR\": 0.928592\n" +
            "    }\n" +
            "}";
}
