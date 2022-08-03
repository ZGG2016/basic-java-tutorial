package org.zgg.多线程.线程通信;

/**
 * 线程通信的应用：经典例题：生产者/消费者问题
 *
 * 生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员
 * 会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品
 * 了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *
 * 分析：
 * 1. 是否是多线程问题？是，生产者线程，消费者线程
 * 2. 是否有共享数据？是，店员（或产品）
 * 3. 如何解决线程的安全问题？同步机制,有三种方法
 * 4. 是否涉及线程的通信？是
 *
 */
public class CommTest02 {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Thread t1 = new Thread(new Producer(clerk));
        Thread t2 = new Thread(new Comsumer(clerk));

        t1.setName("生产者");
        t2.setName("消费者");

        t1.start();
        t2.start();
    }
}

class Clerk{
    private int productNum = 0;

    public synchronized void produceProduct(){
        if (productNum<5){
            productNum++;
            System.out.println(Thread.currentThread().getName() + ":开始生产第" + productNum + "个产品");
            notify();
        }else{
            // 叫生产者停一下
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct(){
        if (productNum>0){
            System.out.println(Thread.currentThread().getName() + ":开始消费第" + productNum + "个产品");
            productNum--;
            notify();
        }else{
            // 消费者等一下
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":开始生产产品.....");

        while(true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.produceProduct();
        }
    }
}

class Comsumer implements Runnable{
    private Clerk clerk;

    public Comsumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":开始消费产品.....");
        while (true){

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumeProduct();
        }
    }
}