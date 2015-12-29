package com.arifcebe.realmio.view;

import android.animation.IntArrayEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.arifcebe.realmio.Cat;
import com.arifcebe.realmio.MapsActivity;
import com.arifcebe.realmio.R;
import com.arifcebe.realmio.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener{
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.age)
    EditText age;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.listview)
    ListView lv;

    private Cat cat;
    private MainAdapter adapter;
    private List<Cat> catList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cat = new Cat();
        adapter = new MainAdapter(this,catList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                String nm = name.getText().toString();
                int ag = Integer.valueOf(age.getText().toString());

                saveCat(nm, ag);
            }
        });

        // get list cart
        getListCat();
    }

    @OnClick(R.id.maps)
    public void onClickMaps(){
        startActivity(new Intent(this, MapsActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    /**
     * simpan nama dan umur ke dalam realm
     * @param name
     * @param age
     */
    private void saveCat(String name,int age){
        Realm realm = Realm.getInstance(this);
        //Log.d("realm","last no "+realm.where(Cat.class).max("no").toString());
        cat.setNo(14);
        cat.setName(name);
        cat.setAge(age);

        realm.beginTransaction();
        realm.copyToRealm(cat);
        realm.commitTransaction();

        getListCat();
    }

    /**
     * tampilkan seluruh data
     */
    private void getListCat(){

        Realm realm = Realm.getInstance(this);
        RealmQuery<Cat> query = realm.where(Cat.class);

        RealmResults<Cat> result = query.findAll();
        for(Cat c : result){
            Log.i("realm","list realm "+c.getName()+" "+c.getAge());
            catList.add(c);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
