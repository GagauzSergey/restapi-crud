package com.restapi.crud.service.impl;

import com.restapi.crud.dao.TesterDao;
import com.restapi.crud.model.Tester;
import com.restapi.crud.service.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TesterServiceImpl implements TesterService {

    private final TesterDao testerDao;

    @Autowired
    public TesterServiceImpl(TesterDao testerDao) {
        this.testerDao = testerDao;
    }

    @Override
    @Transactional(readOnly = false)
    public Tester getTesterByLastName(String lastname) {
        return testerDao.findTesterByLastName(lastname);
    }

    @Override
    public Tester getTesterById(Long id) {
        return testerDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean existsByLastName(String lastname) {
        return testerDao.existsTesterByLastName(lastname);
    }

    @Override
    @Transactional(readOnly = false)
    public void addTester(Tester tester) {
        testerDao.save(tester);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateTester(Tester tester) {
        testerDao.save(tester);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteTester(Long id) {
        testerDao.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Tester> testerList() {
        return testerDao.findAll();
    }

    @Override
    public boolean isTesterExist(Tester tester) {
        return getTesterByLastName(tester.getLastname())!=null;
    }
}
