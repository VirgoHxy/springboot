package com.hxy.boot.common.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @NotNull(message = "id不能为空", groups = UpdateUser.class)
    private Long id;

    @NotBlank(message = "name不能为空", groups = AddUser.class)
    @Length(max = 6, message = "name不能超过6个字符")
    private String name;

    @NotBlank(message = "account不能为空", groups = AddUser.class)
    @Null(message = "account不能更新", groups = UpdateUser.class)
    @Length(max = 10, message = "account不能超过10个字符")
    @Pattern(regexp = "^[A-Za-z\\d]+$", message = "account仅包含字母和数字")
    private String account;

    @NotBlank(message = "password不能为空", groups = AddUser.class)
    @Null(message = "password不能更新", groups = UpdateUser.class)
    @Length(max = 20, message = "password不能超过20个字符")
    @Pattern(regexp = "^[A-Za-z\\d]+$", message = "password仅包含字母和数字")
    private String password;

    @Null(message = "createTime不能存在")
    private Timestamp createTime;

    @Null(message = "updateTime不能存在")
    private Timestamp updateTime;

    public interface AddUser extends Default {}
    public interface UpdateUser extends Default {}
}
