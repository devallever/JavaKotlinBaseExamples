package com.allever.example.java.annotation;

import java.lang.annotation.Annotation;
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

    @MyAnnotation2(id = 23, msg = "2e")
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
                    MyAnnotation myAnnotation = declaredMethod.getAnnotation(MyAnnotation.class);
                    print("MyAnnotation id = " + myAnnotation.id());
                    print("MyAnnotation msg = " + myAnnotation.msg());

                    declaredMethod.invoke(annotationTest, 0);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.getCause().printStackTrace();
                }
            }

            for (Annotation annotation : declaredMethod.getAnnotations()) {
                print(declaredMethod.getName() + " 方法 应用了注解 " + annotation.annotationType().getSimpleName());
            }
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
