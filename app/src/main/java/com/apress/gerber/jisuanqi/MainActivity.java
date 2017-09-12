package com.apress.gerber.jisuanqi;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.apress.gerber.jisuanqi.R.drawable.bu;

public class MainActivity extends AppCompatActivity {

    String frist_str ;
    boolean havefrist_num;
    Double frist_num ;
    String operator ;
    Double next_num ;
    boolean havepoint ;
    boolean op_equal;
    boolean pressed_eque;
    Button bnn_op;
    Button bn;
    TextView num_tv;
    int button[] ={R.id.buttonNum0,R.id.buttonNum1,R.id.buttonNum2,R.id.buttonNum3,R.id.buttonNum4,R.id.buttonNum5,R.id.buttonNum6,
                    R.id.buttonNum7,R.id.buttonNum8,R.id.buttonNum9,R.id.buttonNumPoint};
    int button_op[] ={R.id.buttonNum除,R.id.buttonNumx,R.id.buttonNum减,R.id.buttonNumplus};

    public boolean isPortrait()
    {
        Configuration configuration = this.getResources().getConfiguration(); //获取设备信息
        int LorP = configuration.orientation;

        if(LorP == configuration.ORIENTATION_PORTRAIT)
            return true;
        else
            return false;

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini();
        num_tv = (TextView)findViewById(R.id.numtext);

        for(int i=0;i<11;i++) { //数字部分
            bn = (Button) findViewById(button[i]);
            bn.setOnClickListener(new View.OnClickListener() {

                Button bnn =bn;
                public void onClick(View view) {
                    if(pressed_eque)
                    {
                        ini();
                        pressed_eque =false;
                    }
                    op_equal = false;
                    bnn_op.setBackground(getDrawable(R.drawable.defin));
                    String str = bnn.getText().toString();
                    if(!str.equals(".")) {  //点击数字
                        if (frist_str.equals("0") && !str.equals("0"))
                            frist_str = str;    //当前为零则替换
                        else if (!(frist_str.equals("0") && str.equals("0")))
                            frist_str += str;
                    }
                    else    //点击小数点
                    {
                        if(!havepoint)
                        {
                            havepoint = true;
                            frist_str+=str;
                        }

                    }
                    autoSettext_size();
                    num_tv.setText(frist_str);

                }
            });
        }

        bn = (Button) findViewById(R.id.buttonNumAC);
        bn.setOnClickListener(new View.OnClickListener() { //AC
            @Override
            public void onClick(View view) {
                ini();
                autoSettext_size();
                num_tv.setText(frist_str);
            }
        });
        bn = (Button) findViewById(R.id.buttonNum加减);
        bn.setOnClickListener(new View.OnClickListener() {//+-
            @Override
            public void onClick(View view) {
                if(frist_str.charAt(0)=='-')
                    frist_str = "+"+frist_str.substring(1,frist_str.length());
                else if (frist_str.charAt(0)=='+')
                    frist_str = "-"+frist_str.substring(1,frist_str.length());
                else
                    frist_str = "-"+frist_str;
                autoSettext_size();
                num_tv.setText(frist_str);
            }
        });

        bn = (Button) findViewById(R.id.buttonNum百分号);
        bn.setOnClickListener(new View.OnClickListener() {//百分号
            @Override
            public void onClick(View view) {
                Double dd = Double.valueOf(frist_str).doubleValue();
                dd = dd*0.01;
                frist_str = dd.toString();
                autoSettext_size();
                num_tv.setText(frist_str);
            }
        });

        for(int i=0;i<4;i++)//加减乘除
        {
            bn =(Button) findViewById(button_op[i]);
            bn.setOnClickListener(new View.OnClickListener() {

                Button bnn = bn;
                public void onClick(View view) {
                    pressed_eque =false;
                    if(!op_equal)
                    {
                        equal();
                        op_equal=true;
                    }
                    bnn_op.setBackground(getDrawable(R.drawable.defin));
                    bnn_op =bnn;
                    operator = bnn_op.getText().toString();
                    bnn_op.setBackground(getDrawable(R.drawable.bu));
                    if(!havefrist_num) {
                        frist_num =Double.valueOf(frist_str).doubleValue();
                        havefrist_num =true;
                    }
                    frist_str="0";
                }
            });
        }

        bn = (Button) findViewById(R.id.buttonNumEqual);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op_equal =false;
                if(pressed_eque)
                {
                    frist_str = next_num.toString();
                }
                pressed_eque = true;
                equal();
                frist_str="0";
            }
        });
    }

    void equal()
    {
        String enser;
        Double e = null;
        next_num = Double.valueOf(frist_str).doubleValue();
        switch (operator)
        {
            case ("÷"):
                e = frist_num/next_num;
                break;
            case ("×"):
                e = frist_num*next_num;
                break;
            case ("—"):
                e = frist_num-next_num;
                break;
            case ("+"):
                e = frist_num+next_num;
                break;
        }
        frist_num = e;
        enser = e.toString();
        if(enser.substring(enser.length()-2,enser.length()).equals(".0"))
            enser = enser.substring(0,enser.length()-2);

        autoSettext_size();
        num_tv.setText(enser);
    }

    void autoSettext_size()
    {
        if(isPortrait()) {//竖屏
            if (frist_str.length() > 7) {
                float ts = (float) (700 / (float) frist_str.length());
                num_tv.setTextSize(ts);
            }
            else
                num_tv.setTextSize(100);
        }
        else {
            if(frist_str.length() > 20){
                float ts = (float) (1800 / (float) frist_str.length());
            }
            else
                num_tv.setTextSize(60);

        }

    }
    void ini()
    {
        frist_str="0";
        frist_num=0.0;
        operator = "+";
        next_num =0.0;
        havepoint =false;
        bnn_op=(Button) findViewById(R.id.buttonNum除);
        op_equal=false;
        havefrist_num = false;
        pressed_eque = false;
    }


}


