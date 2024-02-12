package org.itstep.classworks.feb.lambda;

import org.itstep.entities.User;

public interface UserFactory {
    User get(String name, String email);
}
