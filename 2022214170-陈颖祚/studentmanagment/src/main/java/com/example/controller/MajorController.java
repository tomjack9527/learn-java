package com.example.controller;
import com.example.dao.MajorDAO;
import com.example.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/major")
public class MajorController {

    private MajorDAO majorDAO = new MajorDAO();

    @PostMapping("/add")
    public ResponseEntity<Void> addMajor(@RequestBody Major major) {
        majorDAO.addMajor(major);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMajor(@PathVariable("id") int id, @RequestBody Major major) {
        Major existingMajor = majorDAO.getMajorById(id);
        if (existingMajor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingMajor.setName(major.getName());
        existingMajor.setCreateTime(major.getCreateTime());
        existingMajor.setDeleteStatus(major.getDeleteStatus());

        majorDAO.updateMajor(existingMajor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMajor(@PathVariable("id") int id) {
        Major existingMajor = majorDAO.getMajorById(id);
        if (existingMajor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        majorDAO.deleteMajor(existingMajor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Major> getMajorById(@PathVariable("id") int id) {
        Major major = majorDAO.getMajorById(id);
        if (major == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(major, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Major>> getAllMajors() {
        List<Major> majors = majorDAO.getAllMajors();
        if (majors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(majors, HttpStatus.OK);
    }
}