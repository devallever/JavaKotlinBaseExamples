package com.allever.example.java.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Person(role = "xm")
@Person(role = "winchen")
@MyAnnotation(id = 1, msg = "1")
public class AnnotationTest {

    @MyAnnotation(id = 2, msg = "h")
    private int mId;

    public static void main(String[] args) {
        AnnotationTest test = new AnnotationTest();
        test.doMain();
    }

    @MyAnnotation(id = 3, msg = "e")
    private void testMethod(int id) {
        print("执行了注解方法");
    }

    private void normalMethod(String msg) {

    }

    private void doMain(){
        AnnotationTest annotationTest = new AnnotationTest();
        Class clz = annotationTest.getClass();

        for (Method declaredMethod : clz.getDeclaredMethods()) {
            print("方法： " + declaredMethod.getName());
            print("是否注解MyAnnotation的方法 = " + declaredMethod.isAnnotationPresent(MyAnnotation.class));
            if (declaredMethod.isAnnotationPresent(MyAnnotation.class)) {
                declaredMethod.setAccessible(true);
                try {
                    declaredMethod.invoke(annotationTest, 0);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.getCause().printStackTrace();
                }
            } else {

            }
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
