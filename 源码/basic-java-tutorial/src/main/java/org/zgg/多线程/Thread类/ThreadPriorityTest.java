package org.zgg.多线程.Thread类;
/**
 * 线程的优先级：
 * 1.
 *   - MAX_PRIORITY：10
 *   - MIN_PRIORITY：1
 *   - NORM_PRIORITY：5  -->默认优先级
 * 2.如何获取和设置当前线程的优先级：
 *   getPriority():获取线程的优先级
 *   setPriority(int p):设置线程的优先级
 *
 *   说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下
 *   被执行。并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。
 *
 */
public class ThreadPriorityTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread04 myThread04 = new MyThread04("线程1");
        myThread04.setPriority(Thread.MAX_PRIORITY);
        myThread04.start();

        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i=0;i<10;i++){
            if (i%2==0) continue;
            System.out.println(Thread.currentThread().getName() + " ---> " + i);
        }


    }

}

class MyThread04 extends Thread{

    public MyThread04(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            if (i%2!=0) continue;
            System.out.println(Thread.currentThread().getName() + " ---> " + i);
        }
    }
}
