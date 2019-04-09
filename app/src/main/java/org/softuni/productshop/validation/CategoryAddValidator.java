package org.softuni.productshop.validation;

import org.softuni.productshop.domain.models.binding.CategoryAddBindingModel;
import org.softuni.productshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryAddValidator implements Validator {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryAddValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryAddBindingModel categoryAddBindingModel = (CategoryAddBindingModel) o;

        if (categoryAddBindingModel.getName() == null) {
            errors.rejectValue("name", "Name cannot be Null!", "Name cannot be Null!");
        }

        if (categoryAddBindingModel.getName().equals("")) {
            errors.rejectValue("name", "Name cannot be Empty!", "Name cannot be Empty!");
        }

        if (categoryAddBindingModel.getName().length() < 3) {
            errors.rejectValue("name", "Name must contain at least 3 characters!", "Name must contain at least 3 characters!");
        }

        this.categoryRepository.findByName(categoryAddBindingModel.getName())
                .ifPresent((c) -> errors.rejectValue("name", "Category already exists!", "Category already exists!"));
    }
}
