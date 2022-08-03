package org.zgg.多线程.同步;
/**
 * 例子：创建三个窗口卖票，总票数为100张. 使用继承Thread类的方式
 *
 * 存在线程的安全问题
 */
class ThreadWindow01 extends Thread{
    // 静态  所有对象共享
    private static int ticket = 100;

    @Override
    public void run() {
        while(true){
            if(ticket > 0){
                System.out.println(getName() + "：卖票，票号为：" + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }
}

public class ThreadWindowTest01 {
    public static void main(String[] args) {
        ThreadWindow01 t1 = new ThreadWindow01();
        ThreadWindow01 t2 = new ThreadWindow01();
        ThreadWindow01 t3 = new ThreadWindow01();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
