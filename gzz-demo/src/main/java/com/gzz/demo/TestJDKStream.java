package com.gzz.demo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * （主要包含：Map, FlatMap, reduce, groupingBy, joining, reducing, parallelStream, peek 等）
 * @RunWith就是一个运行器
 *
 * @RunWith(JUnit4.class)就是指用JUnit4来运行
 *
 * @RunWith(SpringJUnit4ClassRunner.class),让测试运行于Spring测试环境
 *
 * @RunWith(Suite.class)的话就是一套测试集合
 *
 * @ContextConfiguration Spring整合JUnit4测试时，使用注解引入多个配置文件
 *
 * 单个文件
 * @ContextConfiguration(Locations="classpath：applicationContext.xml")
 * @ContextConfiguration(classes = SimpleConfiguration.class)
 *
 * 多个文件时，可用
 * @ContextConfiguration(locations = { "classpath:spring1.xml", "classpath:spring2.xml" })
 */
@RunWith(JUnit4.class) //就是指用JUnit4来运行
public class TestJDKStream {

    List<Student> studentList = new ArrayList<>();

    @Before
    public void init() {

        Student student1 = new Student();
        student1.setAge(1);
        student1.setName("name1name");
        studentList.add(student1);

        Student student2 = new Student();
        student2.setAge(2);
        student2.setName("name2name");
        studentList.add(student2);

        Student student3 = new Student();
        student3.setAge(3);
        student3.setName("name3name");
        studentList.add(student3);
    }



    @Test
    public void testFlatMap01() {
        List<String> collect = studentList.stream()
                .flatMap(p -> Arrays.stream(p.getName().split("me")))
                .collect(Collectors.toList());

        collect.forEach(c -> {
            System.out.println(c);
            System.out.println("========================");
            // 分别输出：
            // 空
            // e1
            // e
            // 空
            // e2
            // e
            // 空
            // e3
            // e
        });
        System.out.println(String.valueOf(collect.size()));
        // 分别输出：
        // 9
    }

    @Test
    public void testReduce01() {
        Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, (count, item) -> {
            System.out.println("count = " + count +  "   item = " + item);
            return count + 1;
        });
        System.out.println(reduce.toString());
        // 分别输出：
        //  count = 10
        //  item = 1
        //  count = 11
        //  item = 2
        //  count = 13
        //  item = 3
        //  count = 16
        //  item = 4
        //  20
    }


    @Test
    public void toListTest() {
        List<String> collect = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        collect.forEach(c -> {
            System.out.println(c);
        });
        // 分别输出：
        // name1name
        // name2name
        // name3name

        Set<Integer> collect1 = studentList.stream()
                .map(Student::getAge)
                .collect(Collectors.toSet());
        collect1.forEach(c -> {
            System.out.println(c.toString());
        });
        // 分别输出：
        // 1
        // 2
        // 3
    }


    @Test
    public void toMapTest() {
        Map<String, Integer> map = studentList.stream()
                .collect(
                    Collectors.toMap(Student::getName, Student::getAge)
                );
        map.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        // 分别输出：
        // name1name : 1
        // name2name : 2
        // name3name : 3
    }


    /**
     * 指定类型
     */
    @Test
    public void toTreeSetTest() {
        TreeSet<String> collect = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        collect.forEach(c -> {
            System.out.println(c);
        });
        // 分别输出：
        // name1name
        // name2name
        // name3name
    }

    /**
     * 分组
     */
    @Test
    public void toGroupTest() {
        Map<Boolean, List<Student>> collect = studentList.stream()
                .collect(
                        Collectors.groupingBy(
                                c -> "name2name".equals(c.getName())
                        )
                );

        collect.forEach((b, l) -> {
            System.out.println(b + " : " + l.toString());
        });
        // 分别输出：
        // false : [Student{name='name1name', age=1}, Student{name='name3name', age=3}]
        // true : [Student{name='name2name', age=2}]
    }

    /**
     * 分隔
     */
    @Test
    public void testJoiningTest() {
        String collect = studentList.stream()
                .map(s -> s.getName())
                .collect(Collectors.joining(" | ", " { ", " } "));
        System.out.println(collect);
        // 分别输出：
        // { name1name | name2name | name3name }
    }

    /**
     * 自定义
     */
    @Test
    public void reduce() {
        List<String> collect = Stream.of("1", "2", "3").collect(
                Collectors.reducing(
                        new ArrayList<String>(), c -> Arrays.asList(c), (x, y) -> {
                            x.addAll(y);
                            return x;
                        }
                )
        );
        collect.forEach(c -> {
            System.out.println(c);
        });
        // 分别输出：
        // 1
        // 2
        // 3
    }

    @Test
    public void streamTest() {
        // 输入流的大小并不是决定并行化是否会带来速度提升的唯一因素，性能还会受到编码方式和核的数量的影响
        // 影响性能的五要素是：数据大小、源数据结构、值是否可装箱、可用的CPU核数量、处理每个元素所花的时间

        int size = 1000_0000;
        List<Integer> list = new ArrayList<>(size);
        for (Integer i = 0; i < size; i++) {
            list.add(new Integer(i));
        }

        List<Integer> temp1 = new ArrayList<>(size);
        // old
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        for (Integer i : list) {
            temp1.add(i);
        }
        stopWatch1.stop();
        System.out.println("old time = " + stopWatch1.getTime());

        // synchronized
        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        list.stream().collect(Collectors.toList());
        stopWatch2.stop();
        System.out.println("synchronized time = " + stopWatch2.getTime());

        // concurrent
        StopWatch stopWatch3 = new StopWatch();
        stopWatch3.start();
        list.parallelStream().collect(Collectors.toList());
        stopWatch3.stop();
        System.out.println("concurrent time = " + stopWatch3.getTime());
    }

    @Test
    public void peekTest() {
        // 通过 peek 可以查看每个值，并且继续流操作
        studentList.stream().map(s -> s.getName()).peek(p -> {
            System.out.println(p);
        }).collect(Collectors.toList());
    }

    class Student {
        public String name;
        public Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}