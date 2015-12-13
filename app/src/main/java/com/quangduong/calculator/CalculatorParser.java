package com.quangduong.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * Created by QuangDuong on 12/10/2015.
 */
public class CalculatorParser {

    public static final CalculatorParser instance = new CalculatorParser();
    private CalculatorParser(){

    }
    /*
     * This is the starting point of the program. It receives input from the command line
     * and process them further and sends to calculate function. At the end this method
     * displays the calculated result.
     */
    public String start(String line)
    {
        List<String> postfixString = CalculatorParser.infixToPostfixConvert( line );
        Calculator calculator = Calculator.getInstance();
        calculator.setCurrent(new BigDecimal(0));

        calculate(calculator, postfixString );

        return String.valueOf(calculator.getCurrent());

    }

    /*
     * This method keeps a stack to process postfix version of the input and execute the right command implementation.
     * Currently this method supports for arithmetic command calculations only.
     * @param Cal
     * @param postFix
     */
    public void calculate(Calculator cal, List<String> postFix) {

        if(postFix.size() == 1){
            cal.setCurrent(new BigDecimal(postFix.get(0)));
            return;
        }
        Stack<BigDecimal> stack = new Stack<BigDecimal>();

        for ( int i = 0; i < postFix.size(); i++ ) {

            String next = postFix.get(i);

            if (next.equals("+") || next.equals("-") || next.equals("*")
                    || next.equals("/")) {
                ArithmaticCalculatorCommand cmd = new ArithmaticCalculatorCommand(
                        next.charAt(0), stack.pop(), stack.pop(), cal);
                Invoker invoker = new Invoker();
                invoker.compute(cmd);
                stack.push(cal.getCurrent());
            } else if ( false ){

            }
            else
            {
                stack.push(new BigDecimal(next.trim()));
            }
        }
    }

    /*
     * This method convert the infix into postfix in order to proceed in the calculation.
     * @param input
     */
    public static List<String> infixToPostfixConvert(String input) {

        int priority = 0;
        String postfixBuffer = "";
        Stack<Character> stack = new Stack<Character>();
        List<String> postfixArray = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                if((ch == '+' || ch == '-') && ((postfixArray.size() > 0 && !isNumber(String.valueOf(input.charAt(i-1)))) || input.substring(0,i).length() == 0) && i < input.length()-1){
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(ch).append(input.charAt(i+1));
                    postfixBuffer = buffer.toString();
                    i++;
                    continue;

                }
                if (postfixBuffer.length() > 0) {
                    postfixArray.add(postfixBuffer);
                }
                postfixBuffer = "";
                // check the precedence
                if (stack.size() <= 0)
                    stack.push(ch);
                else {
                    Character chTop = (Character) stack.peek();
                    if (chTop == '*' || chTop == '/')
                        priority = 1;
                    else
                        priority = 0;
                    if (priority == 1) {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        } else { // Same
                            postfixArray.add(String.valueOf(stack.pop()));
                            i--;
                        }
                    } else {
                        if (ch == '+' || ch == '-') {
                            postfixArray.add(String.valueOf(stack.pop()));
                            stack.push(ch);
                        } else
                            stack.push(ch);
                    }
                }
            } else {
                postfixBuffer += ch;
            }
        }
        postfixArray.add(postfixBuffer);
        int len = stack.size();
        for (int j = 0; j < len; j++)
            postfixArray.add(stack.pop().toString());

        return postfixArray;
    }

    private static boolean isNumber(String s) {
        String regexStr = "^[0-9]*$";

        if(s.trim().matches(regexStr))
        {
            return true;
        }
        else{
            return false;
        }
    }
}
