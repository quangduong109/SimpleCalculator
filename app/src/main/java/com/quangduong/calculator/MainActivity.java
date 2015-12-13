package com.quangduong.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_function;
    TextView tv_result;
    String func;
    String result;
    double equal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_function =(TextView) findViewById(R.id.tv_func);
        tv_result = (TextView) findViewById(R.id.tv_result);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_equ).setOnClickListener(this);
        findViewById(R.id.btn_C).setOnClickListener(this);

        func = "";
        result = "0";
    }

    @Override
    public void onClick(View v) {
        if(equal == 1 || result == "0" || result == "NaN" || result == "Unlimited"){
            result="";
            equal = 0;
        }
        switch (v.getId()){
            case R.id.btn_0:
                result += "0";
                break;
            case R.id.btn_1:
                result += "1";
                break;
            case R.id.btn_2:
                result += "2";
                break;
            case R.id.btn_3:
                result += "3";
                break;
            case R.id.btn_4:
                result += "4";
                break;
            case R.id.btn_5:
                result += "5";
                break;
            case R.id.btn_6:
                result += "6";
                break;
            case R.id.btn_7:
                result += "7";
                break;
            case R.id.btn_8:
                result += "8";
                break;
            case R.id.btn_9:
                result += "9";
                break;
            case R.id.btn_add:
                result += "+";
                break;
            case R.id.btn_sub:
                result += "-";
                break;
            case R.id.btn_mul:
                result += "*";
                break;
            case R.id.btn_div:
                result += "/";
                break;
            case R.id.btn_del:
                if(result.length() > 0 ){
                    result = result.substring(0,result.length() - 1);
                }
                if(result.length() == 0){
                    result = "0";
                }
                break;
            case R.id.btn_dot:
                result += ".";
                break;
            case R.id.btn_equ:
                func = result;
                if(result.startsWith("*") || result.startsWith("/") ||
                    result.endsWith("+") || result.endsWith("-") ||
                        result.endsWith("*") || result.endsWith("/")){
                    result = "NaN";
                }else{
                    try{
                        result = CalculatorParser.instance.start(func);
                    }catch (Exception ex){
                        result = "Unlimited";
                    }

                }

                equal = 1;
                tv_function.setText(func + "=");
                break;
            case R.id.btn_C:
                result ="0";
                func = "";
                tv_function.setText(func);
                break;
        }
        tv_result.setText(result);
    }
}
