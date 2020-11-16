package com.lsc.springcloud.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfigTestPo {
    private Long id;
    private String status;
    private int type;
}