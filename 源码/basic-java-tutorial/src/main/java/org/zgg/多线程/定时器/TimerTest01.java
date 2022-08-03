package org.zgg.多线程.定时器;

import java.util.Timer;
import java.util.TimerTask;
/**
 * 定时器是一个应用十分广泛的线程工具，可用于调度多个定时任务以后台线程的方式执行。
 *
 * 可以让我们在指定的时间做某件事情，还可以重复的做某件事情。
 *
 * 在Java中，可以通过Timer和TimerTask类来实现定义调度的功能：
 *
 * Timer 定时
 * 	  public Timer()
 * 	      创建一个新计时器
 * 	  public void schedule(TimerTask task, long delay)
 * 	      安排在指定延迟后执行指定的任务
 * 	  public void schedule(TimerTask task,long delay,long period)
 * 	       安排指定的任务从指定的延迟后开始进行重复的固定延迟执行
 * 	  public void schedule(TimerTask task,Date time)
 * 	       安排在指定的时间执行指定的任务。如果此时间已过去，则安排立即执行该任务
 * TimerTask 任务
 * 	  public abstract void run()
 * 	       此计时器任务要执行的操作
 *    public boolean cancel()
 *         取消此计时器任务
 *
 * */
public class TimerTest01 {
    public static void main(String[] args) {
        // 创建定时器对象
        Timer t = new Timer();
        // 3秒后执行爆炸任务
//         t.schedule(new MyTask(), 3000);

        // 结束任务  (传一个定时器参数，方便在run方法中取消定时器)
        t.schedule(new MyTask(t), 3000);
    }
}


// 做一个任务
class MyTask extends TimerTask {

    private Timer t;

    public MyTask(){}

    public MyTask(Timer t){
        this.t = t;
    }

    @Override
    public void run() {
        System.out.println("beng,爆炸了");
        t.cancel();
    }
}