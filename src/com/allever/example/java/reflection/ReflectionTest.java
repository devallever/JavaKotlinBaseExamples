package com.allever.example.java.reflection;

public class ReflectionTest {
    public static void main(String[] args) {
        new ReflectionTest().doMain();
    }

    public void doMain() {
        getClassObject();
        getClassName();
    }


    /***
     * 获取Class 类的方式有三种
     */
    private void getClassObject() {
        Cat cat = new Cat();
        Class classObj = cat.getClass();

        classObj = Cat.class;
        classObj = int.class;
        classObj = String.class;
        

        try {
            classObj = Class.forName("com.allever.example.java.reflection.Cat");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getClassName() {

    }

}
