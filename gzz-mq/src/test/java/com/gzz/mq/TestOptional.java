package com.gzz.mq;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TestOptional {
    public static void main(String[] args) {
        /* of */
        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("YanWei");
        //传入参数为null，抛出NullPointerException.
        //Optional<String> someNull = Optional.of(null);

        /*opNullable*/
        Optional empty = Optional.ofNullable(null);

        /*isPresent*/
        System.out.println("name.isPresent()===================================================");
        if (name.isPresent()) {
            System.out.println(name.get());//输出YanWei
        }

        /*get*/
        System.out.println("Get===================================================");
        try {
            System.out.println(empty.get());
        } catch (NoSuchElementException ex) {
            System.err.println(ex.getMessage());
        }

        /*ifPresent*/
        System.out.println("name.isPresent()===================================================");
        name.ifPresent((value) -> {
            System.out.println("The length of the value is: " + value.length());
        });

        /*orElse*/
        System.out.println("orElse ===================================================");
        System.out.println(empty.orElse("There is no value present!"));
        System.out.println(name.orElse("There is some value!"));

        /*orElseGet*/
        System.out.println("orElseGet ===================================================");
        System.out.println(empty.orElseGet(() -> "Default Value"));
        System.out.println(name.orElseGet(String::new));

        /*orElseThrow*/
        try {
            empty.orElseThrow(IllegalArgumentException::new);
        } catch (Throwable ex) {
            System.out.println("error:" + ex.getMessage());
        }

        /*map*/
        System.out.println("map ===================================================");
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        /*flatMap*/
        System.out.println("flatMap ===================================================");
        upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.get());

        /*filter*/
        System.out.println("filter ===================================================");
        List<String> names = Arrays.asList("YanWei","YanTian");
        for(String s:names)
        {
            Optional<String> nameLenLessThan7 = Optional.of(s).filter((value) -> value.length() < 7);
            System.out.println(nameLenLessThan7.orElse("The name is more than 6 characters"));
        }
    }
}
