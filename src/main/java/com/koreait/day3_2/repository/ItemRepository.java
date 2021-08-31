package com.koreait.day3_2.repository;

import com.koreait.day3_2.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findById(Long id);
    Optional<Item> findByContent(String content);
    Optional<Item> findFirstByNameOrderByIdDesc(String name);
    Optional<Item> findFirstByTitleOrderByPriceDesc(String title);

}
