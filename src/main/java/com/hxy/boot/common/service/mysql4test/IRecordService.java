package com.hxy.boot.common.service.mysql4test;

import com.hxy.boot.common.entity.mysql4test.RecordEntity;

public interface IRecordService {
    RecordEntity selectById(long id);

    void insert(RecordEntity recordEntity);
}
