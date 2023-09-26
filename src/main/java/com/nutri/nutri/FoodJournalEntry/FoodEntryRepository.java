package com.nutri.nutri.FoodJournalEntry;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface FoodEntryRepository extends MongoRepository<FoodEntry, String>{
    List<FoodEntry> findByUserId(String userId);
}
