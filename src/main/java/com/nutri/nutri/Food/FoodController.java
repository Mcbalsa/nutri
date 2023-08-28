package com.nutri.nutri.Food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Food")
@RequiredArgsConstructor
public class FoodController {
    @Autowired
    FoodService foodService;

    @PostMapping("Food")
    @ResponseStatus(HttpStatus.CREATED)
    public Food createNew(@RequestBody Food food) {
        return foodService.createNew(food);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getAllFood() {
        return foodService.getAllFood();
    }

    @DeleteMapping("Delete")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteById(@RequestParam String id) {
        return foodService.deleteById(id);
    }

    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public Food updateFood(@RequestBody Food food) {
        return foodService.updateFood(food);
    }

}
