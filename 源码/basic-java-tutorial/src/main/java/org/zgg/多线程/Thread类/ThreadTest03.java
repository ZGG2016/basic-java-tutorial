package org.zgg.多线程.Thread类;
/**
 * 写成匿名子类的方式
 */
public class ThreadTest03 {
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

    }
}