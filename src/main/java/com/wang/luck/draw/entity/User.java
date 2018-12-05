package com.wang.luck.draw.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String name;
    private String state;
}
