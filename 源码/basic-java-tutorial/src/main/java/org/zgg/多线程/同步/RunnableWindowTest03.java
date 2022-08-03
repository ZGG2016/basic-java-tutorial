package org.zgg.多线程.同步;
/**
 * 方式二：同步方法。
 *
 *      如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。
 *
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 */
class RunnableWindow03 implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            show();
        }
    }

    private synchronized void show(){  // 同步监视器：this
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


public class RunnableWindowTest03 {
    public static void main(String[] args) {
        RunnableWindow03 w = new RunnableWindow03();

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