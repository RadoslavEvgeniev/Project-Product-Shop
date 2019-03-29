package org.softuni.productshop.validation;

import org.softuni.productshop.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}
