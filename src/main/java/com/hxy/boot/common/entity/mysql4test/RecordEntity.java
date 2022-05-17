package com.hxy.boot.common.entity.mysql4test;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Getter
@Setter
@TableName(value = "record", schema = "test")
public class RecordEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("cont")
    private String content;

    @TableField("cr_time")
    @Null(message = "createTime不能存在")
    private Timestamp createTime;
}