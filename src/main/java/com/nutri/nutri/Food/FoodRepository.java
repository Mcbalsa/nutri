package com.nutri.nutri.Food;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String>{
    List<Food> findByName(String name);
}
