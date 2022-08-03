package org.zgg.多线程.死锁;
/**
 * 演示线程的死锁问题
 *
 * 1.死锁的理解：
 *     不同的线程分别占用对方需要的同步资源不放弃，
 *     都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 *     【线程1持有的锁，线程2也需要，它拿到后才能往下执行；而线程2持有的锁，线程1也需要，它拿到后才能往下执行】
 *
 * 2.说明：
 *   1）出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 *   2）我们使用同步时，要避免出现死锁。
 *
 * 3.解决方法
 *   专门的算法、原则
 *   尽量减少同步资源的定义
 *   尽量避免嵌套同步
 */
public class DeadLockTest01 {
    public static void main(String[] args) {

    StringBuffer s1 = new StringBuffer();
    StringBuffer s2 = new StringBuffer();

    // 线程1
    new Thread(){
        @Override
        public void run() {
            synchronized (s1){

                s1.append("a");
                s2.append("1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                synchronized (s2){
                    s1.append("b");
                    s2.append("2");

                    System.out.println(s1);
                    System.out.println(s2);
                }


            }

        }
    }.start();

    // 线程2
    new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (s2){

                s1.append("c");
                s2.append("3");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (s1){
                    s1.append("d");
                    s2.append("4");

                    System.out.println(s1);
                    System.out.println(s2);
                }
            }
        }
    }).start();

    }
}

