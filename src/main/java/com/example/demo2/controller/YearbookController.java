package com.example.demo2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.demo2.exception.ResourceNotFoundException;
import com.example.demo2.model.Yearbook;
import com.example.demo2.repository.YearbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class YearbookController {

    @Autowired
    private YearbookRepository yearbookRepository;

    @GetMapping("yearbooks")

    public List<Yearbook> getAllYearBook(){
        return this.yearbookRepository.findAll();
    }

    @GetMapping("/yearbooks/{id}")
    public ResponseEntity<Yearbook> getYearbookById(@PathVariable(value = "id") Long yearbookId)
            throws ResourceNotFoundException {
        Yearbook yearbook = yearbookRepository.findById(yearbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Yearbook not found for this id :: " + yearbookId));
        return ResponseEntity.ok().body(yearbook);
    }

    @PostMapping("yearbooks")
    public Yearbook createYearbook(@RequestBody Yearbook yearbook){
        return this.yearbookRepository.save(yearbook);
    }

    @PutMapping("/yearbooks/{id}")
    public ResponseEntity<Yearbook> updateYearbook(@PathVariable(value = "id") Long yearbookId,
                                                   @Valid @RequestBody Yearbook yearbookDetails)throws ResourceNotFoundException {
        Yearbook yearbook = yearbookRepository.findById(yearbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Yearbook not found for this id :: " + yearbookId));
        yearbook.setSchoolName(yearbookDetails.getSchoolName());
        yearbook.setYear(yearbookDetails.getYear());
        yearbook.setOrdered(yearbookDetails.getOrdered());
        return ResponseEntity.ok(this.yearbookRepository.save(yearbook));
    }

    @DeleteMapping("/yearbooks/{id}")
    public Map<String, Boolean> deleteYearbook(@PathVariable(value = "id") Long yearbookId)
        throws ResourceNotFoundException {
        Yearbook yearbook = yearbookRepository.findById(yearbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Yearbook not found for this id :: " + yearbookId));

        yearbookRepository.delete(yearbook);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
