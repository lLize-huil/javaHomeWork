package com.jvm.week1.homeWork2;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件
 * 执行 Hello 方法
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件
 */
public class HelloClassLoader extends ClassLoader{

    /**
     * 读取文件内容
     * @param path
     * @return
     */
    public byte[] getFileByte(String path){
        File fileJson = new File(path);
        Long filelength = fileJson.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(path);
            in.read(filecontent);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filecontent;
    }


    /**
     * 读取 Hello.xlass 中 Base64的码
     * @param name
     * @return
     */
    protected Class<?> findClass(String name){
        byte[] bytes = getFileByte("workResource/week1/homework2/Hello.xlass");
        byte[] newBytes = new byte[bytes.length];
        for(int i = 0; i < bytes.length; i++){
            newBytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name,newBytes,0,newBytes.length);
    }

    public static void main(String[] args) throws Exception {
        //拿到class
        Class<?>  clazz  = new HelloClassLoader().loadClass("Hello");
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用实例方法
        Method method = clazz.getMethod("hello");
        method.invoke(instance);
//        //循环输出这个class中的方法名
//        for (Method method : clazz.getDeclaredMethods()){
//            System.out.println(method);
//            System.out.println(clazz.getSimpleName() + "." + method.getName());
//        }
    }


}
