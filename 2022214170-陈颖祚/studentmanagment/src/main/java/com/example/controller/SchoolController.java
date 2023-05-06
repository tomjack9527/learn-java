package com.example.controller;
import com.example.dao.SchoolDAO;
import com.example.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private SchoolDAO schoolDAO = new SchoolDAO();

    @PostMapping("/add")
    public ResponseEntity<?> addSchool(@RequestBody School school) {
        schoolDAO.addSchool(school);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchool(@PathVariable("id") int id, @RequestBody School school) {
        School existingSchool = schoolDAO.getSchoolById(id);
        if (existingSchool == null) {
            return ResponseEntity.notFound().build();
        }
        existingSchool.setName(school.getName());
        existingSchool.setCreateTime(school.getCreateTime());
        existingSchool.setId(school.getId());
        existingSchool.setDeleteStatus(school.getDeleteStatus());
        schoolDAO.updateSchool(existingSchool);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchool(@PathVariable("id") int id) {
        School existingSchool = schoolDAO.getSchoolById(id);
        if (existingSchool == null) {
            return ResponseEntity.notFound().build();
        }
        schoolDAO.deleteSchool(existingSchool);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable("id") int id) {
        School school = schoolDAO.getSchoolById(id);
        if (school == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(school);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolDAO.getAllSchools();
        return ResponseEntity.ok(schools);
    }
}