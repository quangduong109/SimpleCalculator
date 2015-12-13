package com.quangduong.calculator;

/**
 * Created by QuangDuong on 12/10/2015.
 */
public class Invoker {
    public void compute( Command command )
    {
        command.calculate();
    }
}
