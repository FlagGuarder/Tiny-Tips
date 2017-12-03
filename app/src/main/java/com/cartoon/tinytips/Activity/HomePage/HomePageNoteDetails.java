package com.cartoon.tinytips.Activity.HomePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cartoon.tinytips.Activity.Main.Main;
import com.cartoon.tinytips.R;

public class HomePageNoteDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_note_details);
    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent( this,Main.class);
        intent.putExtra("data_return",1);
        startActivity(intent);
        finish();
    }
}
