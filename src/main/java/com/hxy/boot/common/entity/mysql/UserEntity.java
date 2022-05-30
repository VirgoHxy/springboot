package com.hxy.boot.common.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hxy.boot.common.utils.util.RegexpUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.sql.Timestamp;

@Getter
@Setter
@TableName(value = "`user`", schema = "loopback2mysql")
public class UserEntity {

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {UpdateUser.class, RemoveUser.class})
    private Long id;

    @NotBlank(groups = AddUser.class)
    @Length(max = 6)
    @Pattern(regexp = RegexpUtil.CAN_NOT_ILLEGAL_CHAR_REGEXP, message = RegexpUtil.CAN_NOT_ILLEGAL_CHAR_MESSAGE)
    private String name;

    @NotBlank(groups = AddUser.class)
    @Null(groups = UpdateUser.class)
    @Length(max = 10)
    @Pattern(regexp = RegexpUtil.ONLY_NUMBER_LETTER_REGEXP, message = RegexpUtil.ONLY_NUMBER_LETTER_MESSAGE)
    private String account;

    @NotBlank(groups = AddUser.class)
    @Null(groups = UpdateUser.class)
    @Length(max = 20)
    @Pattern(regexp = RegexpUtil.ONLY_NUMBER_LETTER_REGEXP, message = RegexpUtil.ONLY_NUMBER_LETTER_MESSAGE)
    private String password;

    @Null()
    private Timestamp createTime;

    @Null()
    private Timestamp updateTime;

    // 验证组
    public interface AddUser extends Default {
    }

    public interface UpdateUser extends Default {
    }

    public interface RemoveUser extends Default {
    }
}
