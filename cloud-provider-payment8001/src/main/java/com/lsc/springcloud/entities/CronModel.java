package com.lsc.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CronModel {
    private String type; // s 每 秒 m 每 分  d 每天 W 每周 M 每月
    private Integer second;//
    private Integer minute;
    private Integer hour;
    private Integer dayOfWeek;
    private Integer dayOfMonth;
    private Integer month;
}
