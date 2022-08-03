package org.zgg.jdk8.函数式接口;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * java内置的四大核心函数式接口：
 *   - 消费型 Consumer<T>     void accept(T t);     只接受，不返回
 *   - 供给型 Supplier<T>     T get();              不接受，只返回
 *   - 函数型 Function<T,R>   R apply(T t);         接收处理T，返回R
 *   - 断定型 Predicate<T>    boolean test(T t);    判断T是否满足某一约束
 * */
public class FuncInterfaceTest {

    // 消费型 Consumer<T>     void accept(T t);     只接受，不返回
    @Test
    public void test1() {
        spendMoney(100, money -> System.out.println("花了" + money + "元钱！"));
    }

    public void spendMoney(Integer money, Consumer consumer) {
        consumer.accept(money);
    }

    //-----------------------------------------------------------
    @Test
    public void test2(){
        Integer res = transformStr("12", s -> Integer.parseInt(s));
        System.out.println(res);
        System.out.println(res.getClass());
    }

    public Integer transformStr(String str, Function<String,Integer> function){
        return function.apply(str);
    }

    //-----------------------------------------------------------

    //-----------------------------------------------------------
    // 断定型 Predicate<T>    boolean test(T t);    判断T是否满足某一约束
    @Test
    public void test4() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        ArrayList<Integer> res = filterNumber(nums, num -> num > 5);
        System.out.println(res);
    }

    public ArrayList<Integer> filterNumber(List<Integer> nums, Predicate<Integer> predicate) {
        ArrayList<Integer> res = new ArrayList<>();
        for (Integer num : nums) {
            if (predicate.test(num)) {
                res.add(num);
            }
        }
        return res;
    };
}
