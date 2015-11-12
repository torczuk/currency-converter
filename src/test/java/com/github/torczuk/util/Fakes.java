package com.github.torczuk.util;

import com.github.torczuk.domain.model.Address;
import com.github.torczuk.domain.model.User;

public class Fakes {
    public static final Address ADDRESS = new Address("Cracow", "Main Square 2", "20-001");
    public static final User USER_1 = new User("name", "surname", "rty", "sample@email.com", ADDRESS);
}
