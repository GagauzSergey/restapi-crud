package com.restapi.crud.service;

import com.restapi.crud.model.Tester;

import java.util.List;

public interface TesterService {

    Tester getTesterByLastName(String lastname);

    Tester getTesterById(Long id);

    boolean existsByLastName(String lastname);

    void addTester(Tester tester);

    void updateTester(Tester tester);

    void deleteTester(Long id);

    List<Tester> testerList();

    boolean isTesterExist(Tester tester);

}
