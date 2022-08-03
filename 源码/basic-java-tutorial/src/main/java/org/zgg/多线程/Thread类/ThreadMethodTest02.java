package org.zgg.多线程.Thread类;
/**
 * 测试Thread中的常用方法：
 * 11. public final void setDaemon(boolean on):
 *     将该线程标记为守护线程或用户线程。 当正在运行的线程都是守护线程时，Java 虚拟机退出。
 *     该方法必须在启动线程前调用。
 *
 *  用户线程: 工作线程
 *  守护线程: 为工作线程服务，当所有的用户线程结束，守护线程自动结束  (垃圾回收机制)
 *
 */
public class ThreadMethodTest02 {
    public static void main(String[] args) throws InterruptedException {
        ThreadDaemon td = new ThreadDaemon();
        td.setName("关羽");
        // 设置守护线程
        td.setDaemon(true);
        td.start();

        // 守护线程不会输出到1000

        Thread.currentThread().setName("刘备");
        for (int x = 0; x < 5; x++) {
            System.out.println(Thread.currentThread().getName() + ":" + x);
        }
    }
}

class ThreadDaemon extends Thread {
    @Override
    public void run() {
        for (int x = 0; x < 1000; x++) {
            System.out.println(getName() + ":" + x);
        }
    }
}
