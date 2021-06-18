package com.example.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.time.LocalDateTime;

public class DynamicRoutingDataSourceResolver extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        // chuyển hướng datasource ở đây, sample này đang hardcode theo thời gian hiện tại
        int sec = LocalDateTime.now().getSecond();
        if (sec % 2 == 0) {
            return "dataSource1";
        } else {
            return "dataSource2";
        }
    }
}