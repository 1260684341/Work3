package com.example.administrator.caluate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText weightEditText;
    private CheckBox man,woman;
    private Button caluate;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightEditText= (EditText) this.findViewById(R.id.weight);
        man= (CheckBox) this.findViewById(R.id.man);
        woman= (CheckBox) this.findViewById(R.id.woman);
        caluate= (Button) this.findViewById(R.id.caluate);
        result= (TextView) this.findViewById(R.id.result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        caluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weightEditText.getText().toString().trim().equals("")) {
                    showMessage("请输入体重");
                } else {//已经输入体重
                    if (man.isChecked() || woman.isChecked()) {//已经选择性别
                        Double weight = Double.parseDouble(weightEditText.getText().toString());//获取体重值
                        StringBuffer sb = new StringBuffer();
                        sb.append("----------评估结果----------\n");
                        if (man.isChecked()) {
                            sb.append("男性标准身高：");
                            sb.append( count(weight, "男"));
                        } else {
                            sb.append("女性标准身高：");
                            sb.append( count(weight, "女"));
                        }
                        result.setText(sb.toString());
                    }

                }
            }
        });
    }

    private Double count(Double weight, String sex) {
        if (sex=="男"){
            return (170-(62-weight)/0.6);
        }else{
            return (158-(52-weight)/0.5);
        }
    }

    private void showMessage(String str) {
        AlertDialog dialog=new AlertDialog.Builder(this).create();
        dialog.setTitle("系统消息");
        dialog.setMessage(str);
        dialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
