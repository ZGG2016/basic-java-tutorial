package org.zgg.多线程.同步;
/**
 * 例子：创建三个窗口卖票，总票数为100张. 使用实现Runnable接口的方式
 *
 * 1.问题：
 *      - 卖票过程中，出现了重票、错票 --> 出现了线程的安全问题
 * 2.问题出现的原因：
 *      - 当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票。
 * 3.如何解决：
 *      - 当一个线程a在操作ticket的时候，其他线程不能参与进来。
 *        直到线程a操作完ticket时，其他线程才可以开始操作ticket。这种情况即使线程a出现了阻塞，也不能被改变。
 *
 * 4.只有含共享数据的程序才会出现线程的安全问题。
 *
 * 5.在Java中，我们通过同步机制，来解决线程的安全问题。
 */
class RunnableWindow01 implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            if(ticket > 0){

                // 此时，当前正在操作ticket的线程休眠了，那么其他线程就会抢到CPU的执行权
                // 所以，当就剩一张票时，当前线程获得CPU的执行权，判断ticket>0，便开始操作ticket，但紧接着它休眠了，
                // 这时候其他线程就获得CPU的执行权，也判断ticket>0，便开始操作ticket，但它也休眠了，
                // 等休眠完成后，开始真正分别依次操作ticket，此时就会出现 ticket=0 ticket=-1 的情况
                // 所以，正确的情况应该是：一个线程获得CPU的执行权后，直到操作完成，别的线程才能再开始操作
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


public class RunnableWindowTest01 {
    public static void main(String[] args) {
        RunnableWindow01 w = new RunnableWindow01();
        // ticket属性对于下面三个线程是共享的
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