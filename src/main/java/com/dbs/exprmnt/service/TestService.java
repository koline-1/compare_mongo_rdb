package com.dbs.exprmnt.service;

import com.dbs.exprmnt.document.TestDocument;
import com.dbs.exprmnt.entity.TestEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {

    List<TestEntity> findAllRdbData();

    List<TestDocument> findAllMongoData();

    TestEntity findRdbData(Long id);

    TestDocument findMongoData(Long id);

    List<TestEntity> findRdBDataByFirstName(String firstName);

    List<TestDocument> findMongoDataByFirstName(String firstName);

    List<TestEntity> findRdBDataByPage(Pageable pageable);

    List<TestDocument> findMongoDataByPage(Pageable pageable);

    void saveRdbData(TestEntity entity);

    void saveMongoData(TestDocument document);

    Long generateRdbId();

    Long generateMongoId();

    void deleteRdbData(Long id);

    void deleteMongoData(Long id);
}
