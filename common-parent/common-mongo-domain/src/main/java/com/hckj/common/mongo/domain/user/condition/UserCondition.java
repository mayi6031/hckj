package com.hckj.common.mongo.domain.user.condition;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ：yuhui
 * @date ：Created in 2020/9/29 17:04
 */
@Data
@ToString
@NoArgsConstructor
public class UserCondition {
    private String name;

    private String username;

    private Integer pageNo = 1;

    private Integer pageSize = 10;
}
