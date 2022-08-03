package org.zgg.多线程.死锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行有一个账户
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 *
 * 分析：
 * 1.是否是多线程问题？ 是，两个储户线程
 * 2.是否有共享数据？ 有，账户（或账户余额）
 * 3.是否有线程安全问题？有
 * 4.需要考虑如何解决线程安全问题？同步机制：有三种方式。
 */
public class LockTest02 {
    public static void main(String[] args) {
        Account acc = new Account(0.0);
        Customer c = new Customer(acc);
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}

class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    private ReentrantLock lock = new ReentrantLock(true);

//    public synchronized void deposit(double amount){   // 这里的同步监视器是Account.class
    public void deposit(double amount){
        try{
            lock.lock();
            if (amount>0){
                balance += amount;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 存钱成功，余额为：" + balance);
        }finally {
            lock.unlock();
        }
    }

}

class Customer implements Runnable{
    private Account acc;

    public Customer(Account acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        for (int i=0;i<3;i++){
            acc.deposit(1000);
        }
    }
}
