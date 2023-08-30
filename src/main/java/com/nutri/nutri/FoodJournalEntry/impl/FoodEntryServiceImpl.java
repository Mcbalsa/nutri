package com.nutri.nutri.FoodJournalEntry.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutri.nutri.FoodJournalEntry.FoodEntry;
import com.nutri.nutri.FoodJournalEntry.FoodEntryRepository;
import com.nutri.nutri.FoodJournalEntry.FoodEntryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//Do not forget the @Service in impl
@Service
@RequiredArgsConstructor
@Slf4j
public class FoodEntryServiceImpl implements FoodEntryService {

    @Autowired
    FoodEntryRepository foodEntryRepository;

    @Override
    public FoodEntry createNew(FoodEntry entry) {
        return foodEntryRepository.save(
            FoodEntry.builder()
                .totalCarbs(entry.getTotalCarbs())
                .totalFat(entry.getTotalFat())
                .totalProtein(entry.getTotalProtein())
                .userId(entry.getUserId())
                .foods(entry.getFoods())
                .build()
        );
        
    }

    @Override
    public FoodEntry updateEntry(FoodEntry entry) {
        Optional <FoodEntry> existingEntry =  foodEntryRepository.findById(entry.getId());
        existingEntry.get().setTotalCarbs(entry.getTotalCarbs());
        existingEntry.get().setTotalFat(entry.getTotalFat());
        existingEntry.get().setTotalProtein(entry.getTotalProtein());
        existingEntry.get().setFoods(entry.getFoods());
        return foodEntryRepository.save(existingEntry.get());
        
    }

    @Override
    public List<FoodEntry> getByUser(String id) {
        List<FoodEntry> journal = foodEntryRepository.findByUserId(id);

        return journal;
    }
    
    
}
