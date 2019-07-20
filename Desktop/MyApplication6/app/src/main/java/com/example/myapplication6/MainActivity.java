package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBManager dbManager = new DBManager(getApplicationContext(), "Gift.db", null, 1);

 //      DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.et_foodname);
        final EditText etPrice = (EditText) findViewById(R.id.et_price);

        // 쿼리 결과 입력
        final TextView tvResult = (TextView) findViewById(R.id.tv_result);

        // Insert
 /*       Button btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                dbManager.insert("insert into FOOD_LIST values(null, '" + name + "', " + price + ");");

                tvResult.setText( dbManager.PrintData() );
            }
        });
*/
        //insert
        /*dbManager.insert("insert into GIFT_LIST values(null, 'org_img1', '해피머니', 5000)");
        dbManager.insert("insert into GIFT_LIST values(null, 'org_img2', '해피머니', 10000)");
        dbManager.insert("insert into GIFT_LIST values(null, 'org_img3', '문화컬쳐', 5000)");
        dbManager.insert("insert into GIFT_LIST values(null, 'org_img4', '문화컬쳐', 10000)");*/

     //   dbManager.insert("insert into GIFT_LIST values(null, 'org_img1', '해피머니', 5000), (null, 'org_img2', '해피머니', 10000), (null, 'org_img3', '문화컬쳐', 5000),(null, 'org_img4', '문화컬쳐', 10000)");
        dbManager.insert("insert into GIFT_LIST values(null, 'org_img1', '해피머니', 7000)");


        // Delete
        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // delete from 테이블명 where 조건;
                String name = etName.getText().toString();
                dbManager.delete("delete from GIFT_LIST where name = '" + name + "';");

                tvResult.setText( dbManager.PrintData() );
            }
        });


        // Select
        Button btnSelect = (Button) findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tvResult.setText( dbManager.PrintData() );
            }
        });
    }
}