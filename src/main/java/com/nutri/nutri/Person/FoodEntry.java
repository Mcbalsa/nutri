package com.nutri.nutri.Person;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FoodEntry {
    @Indexed
    @CreatedDate
    private final Date creationDate = new Date();
    
    List<FoodEntryData> foods;
    int totalCarbs;
    int totalFat;
    int totalProtein;
    
}
