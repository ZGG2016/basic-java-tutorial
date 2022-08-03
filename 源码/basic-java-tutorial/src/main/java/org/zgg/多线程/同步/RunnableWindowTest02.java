package org.zgg.多线程.同步;
/**
 * 在Java中，我们通过同步机制，来解决线程的安全问题
 *
 *  方式一：同步代码块
 *
 *      synchronized(同步监视器){
 *          //需要被同步的代码
 *      }
 *      说明：1. 同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *           2. 操作共享数据的代码，即为需要被同步的代码。  --> 不能包含代码多了，也不能包含代码少了。
 *           3. 共享数据：多个线程共同操作的变量。比如：ticket就是共享数据。
 *     要求：多个线程必须要共用同一把锁。（如果将 `final Object obj = new Object();` 语句放到run方法里就不是共用一把锁了）
 *
 *     补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 *
 *  同步的方式，解决了线程的安全问题。---好处
 *  操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。 ---局限性
 *
 */
class RunnableWindow02 implements Runnable {

    private int ticket = 100;
    final Object obj = new Object();

    @Override
    public void run() {
        while(true){
//            synchronized (obj){
            synchronized (this){
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
            }
        }
    }
}


public class RunnableWindowTest02 {
    public static void main(String[] args) {
        RunnableWindow02 w = new RunnableWindow02();

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