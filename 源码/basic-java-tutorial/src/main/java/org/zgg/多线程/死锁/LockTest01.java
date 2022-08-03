package org.zgg.多线程.死锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁  --- JDK5.0新增
 *
 * 通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当。
 *
 * java.util.concurrent.locks.Lock接口是控制多个线程对共享资源进行访问的工具。
 * 锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象
 *
 * 1. 面试题：synchronized 与 Lock的异同？
 *   相同：二者都可以解决线程安全问题
 *   不同：synchronized机制在执行完相应的同步代码以后，自动的释放同步监视器
 *        Lock需要手动的启动同步（lock()），同时结束同步也需要手动的实现（unlock()）
 *
 * 2.优先使用顺序：
 * Lock  同步代码块（已经进入了方法体，分配了相应资源）  同步方法（在方法体之外）
 *
 *  面试题：如何解决线程安全问题？有几种方式
 */
class RunnableWindow01 implements Runnable {

    private int ticket = 100;
    // 1.实例化ReentrantLock
    // 这个lock也得是唯一的，所以在继承Thread类的实现中，就需要再加static
    // 当设置为true时，在争用下，锁有利于将访问权授予等待时间最长的线程。否则，这个锁不能保证任何特定的访问顺序。
    private ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while(true){
            try{
                // 2.调用锁定方法lock()
                lock.lock();
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally {
                // 3.调用解锁方法：unlock()
                lock.unlock();
            }
        }
    }
}


public class LockTest01 {
    public static void main(String[] args) {
        RunnableWindow01 w = new RunnableWindow01();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}