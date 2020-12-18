package com.example.login.Bank;
//抽象接口
public abstract class Component {
    public abstract void add(Component c);
    public abstract void remove(Component c);
    public abstract Component getShelves(int i);
    public abstract void operation();
}
