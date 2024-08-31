package com.dbs.exprmnt.repository;

import com.dbs.exprmnt.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRdbRepository extends JpaRepository<TestEntity, Long> {

    @Query(value = "SELECT * FROM test_tbl e ORDER BY e.id DESC LIMIT 1", nativeQuery = true)
    TestEntity findEntityWithMaxId();

}
