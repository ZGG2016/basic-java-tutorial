package org.zgg.jdk8.lambda表达式;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/*
 * lambda表达式
 *    格式：
 *        - 箭头左边：lambda形参列表 （其实就是接口中的抽象方法的形参列表）
 *        - 箭头：lambda操作符
 *        - 箭头右边：lambda体 （其实就是重写的抽象方法的方法体）
 *
 *    本质：作为函数式接口的实例（函数式接口只有一个抽象方法）
 *         在scala语言中，lambda表达式的类型是函数，而在java8中，是对象，且依附于函数式接口
 *         以前使用匿名函数类表示的现在都可以用lambda表达式来写
 *
 *    使用总结：
 *        - 左边：lambda形参列表的参数类型可以省略（类型推断）；如果lambda形参列表只有一个参数，可以省略括号
 *        - 右边：lambda体应该使用一对{}包裹；如果lambda体只有一条执行语句（可能是return语句），可以省略{}和return关键字
 *
 *
 * */
public class LambdaTest {
    // 格式一：lambda形参列表无参，lanbda体无返回值
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            // 无参 无返回值
            public void run() {
                System.out.println("test1111111111111111111");
            }
        };
        r1.run();
        System.out.println("----------------------------------");
        // 格式一：无参，无返回值
        Runnable r2 = () -> System.out.println("test122222222222222222");
        r2.run();
    }


    // 格式二：lambda形参列表一个参数，lanbda体无返回值
    @Test
    public void test2() {
        Consumer<String> c1 = new Consumer<String>() {
            @Override
            // 一个参数，无返回值
            public void accept(String s) {
                System.out.println(s);
            }
        };
        c1.accept("test22222222222222");
        System.out.println("----------------------------------");

        Consumer<String> c2 = (String s) -> System.out.println(s);
        c2.accept("test22222222222222");
    }

    // 格式三：省略形参列表的类型，类型由编译器推断得出（类型推断）
    @Test
    public void test3() {

        Consumer<String> c3 = (s) -> System.out.println(s);
        c3.accept("test3333333333333333");
    }

    // 格式四：lambda形参列表只有一个参数时，可以省略括号
    @Test
    public void test4() {
        Consumer<String> c3 = s -> System.out.println(s);
        c3.accept("test44444444444444444444");
    }

    // 格式五：lambda需要两个或两个以上参数，多条执行语句，且有返回值
    @Test
    public void test5() {
        Comparator<String> c4 = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(c4.compare("aa","bb"));
        System.out.println("----------------------------------");

        Comparator<String> c5 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(c5.compare("bb","aa"));
    }

    // 格式六：lambda体只有一条语句时，return和大括号若有，都可以省略
    @Test
    public void test6() {
        Comparator<String> c6 = (o1, o2) -> o1.compareTo(o2);

        System.out.println(c6.compare("bb","aa"));
    }
}
