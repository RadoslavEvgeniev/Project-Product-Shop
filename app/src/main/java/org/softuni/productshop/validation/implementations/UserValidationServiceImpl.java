package org.softuni.productshop.validation.implementations;

import org.softuni.productshop.domain.models.service.UserServiceModel;
import org.softuni.productshop.validation.UserValidationService;
import org.springframework.stereotype.Component;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}
