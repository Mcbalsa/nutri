package com.nutri.nutri.Food;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Food")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Food {
    @Id
    private String id;

    private String name;
    private int fat;
    private int protein;
    private int carbs;
}
