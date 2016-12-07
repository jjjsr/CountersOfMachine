package com.example.jsandoval.countersofmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class OptionsActivity extends AppCompatActivity {
    ImageButton ADD,LOOK,DELETE,EXPORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ADD    = (ImageButton) findViewById(R.id.ibtn_add);
        LOOK = (ImageButton) findViewById(R.id.ibtn_view);
        DELETE = (ImageButton) findViewById(R.id.ibtn_delete);
        EXPORT = (ImageButton) findViewById(R.id.ibtn_export);

        ADD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(OptionsActivity.this, aggreCounters.class);
                startActivity(intent);
            }
        });
        LOOK.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(OptionsActivity.this, watchCounters.class);
                startActivity(intent);
            }
        });
        DELETE.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(OptionsActivity.this, eraserCounters.class);
                startActivity(intent);
            }
        });
        EXPORT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OptionsActivity.this, exportCounters.class);
                startActivity(intent);
            }

        });
    }
}