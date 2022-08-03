package org.zgg.多线程.Thread类;
/**
 * 线程：一个程序内部的一条执行路径
 * 多线程：若一个进程同一时间【并行】执行多个线程，就是支持多线程的
 *       - 单核CPU其实是一种假的多线程
 *       - 多核CPU才能更好的发挥多线程的效率
 *
 * 并行：多个CPU【同时执行】多个任务
 * 并发：一个CPU(采用时间片)“同时执行”多个任务
 * 
 * 多线程的创建，
 *   方式一：继承于Thread类
 *     1. 创建一个继承于Thread类的子类
 *     2. 重写Thread类的run() --> 【将此线程要执行的操作声明在run()中】
 *     3. 创建Thread类的子类的对象
 *     4. 通过此对象调用start()
 *
 * main方法里的 `MyThread myThread = new MyThread();` 和 `myThread.start();` 语句是在主线程中执行的，
 * 当执行start后，开始执行myThread线程，而主线程接着往下执行。
 */
public class ThreadTest01 {
    public static void main(String[] args) {
        // 3
        MyThread01 myThread = new MyThread01();
        // 4
        myThread.start();

        for (int i=0;i<10;i++){
            if (i%2==0) continue;
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }
    }
}

// 1
class MyThread01 extends Thread{

    // 2
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            if (i%2!=0) continue;
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }
    }
}
