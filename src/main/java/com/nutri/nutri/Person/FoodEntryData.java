package com.nutri.nutri.Person;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.nutri.nutri.Food.Food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FoodEntryData {
    @DBRef
    Food food;
    int servingSize;
}
