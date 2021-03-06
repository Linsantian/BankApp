package com.example.login.Staff;

public class Staff {
    //私有属性
    private static int id = 1;
    // Staff的唯一实例
    private static Staff instance = null;

    //将构造函数私有，防止外界构造SingletonB实例
    private Staff() {
    }

    //获取SingletonB的唯一实例，同样用synchronized关键字保证某一时刻只有一个线程调用此方法。
    public static synchronized Staff getInstance() {
        //如果instance为空，便创建一个新的SingletonB实例，否则，返回已有的实例
        if (instance == null) {
            instance = new Staff();}
            return instance;
        }
        public synchronized int getID () {
            return id;
        }
        public synchronized void nextID () {
            id++;
        }


}
