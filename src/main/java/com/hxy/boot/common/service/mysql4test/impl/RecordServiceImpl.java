package com.hxy.boot.common.service.mysql4test.impl;

import com.hxy.boot.common.entity.mysql4test.RecordEntity;
import com.hxy.boot.common.mapper.mysql4test.RecordMapper;
import com.hxy.boot.common.service.mysql4test.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public RecordEntity selectById(long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public void insert(RecordEntity recordEntity) {
        recordEntity.setCreateTime(new Timestamp(new Date().getTime()));
        recordMapper.insert(recordEntity);
    }

}