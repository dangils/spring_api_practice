package com.koreait.day3_2.generic;

public class Main {
    public static void main(String[] args) {
        Box<Integer> box1 = Util.<Integer>boxing(100);
        int inValue = box1.getT();

        Box<String> box2 = Util.boxing("문자열"); //Util 뒤에 제네릭 안써도 무방
        String stringValue = box2.getT();

        System.out.println("inValue:" +inValue);
        System.out.println("stringValue: "+ stringValue);

    }

}
