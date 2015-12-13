package com.quangduong.calculator;

import java.math.BigDecimal;

/**
 * Created by QuangDuong on 12/10/2015.
 */
public class ArithmaticCalculatorCommand implements Command {
    private char operator;
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;
    private Calculator calculator;

    public ArithmaticCalculatorCommand( char operator, BigDecimal leftOperand, BigDecimal rightOperand, Calculator calculator ) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
        this.calculator = calculator;
    }

    /*
     * This method invoke the three argument operation method that is only used for arithmetic calculations.
     * @param operator
     * @param leftOperand
     * @param rightOperand   *
     * @see org.sanjaya.Command#calculate()
     */
    public void calculate() {
        calculator.operation( operator, leftOperand, rightOperand );
    }
}
