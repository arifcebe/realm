package com.arifcebe.realmio.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.arifcebe.realmio.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by arifcebe on 28/12/15.
 */
public class MainDetailActivity extends AppCompatActivity {

    @Bind(R.id.edAge)
    EditText age;
    @Bind(R.id.edName)
    EditText name;
    @Bind(R.id.btnEdit)
    Button btnEdit;
    @Bind(R.id.btndelete)
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_detail_activity);
        ButterKnife.bind(this);
    }
}
