package com.nutri.nutri.FoodJournalEntry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/v1/FoodEntry")
@RequiredArgsConstructor
public class FoodEntryController {
    @Autowired
    FoodEntryService foodEntryService;

    @PostMapping("FoodEntry")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodEntry createNew(@RequestBody FoodEntry entry) {
        return foodEntryService.createNew(entry);
    }
    
    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public FoodEntry updateFoodEntry(@RequestBody FoodEntry entry) {
        return foodEntryService.updateEntry(entry);
    }

    @GetMapping("GetByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodEntry> GetByUser(@RequestParam String id) {
        return foodEntryService.getByUser(id);
    }

    @GetMapping("isPresent")
    @ResponseStatus(HttpStatus.OK)
    public boolean isPresent(@RequestParam String id, @RequestParam String date) {
        return foodEntryService.isPresent(id, date);
    }
    

}
