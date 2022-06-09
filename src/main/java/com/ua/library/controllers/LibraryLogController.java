package com.ua.library.controllers;

import com.ua.library.entities.LibraryLogEntity;
import com.ua.library.exceptions.LibraryLogNotFoundException;
import com.ua.library.service.LibraryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@CrossOrigin(origins = "http://localhost:3000")
public class LibraryLogController {

    private final LibraryLogService logService;

    @Autowired
    public LibraryLogController(LibraryLogService logService) {
        this.logService = logService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long logId) {
        try {
            LibraryLogEntity libraryLog = logService.findById(logId);
            return new ResponseEntity<>(libraryLog, HttpStatus.OK);
        } catch (LibraryLogNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LibraryLogEntity>> findAll() {
        List<LibraryLogEntity> logs = logService.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam String dateTaking,
                                  @RequestParam String dateShouldReturn,
                                  @RequestParam Long bookId,
                                  @RequestParam Long customerId) {
        try {
            LibraryLogEntity libraryLogSaved = logService.save(dateTaking,
                    dateShouldReturn, bookId, customerId);
            return new ResponseEntity<>(libraryLogSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long logId,
                                    @RequestParam String dateTaking,
                                    @RequestParam String dateShouldReturn,
                                    @RequestParam Long bookId,
                                    @RequestParam Long customerId) {
        try {
            LibraryLogEntity libraryLogUpdated = logService.update(logId, dateTaking,
                    dateShouldReturn, bookId, customerId);
            return new ResponseEntity<>(libraryLogUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> putDateReturning(@RequestParam Long logId,
                                              @RequestParam String dateReturning) {
        try {
            LibraryLogEntity libraryLog = logService.putDateReturning(logId, dateReturning);
            return new ResponseEntity<>(libraryLog, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long logId) {
        try {
            logService.delete(logId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
