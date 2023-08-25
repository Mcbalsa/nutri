package com.nutri.nutri.Food;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface FoodService {
    Food createNew(Food food);
    Food updateFood(Food food);
    List<Food> getAllFood();
    Boolean deleteById(String id);
}
