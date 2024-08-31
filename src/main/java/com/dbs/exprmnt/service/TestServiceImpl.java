package com.dbs.exprmnt.service;

import com.dbs.exprmnt.document.TestDocument;
import com.dbs.exprmnt.entity.TestEntity;
import com.dbs.exprmnt.repository.TestMongoRepository;
import com.dbs.exprmnt.repository.TestRdbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRdbRepository testRdbRepository;

    private final TestMongoRepository testMongoRepository;

    @Override
    public List<TestEntity> findAllRdbData() {
        return testRdbRepository.findAll();
    }

    @Override
    public List<TestDocument> findAllMongoData() {
        return testMongoRepository.findAll();
    }

    @Override
    public TestEntity findRdbData(Long id) {
        return testRdbRepository.findById(id).orElse(null);
    }

    @Override
    public TestDocument findMongoData(Long id) {
        return testMongoRepository.findById(id).orElse(null);
    }

    @Override
    public List<TestEntity> findRdBDataByFirstName(String firstName) {
        TestEntity entity = TestEntity.builder()
                .firstName(firstName)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return testRdbRepository.findAll(Example.of(entity, matcher));
    }

    @Override
    public List<TestDocument> findMongoDataByFirstName(String firstName) {
        TestDocument doc = TestDocument.builder()
                .firstName(firstName)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return testMongoRepository.findAll(Example.of(doc, matcher));
    }

    @Override
    public List<TestEntity> findRdBDataByPage(Pageable pageable) {
        return testRdbRepository.findAll(pageable).toList();
    }

    @Override
    public List<TestDocument> findMongoDataByPage(Pageable pageable) {
        return testMongoRepository.findAll(pageable).toList();
    }

    @Override
    public void saveRdbData(TestEntity entity) {
        testRdbRepository.save(entity);
    }

    @Override
    public void saveMongoData(TestDocument document) {
        testMongoRepository.save(document);
    }

    @Override
    public Long generateRdbId() {
        return testRdbRepository.findEntityWithMaxId().getId()+1;
    }

    @Override
    public Long generateMongoId() {
        return testMongoRepository.findTopByOrderByIdDesc().getId()+1;
    }

    @Override
    public void deleteRdbData(Long id) {
        testRdbRepository.deleteById(id);
    }

    @Override
    public void deleteMongoData(Long id) {
        testMongoRepository.deleteById(id);
    }
}
