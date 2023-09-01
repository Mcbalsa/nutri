package com.nutri.nutri.FoodJournalEntry;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface FoodEntryService {
    FoodEntry createNew(FoodEntry entry);
    FoodEntry updateEntry(FoodEntry entry);
    List<FoodEntry> getByUser(String id);
    FoodEntry getByDate(String id, String date);
    boolean isPresent(String id, String date);
}
