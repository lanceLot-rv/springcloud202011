package com.lsc.springcloud;

import com.lsc.springcloud.entities.CronModel;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
/**
 * lsc
 */
public class CronUtil {

    public String generateCron(CronModel model) {
        if(model == null || model.getType() == null){
            return null;
        }
        String cron = "";
        if("s".equals(model.getType())){
            if(model.getSecond() > 59 ){
                cron = cron + "0/59 ";
            } else {
                cron = cron + "0/" + model.getSecond() +" ";
            }
            cron = cron + "* * * * ?";
        }

        if("m".equals(model.getType())) {
            if(model.getMinute() > 59){
                cron = cron + "0 */59 ";
            } else {
                cron = cron + "0 */" + model.getMinute() + " ";
            }
            cron = cron + "* * * ?";
        }
        if("d".equals(model.getType())){
                cron = model.getSecond() + " " + model.getMinute() + " " + model.getHour();
                cron = cron + " * * ?";
        }
        if("w".equals(model.getType())){
            cron = model.getSecond() + " " + model.getMinute() + " " + model.getHour();
            cron = cron + " * * " + model.getDayOfWeek();
        }
        if("M".equals(model.getType())) {
            cron = model.getSecond() + " " + model.getMinute() + " " + model.getHour();
            cron = cron + model.getDayOfMonth() + "* ?";
        }
        return cron;
    }

    public String cron(CronModel model){
        if(model == null || model.getType() == null){
            return null;
        }
        String cron = "";
        if(model.getType().equals("w") && model.getDayOfWeek() != null){
            cron = model.getDayOfWeek() + "";
        } else {
            cron = "?";
        }
        //
        cron = " * " + cron;
        if(model.getType().equals("M")){
            if(model.getDayOfMonth() != null && model.getDayOfMonth() <= lastDayOfThisMonth()){
                cron = model.getDayOfMonth() + cron;
            }
        }else {
            cron = " *" + cron;
        }
        if(model.getType().equals("d")){
            if(model.getHour() != null && model.getHour() >=0 && model.getHour() <= 23){
                cron = " " + model.getHour() + cron;
            }
        } else {
            cron = " *" + cron;
        }
        if(model.getType().equals("m") && model.getMinute() != null && model.getMinute() >= 0 && model.getMinute() <= 59){
            cron = " " + model.getMinute() + cron;
        } else {
            cron = " *" + cron;
        }
        if(model.getType().equals("s") && model.getSecond() != null && model.getSecond() >= 0 && model.getSecond() <= 59){
            cron = "0/" + model.getMinute() + cron;
        } else {
            cron = "0" + cron;
        }
        return cron;
    }

    public Integer lastDayOfThisMonth(){

        LocalDate today = LocalDate.now();
        //本月的最后一天
        LocalDate lastDay =today.with(TemporalAdjusters.lastDayOfMonth());
        return lastDay.getMonth().getValue();
    }
}
