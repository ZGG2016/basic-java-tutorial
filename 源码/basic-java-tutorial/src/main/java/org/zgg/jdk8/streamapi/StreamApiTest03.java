package org.zgg.jdk8.streamapi;

import org.zgg.jdk8.data.Employee;
import org.zgg.jdk8.data.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 终止操作:
 *    - 会从流的流水线生成结果。其结果可以是任何不是流的值，例如：List、Integer，甚至是 void 。
 *    - 流进行了终止操作后，不能再次使用
 * */
public class StreamApiTest03 {

    // 1 匹配与查找
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        // allMatch(Predicate p) 检查是否匹配所有元素
        // 是否所有的员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        // anyMatch(Predicate p)——检查是否至少匹配一个元素。
        // 是否存在员工的工资大于 10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

        // noneMatch(Predicate p)——检查是否没有匹配的元素。
        // 是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().contains("雷"));
        System.out.println(noneMatch);

        // findFirst() 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);

        // findAny() 返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);

    }

    @Test
    public void test2(){
        List<Employee> employees = EmployeeData.getEmployees();

        // count() 返回流中元素总数
        long count = employees.stream().filter(e -> e.getAge() > 30).count();
        System.out.println(count);

        // max(Comparator c) 返回流中最大值
        // 返回最高的工资
        Optional<Double> max = employees
                .stream()
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println(max);

        // min(Comparator c) 返回流中最小值
        // 返回最低工资的员工
        Optional<Employee> min = employees
                .stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min);

        // forEach(Consumer c) 内部迭代
        // (使用 Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部迭代——它帮你把迭代做了)
        employees
                .stream()
                .map(Employee::getSalary)
                .forEach(System.out::println);

    }

    // 2 归约
    @Test
    public void test3(){

        // reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 T
        // 使用初始值iden和传来的值，迭代执行 b 操作
        // 计算1-10的自然数的和
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = nums.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

        // reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        // 计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> reduce1 = employees
                .stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce1);
    }

    // 3 收集
    @Test
    public void test4(){
        // collect(Collector c)  将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
        // Collector 接口中方法的实现决定了如何对流执行收集的操作(如收集到 List、Set、Map)。
        // 另外， Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例
        // 查找工资大于6000的员工，结果返回为一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> collect = employees
                .stream()
                .filter(e -> e.getSalary() > 6000)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

}
