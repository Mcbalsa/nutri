package com.nutri.nutri.FoodJournalEntry;

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
    private Food food;
    private int servingSize;
}
