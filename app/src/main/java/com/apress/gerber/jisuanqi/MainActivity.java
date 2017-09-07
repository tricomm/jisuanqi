package com.apress.gerber.jisuanqi;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int operator = 0;
    String frist_str = "0";
    double frist_num = 0;
    double next_num = 0;

    Button bn;
    TextView num_tv;
    int button[] ={R.id.buttonNum0,R.id.buttonNum1,R.id.buttonNum2,R.id.buttonNum3,R.id.buttonNum4,R.id.buttonNum5,R.id.buttonNum6,
                    R.id.buttonNum7,R.id.buttonNum8,R.id.buttonNum9,R.id.buttonNumPoint};
    int button2[] ={R.id.buttonNumAC,R.id.buttonNum加减,R.id.buttonNum百分号};
    boolean havepoint = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num_tv = (TextView)findViewById(R.id.numtext);

        for(int i=0;i<11;i++) { //数字部分
            bn = (Button) findViewById(button[i]);
            bn.setOnClickListener(new View.OnClickListener() {

                Button bnn =bn;
                public void onClick(View view) {

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
                    num_tv.setText(frist_str);

                }
            });
        }





    }



}


