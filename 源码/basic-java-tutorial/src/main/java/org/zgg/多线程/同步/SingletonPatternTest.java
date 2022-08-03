package org.zgg.多线程.同步;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 */
public class SingletonPatternTest {
    public static void main(String[] args) {
        Thread sMyThread1 = new Thread(new SMyThread());
        Thread sMyThread2 = new Thread(new SMyThread());
        sMyThread1.start();
        sMyThread2.start();
    }

}

class SMyThread implements Runnable {

    @Override
    public void run() {
        SingletonPattern instance = SingletonPattern.getInstance();
        System.out.println(instance);
    }
}

class SingletonPattern {

    private SingletonPattern() {
    }

    private static SingletonPattern instance = null;

    // 同步方法
//    public static synchronized SingletonPattern getInstance(){
//        if (instance==null){
//            instance = new SingletonPattern();
//        }
//        return instance;
//    }
    // 同步代码块
    public static SingletonPattern getInstance() {
        //方式一：效率稍差
//        synchronized (SingletonPattern.class){
//            if (instance==null){
//                instance = new SingletonPattern();
//            }
//            return instance;
//        }
        //方式二：效率更高
        if (instance == null) {
            synchronized (SingletonPattern.class) {
                if (instance == null) {
                    instance = new SingletonPattern();
                }
            }
        }
        return instance;
    }
}
