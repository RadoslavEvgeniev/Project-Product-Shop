package org.softuni.productshop.validation.category;

import org.softuni.productshop.domain.models.binding.CategoryAddBindingModel;
import org.softuni.productshop.domain.models.binding.CategoryEditBindingModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryEditValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryEditBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryEditBindingModel categoryEditBindingModel = (CategoryEditBindingModel) o;

        if (categoryEditBindingModel.getName() == null) {
            errors.rejectValue("name", "Name cannot be Null!", "Name cannot be Null!");
        }

        if (categoryEditBindingModel.getName().equals("")) {
            errors.rejectValue("name", "Name cannot be Empty!", "Name cannot be Empty!");
        }

        if (categoryEditBindingModel.getName().length() < 3) {
            errors.rejectValue("name", "Name must contain at least 3 characters!", "Name must contain at least 3 characters!");
        }
    }
}
