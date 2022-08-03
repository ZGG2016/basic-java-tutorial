package org.zgg.多线程.定时器;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest02 {
    public static void main(String[] args) {
        // 创建定时器对象
        Timer t = new Timer();
        // 3秒后执行爆炸任务第一次，如果不成功，每隔2秒再继续炸
        t.schedule(new MyTask2(), 3000, 2000);
    }
}

// 做一个任务
class MyTask2 extends TimerTask {
    @Override
    public void run() {
        System.out.println("beng,爆炸了");
    }
}