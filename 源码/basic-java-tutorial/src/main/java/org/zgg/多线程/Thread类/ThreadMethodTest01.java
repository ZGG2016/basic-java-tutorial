package org.zgg.多线程.Thread类;
/**
 * 测试Thread中的常用方法：
 * 1. start():启动当前线程；调用当前线程的run()
 * 2. run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * 3. currentThread():静态方法，返回执行当前代码的线程
 * 4. getName():获取当前线程的名字
 * 5. setName():设置当前线程的名字  【也可以使用构造器】
 * 6. yield():释放当前cpu的执行权
 * 7. join():在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才
 *           结束阻塞状态。
 * 8. stop():已过时。当执行此方法时，强制结束当前线程。
 * 9. sleep(long millitime):让当前线程“睡眠”指定的millitime毫秒。在指定的millitime毫秒时间内，当前
 *                          线程是阻塞状态。
 * 10. isAlive():判断当前线程是否存活
 */
public class ThreadMethodTest01 {
    public static void main(String[] args) throws InterruptedException {
        MyThread03 myThread03 = new MyThread03("线程1");
//        myThread03.setName("线程1 - ");
        myThread03.start();

        Thread.currentThread().setName("主线程");
        for (int i=0;i<100;i++){
            if (i%2==0) continue;
            System.out.println(Thread.currentThread().getName() + " ---> " + i);
            // 主线程遍历到11的时候，阻塞主线程，执行线程1
//            if (i == 11) {
//                myThread03.join();
//            }
        }


    }

}

class MyThread03 extends Thread{

    public MyThread03(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            if (i%2!=0) continue;

            // 线程1每次输出的时候睡1秒
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " ---> " + i);

            // 线程1遍历到50的时候，释放当前cpu的执行权
//            if (i == 50){
//                yield();
//            }
        }
    }
}
