package org.zgg.多线程.Thread类;
/**
 * start方法的作用：
 *   - 启动当前线程
 *   - 调用当前线程的run()
 *
 * 两个问题：
 *   1. 如果使用run方法启动线程，而不是start方法，那么就不会启动自己创建的线程，而是都在主线程中执行的，
 *      即，在主线程中创建一个myThread对象，调用该对象的run方法。
 *   2. 如果需要再启动一个线程执行run方法里的逻辑，那么就需要重新创建一个myThread对象
 */
public class ThreadTest02 {
    public static void main(String[] args) {
        MyThread02 myThread = new MyThread02();
        myThread.start();
        // 问题一：我们不能通过直接调用run()的方式启动线程
//        myThread.run();

        //问题二：再启动一个线程，遍历100以内的偶数。不可以还让已经start()的线程去执行。会报IllegalThreadStateException
//        myThread.start();

        MyThread02 myThread2 = new MyThread02();
        myThread2.start();

        for (int i=0;i<10;i++){
            if (i%2==0) continue;
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }
    }
}

class MyThread02 extends Thread{

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            if (i%2!=0) continue;
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }
    }
}