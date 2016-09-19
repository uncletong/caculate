package com.example.learner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divide;
    Button btn_del;
    Button btn_clear;
    Button btn_point;
    Button btn_equal;
    EditText et_input;
    boolean flag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caculate_main);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        et_input = (EditText) findViewById(R.id.et_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String str = et_input.getText().toString();
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (flag) {
                    et_input.setText("");
                    str="";
                    flag = false;
                }
                et_input.setText(str+((Button) view).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if (flag) {
                    et_input.setText("");
                    str="";
                    flag = false;
                }
                et_input.setText(str+" "+((Button) view).getText()+" ");
                break;
            case R.id.btn_clear:
                flag=false;
                et_input.setText("");
                break;
            case R.id.btn_del:
                if (flag) {
                    et_input.setText("");
                    flag = false;
                }
                else if (!str.equals(""))
                    et_input.setText(str.substring(0,str.length()-1));
                break;
            case R.id.btn_equal:
                getResult();
                break;
        }
    }

    private void getResult(){
        String exp=et_input.getText().toString();
        if (exp.equals("")||exp==null)
            return;
        if (!exp.contains(" "))
            return;
        if (flag) {
            flag = false;
            return;
        }
        flag=true;
        double result=0;
        String s1=exp.substring(0,exp.indexOf(" "));
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        String s2=exp.substring(exp.indexOf(" ")+3);
        if (!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
           switch (op){
               case "+":
                   result=d1+d2;
                   break;
               case "-":
                   result=d1-d2;
                   break;
               case "*":
                   result=d1*d2;
                   break;
               case "/":
                   if (d2==0)
                       result =0;
                   else
                       result=d1/d2;
                   break;
           }
            if (!s1.contains(".")&&!s2.contains(".")&&!op.equals("/")) {
                int r = (int) result;
                et_input.setText(r+"");
            }
            else
                et_input.setText(result+"");
        }
        else if (!s1.equals("")&&s2.equals(""))
            et_input.setText(exp);
        else if (s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            switch (op){
                case "+":
                    result=d2;
                    break;
                case "-":
                    result=-d2;
                    break;
                case "*":
                    result=0;
                    break;
                case "/":
                    result=0;
                    break;
            }
            if (!s2.contains(".")&&!s2.contains(".")&&!op.equals("/")) {
                int r = (int) result;
                et_input.setText(r + "");
            }
            else
                et_input.setText(result+"");
        }
        else
            et_input.setText("");


    }
}


