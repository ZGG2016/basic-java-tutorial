package org.zgg.jdk8.函数式接口;
/*
* 函数式接口：
*    - 只包含一个抽象方法的接口。
*    - 可以通过lambda表达式创建该接口的对象
*    - 在接口上使用@FunctionalInterface注解，这样可以检查它是否是一个函数式接口
* */
@FunctionalInterface
public interface CustomFuncInterface {
    public abstract void eatFood();
}
