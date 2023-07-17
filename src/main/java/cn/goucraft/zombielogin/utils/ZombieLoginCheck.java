package cn.goucraft.zombielogin.utils;

import cn.goucraft.zombielogin.configuration.ZombieLoginConfig;
import org.bukkit.Bukkit;

public class ZombieLoginCheck {
     int baseProbability; // Day1发生尸潮的基础概率
     int increasePercentage; // Day2及以后未发生尸潮概率降低的百分比
     int decreasePercentage; // Day2及以后发生尸潮概率增加的百分比
     int currentProbability = baseProbability; // 当前的尸潮发生概率

    int t_change;
    int random;
    int lastDay;
    boolean isLastDayChange=false;

    public void algo(ZombieLoginConfig config) {
        baseProbability=config.getBaserandom();
        increasePercentage=config.getInrandom();
        decreasePercentage=config.getDerandom();
        int random= (int) (Math.random()*100);
        Bukkit.broadcastMessage("今日发生尸潮的概率为  "+currentProbability);
       if (currentProbability>=100||currentProbability<=0){
           if (currentProbability>=100){
               //这里召唤尸潮方法
               Bukkit.broadcastMessage("召唤尸潮>=1");
           }
           currentProbability=baseProbability;
       }else {
           if (random<=currentProbability){
               //这里召唤尸潮方法
               Bukkit.broadcastMessage("有尸潮"+random+"<=尸潮概率"+currentProbability);
               currentProbability-=increasePercentage;
           }else if (random>currentProbability){
               Bukkit.broadcastMessage("无尸潮"+random+">尸潮概率"+ currentProbability);
               currentProbability += decreasePercentage;

           }
       }

//从config.yml中获取当日尸潮发生的基础概率
    }
    private void ttdt(ZombieLoginConfig config) {
        //ture=前一日没有发生尸潮 false=前一日发生了尸潮
        if (!isLastDayChange){
            lastDay= (int) (Math.random()*100);
        }
        t_change=config.getT_Change();
        //currentProbability= (int) (Math.random()*100);
        random= (int) (Math.random()*100);
        //判断今日是否发生尸潮true不发生 false发生
        if (random>lastDay){
            //判断是否开启递减
            if (config.isTo_Enable()){
                lastDay= (int) Math.random()*t_change;
            }
            lastDay+=Math.random()*100;
            lastDay=Math.min(100,lastDay);
            lastDay=Math.max(0,lastDay);
            isLastDayChange=true;
            Bukkit.broadcastMessage("无尸潮次日尸潮概率已增幅至"+lastDay);
        }else {
            isLastDayChange=false;
        Bukkit.broadcastMessage("尸潮来咯");
        }

    }

    public boolean isAllTrue(ZombieLoginConfig config) {
        if (config.isAlgo_Enable()&&!config.isTtdt_Enable()){
            algo(config);
            return true;
        }else if (config.isTtdt_Enable()&&!config.isAlgo_Enable()){
            ttdt(config);
            return true;
        }else if (!config.isAlgo_Enable()&&!config.isTtdt_Enable()){
            Bukkit.getLogger().info("你没有开启任何尸潮算法");
        }
        return false;
    }


}
