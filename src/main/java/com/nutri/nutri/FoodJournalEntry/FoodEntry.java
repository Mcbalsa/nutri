package com.nutri.nutri.FoodJournalEntry;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
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
    @Id
    private String id;
    @Indexed
    @CreatedDate
    private Date creationDate = new Date();

    private List<FoodEntryData> foods;
    private int totalCarbs;
    private int totalFat;
    private int totalProtein;
    private String userId;

}
