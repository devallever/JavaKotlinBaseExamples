package com.allever.example.java.reflection;

import com.allever.example.java.util.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static void main(String[] args) {
        new ReflectionTest().doMain();
    }

    public void doMain() {
        getClassObject();
        className();
        classModifier();
        classFiled();
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

    /***
     * 获取类名
     */
    private void className() {
        print("\n引用类型");
        printClzName(Cat.class);

        print("\n内部类");
        printClzName(Cat.CatInner.class);

        print("\n基本类型");
        printClzName(int.class);

        print("\n基本类型数组");
        printClzName(new int[2][2].getClass());

        print("\n引用类型数组");
        printClzName(new Cat[2][2].getClass());

        print("\n匿名内部类");
        printClzName(new Runnable() {
            @Override
            public void run() {

            }
        }.getClass());

        class Test{ }
        print("\n局部内部类");
        printClzName(Test.class);

    }

    private void classModifier() {
        print("\n");
        printClassModifier(Animal.class);
        printClassModifier(Cat.CatInner.class);
    }

    private void classFiled() {
        Class clz = Cat.class;
        try {
            //获取私有属性
            Field declareField = clz.getDeclaredField("age");
            //获取非私有属性，包括父类
            Field field = clz.getField("head");
            //获取全部属性，不包括父类
            Field[] declaredFields = clz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                print("declareField name = " + declaredField.getName());
            }

            //获取全部public属性，包括父类
            Field[] fields = clz.getFields();
            for (Field fieldItem : fields) {
                print("field name = " + fieldItem.getName());
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void printClassModifier(Class clz) {
        int value = clz.getModifiers();
        String modifers = Modifier.toString(value);
        print("类修饰符： " + modifers);
        print("isPublic = " + Modifier.isPublic(value));
    }

    private void printClzName(Class clz) {
        print("getName = " + clz.getName());
        print("getSimpleName = " + clz.getSimpleName());
        print("getCanonicalName = " + clz.getCanonicalName());
    }

    private void print(String msg) {
        LogUtils.d(msg);
    }

}
