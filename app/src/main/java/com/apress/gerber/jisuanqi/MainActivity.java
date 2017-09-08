package com.apress.gerber.jisuanqi;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.apress.gerber.jisuanqi.R.drawable.bu;

public class MainActivity extends AppCompatActivity {

    String frist_str ;
    double frist_num ;
    String operator ;
    double next_num ;
    boolean havepoint ;
    Button bnn_op;
    Button bn;
    TextView num_tv;
    int button[] ={R.id.buttonNum0,R.id.buttonNum1,R.id.buttonNum2,R.id.buttonNum3,R.id.buttonNum4,R.id.buttonNum5,R.id.buttonNum6,
                    R.id.buttonNum7,R.id.buttonNum8,R.id.buttonNum9,R.id.buttonNumPoint};
    int button_op[] ={R.id.buttonNum除,R.id.buttonNumx,R.id.buttonNum减,R.id.buttonNumplus};


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

        for(int i=0;i<4;i++)
        {
            bn =(Button) findViewById(button_op[i]);
            bn.setOnClickListener(new View.OnClickListener() {

                Button bnn = bn;
                public void onClick(View view) {
                    bnn_op.setBackground(getDrawable(R.drawable.defin));
                    bnn_op =bnn;
                    operator = bnn_op.getText().toString();
                    bnn_op.setBackground(getDrawable(R.drawable.bu));
                    frist_num =Double.valueOf(frist_str).doubleValue();
                    frist_str="0";
                }
            });
        }

        bn = (Button) findViewById(R.id.buttonNumEqual);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            equal();
            }
        });
    }

    void equal()
    {
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
        frist_str = e.toString();
        autoSettext_size();
        num_tv.setText(frist_str);
    }

    void autoSettext_size()
    {
        if(frist_str.length()>8)
        {float ts = (float) (720.0/ (float)frist_str.length());
            num_tv.setTextSize(ts);
        }
        else
            num_tv.setTextSize(90);

    }
    void ini()
    {
        frist_str="0";
        frist_num=0;
        operator = "+";
        next_num =0;
        havepoint =false;
        bnn_op=(Button) findViewById(R.id.buttonNum除);
    }


}


