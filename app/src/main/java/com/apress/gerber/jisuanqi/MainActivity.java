package com.apress.gerber.jisuanqi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.apress.gerber.jisuanqi.R.drawable.bu;

public class MainActivity extends Activity {

    final Double e = 2.718281828459045;
    final Double PI = 3.141592653589793;

    String str_num;
    Double frist_num;
    String operator;
    Double next_num;
    Button bnn_op;
    Button bn;
    TextView num_tv;

    boolean havefrist_num;  //状态信号量
    boolean havepoint;
    boolean op_equal;
    boolean pressed_eque;
    boolean find_BUG;

    int button_date1[] = {R.id.buttonNum0, R.id.buttonNum1, R.id.buttonNum2, R.id.buttonNum3, R.id.buttonNum4, R.id.buttonNum5, R.id.buttonNum6,
            R.id.buttonNum7, R.id.buttonNum8, R.id.buttonNum9, R.id.buttonNumPoint,};
    int button_date2[] = {R.id.buttone, R.id.buttonPI, R.id.buttonrand};

    int button_one_op2[] = {R.id.buttonNum百分号, R.id.button3sqrtx, R.id.buttonEx, R.id.button10x, R.id.buttonX2, R.id.buttonX3};
    int button_one_op3[] = {R.id.buttonxx, R.id.buttonln, R.id.buttonlog10, R.id.button1x, R.id.button2sqrtx};

    int button_two_op[] = {R.id.buttonNum除, R.id.buttonNumx, R.id.buttonNum减, R.id.buttonNumplus};

    public boolean isPortrait() {
        Configuration configuration = this.getResources().getConfiguration(); //获取设备信息
        int LorP = configuration.orientation;

        return LorP == Configuration.ORIENTATION_PORTRAIT;

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini();
        num_tv = findViewById(R.id.numtext);


        for (int i = 0; i < button_date1.length; i++) { //date1数字及小数点部分
            bn = findViewById(button_date1[i]);
            bn.setOnClickListener(new View.OnClickListener() {

                Button bnn = bn;

                public void onClick(View view) {
                    if (pressed_eque) {
                        ini();
                    }
                    op_equal = false;
                    bnn_op.setBackground(getDrawable(R.drawable.defin));
                    String str = bnn.getText().toString();
                    if (!str.equals(".")) {  //点击数字
                        if (str_num.equals("0") && !str.equals("0"))
                            str_num = str;    //当前为零则替换
                        else if (!(str_num.equals("0") && str.equals("0")))
                            str_num += str;
                    } else    //点击小数点
                    {
                        if (!havepoint) {
                            havepoint = true;
                            str_num += str;
                        }

                    }
                    autoSettext_size();
                    num_tv.setText(str_num);

                }
            });
        }


        for (int i = 0; i < button_date2.length; i++)  //date2 约定数字及随机数
        {
            bn = findViewById(button_date2[i]);
            bn.setOnClickListener(new View.OnClickListener() {
                Button bnn = bn;

                public void onClick(View view) {
                    switch (bnn.getText().toString()) {

                        case "e":
                            str_num = "2.718281828459045";
                            break;
                        case "π":
                            str_num = "3.141592653589793";
                            break;
                        case "Rand":
                            java.util.Random r = new java.util.Random();
                            Double Randomstr = r.nextDouble();
                            Randomstr = Randomstr % 1;
                            str_num = Randomstr.toString();
                            break;
                    }

                    num_tv.setText(str_num);

                }
            });
        }


        bn = findViewById(R.id.buttonNum加减); //+/-
        bn.setOnClickListener(new View.OnClickListener() {//+-
            public void onClick(View view) {
                if (str_num.charAt(0) == '-')
                    str_num = "+" + str_num.substring(1, str_num.length());
                else if (str_num.charAt(0) == '+')
                    str_num = "-" + str_num.substring(1, str_num.length());
                else
                    str_num = "-" + str_num;
                autoSettext_size();
                num_tv.setText(str_num);
            }
        });


        for (int i = 0; i < button_one_op2.length; i++)    //一元二类运算符
        {
            bn = findViewById(button_one_op2[i]);
            bn.setOnClickListener(new View.OnClickListener() {
                Button bnn = bn;

                public void onClick(View view) {
                    Double dd = Double.valueOf(str_num).doubleValue();
                    switch (bnn.getText().toString()) {
                        case "%":
                            dd = dd * 0.01;
                            break;
                        case "³√x":
                            dd = Math.pow(dd, 1 / 3.0);
                            break;
                        case "e^x":
                            dd = Math.pow(e, dd);
                            break;
                        case "10^x":
                            dd = Math.pow(10, dd);
                            break;
                        case "X²":
                            dd = Math.pow(dd, 2);
                            break;
                        case "X³":
                            dd = Math.pow(dd, 3);
                            break;

                    }
                    str_num = dd.toString();
                    autoSettext_size();
                    num_tv.setText(str_num);
                }
            });
        }

        for (int i = 0; i < button_one_op3.length; i++)   //一元三类
        {
            bn = findViewById(button_one_op3[i]);
            bn.setOnClickListener(new View.OnClickListener() {
                Button bnn = bn;

                public void onClick(View view) {
                    Double dd = Double.valueOf(str_num);
                    final Double eps = 1e-10;
                    switch (bnn.getText().toString()) {
                        case "x!":
                            if (dd - (double) dd.intValue() < eps)
                                str_num = "不可为小数";
                            else {
                                int id = 1;
                                for (int i = 1; i <= dd.intValue(); i++)
                                    id *= i;
                                dd = (double) id;
                                str_num = dd.toString();
                            }
                            break;
                        case "ln":
                            if (dd - (double) 0 < eps)
                                str_num = "不可为负数";
                            else {
                                dd = Math.log(dd) / Math.log(e);
                                str_num = dd.toString();
                            }
                            break;
                        case "log₁₀":
                            if (dd - (double) 0 < eps)
                                str_num = "不可为负数";
                            else {
                                dd = Math.log(dd);
                                str_num = dd.toString();
                            }
                            break;
                        case "1/x":
                            if (Math.abs(dd - (double) 0) < eps)
                                str_num = "不可为零";
                            else {
                                dd = Math.pow(dd, -1);
                                str_num = dd.toString();
                            }
                            break;
                        case "²√x":
                            if (dd - (double) 0 < eps)
                                str_num = "不可为负数";
                            else {
                                dd = Math.sqrt(dd);
                                str_num = dd.toString();
                            }


                    }
                    num_tv.setText(str_num);
                }
            });
        }


        bn = findViewById(R.id.buttonNumAC); //AC
        bn.setOnClickListener(new View.OnClickListener() { //AC
            @Override
            public void onClick(View view) {
                ini();
                autoSettext_size();
                num_tv.setText(str_num);
            }
        });


        for (int i = 0; i < button_two_op.length; i++)//加减乘除
        {
            bn = findViewById(button_two_op[i]);
            bn.setOnClickListener(new View.OnClickListener() {

                Button bnn = bn;

                public void onClick(View view) {
                    havepoint = false;
                    pressed_eque = false;
                    if (!op_equal) {
                        equal();
                        op_equal = true;
                    }
                    bnn_op.setBackground(getDrawable(R.drawable.defin));
                    bnn_op = bnn;
                    operator = bnn_op.getText().toString();
                    bnn_op.setBackground(getDrawable(R.drawable.bu));
                    if (!havefrist_num) {
                        frist_num = Double.valueOf(str_num).doubleValue();
                        havefrist_num = true;
                    }
                    str_num = "0";
                }
            });
        }

        bn = findViewById(R.id.buttonNumEqual); //=
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op_equal = false;
                if (pressed_eque) {
                    str_num = next_num.toString();
                }
                pressed_eque = true;
                equal();
                str_num = "0";
            }
        });

    }

    void equal() {
        String enser;
        Double e = null;
        next_num = Double.valueOf(str_num).doubleValue();
        switch (operator) {
            case ("÷"):
                e = frist_num / next_num;
                break;
            case ("×"):
                e = frist_num * next_num;
                break;
            case ("—"):
                e = frist_num - next_num;
                break;
            case ("+"):
                e = frist_num + next_num;
                break;
        }
        frist_num = e;
        enser = e.toString();
        if (enser.substring(enser.length() - 2, enser.length()).equals(".0"))
            enser = enser.substring(0, enser.length() - 2);

        autoSettext_size();
        num_tv.setText(enser);
    }

    void autoSettext_size() {
        if (isPortrait()) {//竖屏
            if (str_num.length() > 7) {
                float ts = 700 / (float) str_num.length();
                num_tv.setTextSize(ts);
            } else
                num_tv.setTextSize(100);
        } else {
            if (str_num.length() > 20) {
                float ts = 1800 / (float) str_num.length();
            } else
                num_tv.setTextSize(60);

        }

    }

    void ini() {
        str_num = "0";
        frist_num = 0.0;
        operator = "+";
        next_num = 0.0;

        bnn_op = findViewById(R.id.buttonNum除);
        havepoint = false;
        op_equal = false;
        havefrist_num = false;
        pressed_eque = false;
        find_BUG = false;
    }


}


