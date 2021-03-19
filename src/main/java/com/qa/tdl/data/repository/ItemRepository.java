package com.qa.tdl.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl.data.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}