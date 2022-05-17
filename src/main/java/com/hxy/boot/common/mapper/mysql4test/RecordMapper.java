package com.hxy.boot.common.mapper.mysql4test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxy.boot.common.entity.mysql4test.RecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordMapper extends BaseMapper<RecordEntity> {
}
