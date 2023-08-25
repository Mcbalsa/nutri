package com.nutri.nutri.Food;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface FoodRepository extends MongoRepository<Food, String>{
    Optional <Food> findByname(String username);
}
