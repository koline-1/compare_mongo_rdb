package com.dbs.exprmnt.repository;

import com.dbs.exprmnt.document.TestDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMongoRepository extends MongoRepository<TestDocument, Long> {
    @Query(sort = "{ _id: -1 }")
    TestDocument findTopByOrderByIdDesc();
}
