package com.gzz.socket;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static final String PW_PATTERN2 = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    public static final String PW_PATTERN1 = "^((?=\\w*?[A-Z]) (?=\\S*?[a-z]) (?=\\S*?[0-9]).{6,})\\S$";
    public static final String PW_PATTERN = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]))[\\w\\W]{6,18}$"; //^￥
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        String pw1 = "ABCDEFGHIG";
        String pw2 = "abcdefghig";
        String pw3 = "0123456789";
        String pw4 = "~!@#$%^&*()";
        String pw5 = "ABCDEabcde";
        String pw6 = "ABCDE01234";
        String pw7 = "ABCDE!@#$%";
        String pw8 = "abcde01234";
        String pw9 = "abcde!@#$%";
        String pw10 = "01234!@#$%";
        String pw11 = "abcde01234!@#$%";
        String pw12 = "ABCDE01234!@#$%";
        String pw13 = "2ABCDEabcde!@#$%";
        String pw14 = "ABCDEabcde01234";
        String pw15 = "Aa0!";
        String pw16="!aabc01dABC";         //符合要求密码
        String pw17 = "1234ABCDEabcde0";

        System.out.println("1."+pw1.matches(PW_PATTERN));
        System.out.println("2."+pw2.matches(PW_PATTERN));
        System.out.println("3."+pw3.matches(PW_PATTERN));
        System.out.println("4."+pw4.matches(PW_PATTERN));
        System.out.println("5."+pw5.matches(PW_PATTERN));
        System.out.println("6."+pw6.matches(PW_PATTERN));
        System.out.println("7."+pw7.matches(PW_PATTERN));
        System.out.println("8."+pw8.matches(PW_PATTERN));
        System.out.println("9."+pw9.matches(PW_PATTERN));
        System.out.println("10."+pw10.matches(PW_PATTERN));
        System.out.println("11."+pw11.matches(PW_PATTERN));
        System.out.println("12."+pw12.matches(PW_PATTERN));
        System.out.println("13."+pw13.matches(PW_PATTERN));
        System.out.println("14."+pw14.matches(PW_PATTERN));
        System.out.println("15."+pw15.matches(PW_PATTERN));
        System.out.println("16."+pw16.matches(PW_PATTERN));
        System.out.println("17."+pw17.matches(PW_PATTERN));

        //assertTrue( true );
    }
}
