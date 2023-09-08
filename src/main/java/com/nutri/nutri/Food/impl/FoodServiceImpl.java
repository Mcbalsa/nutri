package com.nutri.nutri.Food.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutri.nutri.Food.Food;
import com.nutri.nutri.Food.FoodRepository;
import com.nutri.nutri.Food.FoodService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public Food createNew(Food food) {
        return foodRepository.save(
                Food.builder()
                        .name(food.getName())
                        .fat(food.getFat())
                        .carbs(food.getCarbs())
                        .protein(food.getProtein())
                        .calories(calcCalories(food.getFat(),food.getProtein(), food.getCarbs()))
                        .servings(1.0f)
                        .build()

        );
    }

    @Override
    public Food updateFood(Food food) {
        Optional<Food> existingFood = foodRepository.findById(food.getId());
        if (existingFood.isEmpty())
            throw new RuntimeException(String.format("No food found for id %s", food.getId()));
        existingFood.get().setName(food.getName());
        existingFood.get().setCarbs(food.getCarbs());
        existingFood.get().setFat(food.getFat());
        existingFood.get().setProtein(food.getProtein());
        existingFood.get().setCalories(calcCalories(food.getFat(),food.getProtein(), food.getCarbs()));
        return foodRepository.save(existingFood.get());
    }

    @Override
    public List<Food> getAllFood() {
        List<Food> food = foodRepository.findAll();
        return food;
    }

    @Override
    public Boolean deleteById(String id) {
        foodRepository.deleteById(id);
        if (foodRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    // Based of Formula: Total calories in marcos = (grams of protein * 4) + (grams of fat * 9) + (grams of carbs * 4)
    private int calcCalories(int fat, int protein, int carbs) {
        return (fat * 9) + (protein * 4) + (carbs * 4);
    }
}
