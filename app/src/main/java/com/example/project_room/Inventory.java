package com.example.project_room;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class Inventory extends Activity {
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.inventory);

        mListView = (ListView)findViewById(R.id.listView);
        dataSetting();
    }
    private void dataSetting(){
        ItemAdapter adapter = new ItemAdapter();
        if (data.haveKey){
            adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.img),"열쇠","문에 쓰는 열쇠는 아닌듯하다.");
        }
        if (data.haveMemo){
            adapter.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.img),"메모","'4829'라고 적혀있다.");
        }
        mListView.setAdapter(adapter);

    }
    public void onExitBtn(View view){
        finish();
    }

}