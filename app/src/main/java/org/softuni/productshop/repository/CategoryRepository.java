package org.softuni.productshop.repository;

import org.softuni.productshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
