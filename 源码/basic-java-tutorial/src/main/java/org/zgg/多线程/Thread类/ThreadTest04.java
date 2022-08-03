package org.zgg.多线程.Thread类;
/**
 * 练习：创建两个分线程，其中一个线程遍历10以内的偶数，另一个线程遍历10以内的奇数
 */
public class ThreadTest04 {
    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    if (i%2!=0) continue;
                    System.out.println(Thread.currentThread().getName() + "--->" + i);
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    if (i%2==0) continue;
                    System.out.println(Thread.currentThread().getName() + "--->" + i);
                }
            }
        }.start();

    }
}