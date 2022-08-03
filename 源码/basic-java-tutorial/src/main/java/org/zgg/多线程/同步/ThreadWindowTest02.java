package org.zgg.多线程.同步;
/**
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。
 */
class ThreadWindow02 extends Thread{
    private static int ticket = 100;
//    private static Object obj = new Object();

    @Override
    public void run() {
        while(true){
            // 不能用this了，它不是共享的  this代表着t1,t2,t3三个对象
            // 也不能使用 obj，同理

            synchronized (ThreadWindow02.class){
                if(ticket > 0){
                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}

public class ThreadWindowTest02 {
    public static void main(String[] args) {
        ThreadWindow02 t1 = new ThreadWindow02();
        ThreadWindow02 t2 = new ThreadWindow02();
        ThreadWindow02 t3 = new ThreadWindow02();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
