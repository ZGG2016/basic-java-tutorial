package org.zgg.多线程.Callable接口;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * 创建线程的方式三：实现Callable接口。 --- JDK 5.0新增
 *
 * Future接口可以对具体Runnable、Callable任务的执行结果进行取消、查询是否完成、获取结果等。
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
 * 1. call()可以有返回值的。
 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3. Callable是支持泛型的
 *
 */
public class CallableTest01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 3.创建Callable接口实现类的对象
        CMyThread cMyThread = new CMyThread();
        // 4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask<Integer> futureTask = new FutureTask<>(cMyThread);
        // 5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();

        // 6.获取Callable中call方法的返回值
        // get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
        Integer res = futureTask.get();
        System.out.println("总和为：" + res);

    }
}

// 1.创建一个实现Callable的实现类
class CMyThread implements Callable<Integer> {

    // 2.实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
