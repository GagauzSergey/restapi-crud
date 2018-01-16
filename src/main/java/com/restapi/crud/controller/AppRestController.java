package com.restapi.crud.controller;

import com.restapi.crud.model.Tester;
import com.restapi.crud.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin //(origins = "http://localhost:63343", maxAge = 3600)
public class AppRestController {

    private final TesterService testerService;

    @Autowired
    public AppRestController(TesterService testerService) {
        this.testerService = testerService;
    }

    // All Testers
    @RequestMapping(value = "/testers/", method = RequestMethod.GET)
    public ResponseEntity<List<Tester>> listAllTester() {
        List<Tester> testers = testerService.testerList();

        if (testers.isEmpty()) {
            return new ResponseEntity<List<Tester>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Tester>>(testers, HttpStatus.OK);
    }

    // Get One Tester
    @RequestMapping(value = "/testers/{id}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tester> getTester(@PathVariable("id") long id) {
        Tester tester = testerService.getTesterById(id);
        if (tester == null) {
            return new ResponseEntity<Tester>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tester>(tester, HttpStatus.OK);
    }

    // Create New Tester
    @RequestMapping(value = "/testers/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTester(@RequestBody Tester tester,
                                             UriComponentsBuilder uriComponentsBuilder) {
        if (testerService.isTesterExist(tester)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        testerService.addTester(tester);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/testers/{id}").
                buildAndExpand(tester.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }

    // Update Tester
    @RequestMapping(value = "/testers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Tester> updateTester(@PathVariable("id") long id,
                                               @RequestBody Tester tester) {
        Tester tempTester = testerService.getTesterById(id);
        if (tempTester == null) {
            return new ResponseEntity<Tester>(HttpStatus.NO_CONTENT);
        }
        tempTester.setFirstname(tester.getFirstname());
        tempTester.setLastname(tester.getLastname());
        tempTester.setPosition(tester.getPosition());
        tempTester.setSalary(tester.getSalary());
        testerService.updateTester(tempTester);

        return new ResponseEntity<Tester>(tempTester, HttpStatus.OK);
    }

    // Delete Tester
    @RequestMapping(value = "/testers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTester(@PathVariable("id") long id) {
        Tester tester = testerService.getTesterById(id);

        if (tester == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        testerService.deleteTester(id);
        return new ResponseEntity<Tester>(HttpStatus.NO_CONTENT);
    }
}
