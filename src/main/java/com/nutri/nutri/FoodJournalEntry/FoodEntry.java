package com.nutri.nutri.FoodJournalEntry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.nutri.nutri.Food.Food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FoodEntry {
    @Id
    private String id;
    @Indexed

    private LocalDate creationDate;

    private List<Food> foods;
    private int totalCarbs;
    private int totalFat;
    private int totalProtein;
    private String userId;

}
