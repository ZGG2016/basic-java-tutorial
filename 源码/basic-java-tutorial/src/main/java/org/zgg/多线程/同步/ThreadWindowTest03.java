package org.zgg.多线程.同步;
/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 *
 *
 * 关于同步方法的总结：
 *  1. 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 *  2. 非静态的同步方法，同步监视器是：this
 *     静态的同步方法，同步监视器是：当前类本身
 */
class ThreadWindow03 extends Thread{
    private static int ticket = 100;

    @Override
    public void run() {
        while(true){
            show();
        }
    }


    //private synchronized void show(){ //同步监视器：t1,t2,t3。此种解决方式是错误的
    private static synchronized void show(){//同步监视器：Window4.class
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
            ticket--;
        }
    }
}

public class ThreadWindowTest03 {
    public static void main(String[] args) {
        ThreadWindow03 t1 = new ThreadWindow03();
        ThreadWindow03 t2 = new ThreadWindow03();
        ThreadWindow03 t3 = new ThreadWindow03();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
