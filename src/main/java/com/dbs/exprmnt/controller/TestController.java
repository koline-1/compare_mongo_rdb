package com.dbs.exprmnt.controller;

import com.dbs.exprmnt.document.TestDocument;
import com.dbs.exprmnt.entity.TestEntity;
import com.dbs.exprmnt.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class TestController {

    private final TestService testService;

    /**
     * RDB 전체 데이터 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rdb/all")
    public void findAllRdbData() {
        testService.findAllRdbData();
    }

    /**
     * Mongo 전체 데이터 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mongo/all")
    public void findAllMongoData() {
        testService.findAllMongoData();
    }

    /**
     * RDB PK 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rdb/id")
    public TestEntity findRdbData() {
        return testService.findRdbData(Math.round(Math.random()*100000));
    }

    /**
     * Mongo PK 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mongo/id")
    public TestDocument findMongoData() {
        TestDocument test = testService.findMongoData(Math.round(Math.random()*100000));
        return test;
    }

    /**
     * RDB 조건 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rdb/condition")
    public List<TestEntity> findRdBDataByCondition() {
        return testService.findRdBDataByFirstName(getSearchName());
    }

    /**
     * Mongo 조건 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mongo/condition")
    public List<TestDocument> findMongoDataByCondition() {
        return testService.findMongoDataByFirstName(getSearchName());
    }

    /**
     * random 2글자 String 생성
     */
    private String getSearchName() {
        String lower_case_alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < 2; i++)
            sb.append(lower_case_alphabet.charAt((int) Math.round(Math.random()*25)));

        return sb.toString();
    }

    /**
     * RDB 페이지 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rdb/page")
    public List<TestEntity> findRdbDataByPage() {
        Pageable pageable = Pageable.ofSize(20).withPage(
                (int) Math.round(Math.random() * ((double) 100000 / 20))
        );

        return testService.findRdBDataByPage(pageable);
    }

    /**
     * Mongo 페이지 조회
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mongo/page")
    public List<TestDocument> findMongoDataByPage() {
        Pageable pageable = Pageable.ofSize(20).withPage(
                (int) Math.round(Math.random() * ((double) 100000 / 20))
        );

        return testService.findMongoDataByPage(pageable);
    }

    /**
     * RDB 삽입
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rdb/insert")
    public void insertRdbData() {
        testService.saveRdbData(
                TestEntity.builder()
                        .id(testService.generateRdbId())
                        .firstName(getSearchName())
                        .lastName(getSearchName())
                        .email(getSearchName() + "@email.com")
                        .build()
        );
    }

    /**
     * Mongo 삽입
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mongo/insert")
    public void insertMongoData() {
        testService.saveMongoData(
                TestDocument.builder()
                        .id(testService.generateMongoId())
                        .firstName(getSearchName())
                        .lastName(getSearchName())
                        .email(getSearchName() + "@email.com")
                        .build()
        );
    }

    /**
     * RDB 수정
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rdb/update")
    public void updateRdbData() {
        TestEntity entity = testService.findRdbData(Math.round(Math.random()*100000));
        entity.setLastName(getSearchName());
        testService.saveRdbData(entity);
    }

    /**
     * Mongo 수정
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mongo/update")
    public void updateMongoData() {
        TestDocument document = testService.findMongoData(Math.round(Math.random()*100000));
        document.setLastName(getSearchName());
        testService.saveMongoData(document);
    }

    /**
     * RDB 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rdb/delete")
    public void deleteRdbData() {
        testService.deleteRdbData(Math.round(Math.random()*100000));
    }

    /**
     * Mongo 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mongo/delete")
    public void deleteMongoData() {
        testService.deleteMongoData(Math.round(Math.random()*100000));
    }
}
