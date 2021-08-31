package com.koreait.day3_2.repository;

import com.koreait.day3_2.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
    Optional<Category> findByType(String type);

    Optional<Category> findFirstByTitleOrderByIdDesc(String title);

    Optional<Category> findFirstByTypeOrderByIdDesc(String type);
}