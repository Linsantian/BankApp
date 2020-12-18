package com.example.login.Bank;

import java.util.ArrayList;

public class Warehouse extends Component {
    private ArrayList list = new ArrayList();

    @Override
    public void add(Component c) {
        list.add(c);
    }

    @Override
    public void remove(Component c) {
        list.remove(c);
    }

    @Override
    public Component getShelves(int i) {
        return (Component) list.get(i);
    }

    @Override
    public void operation() {
        for (Object o : list) {
            ((Component) o).operation();
        }
    }
}
