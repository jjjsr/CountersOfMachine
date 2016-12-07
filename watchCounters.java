package com.example.jsandoval.countersofmachine;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsandoval on 07/11/16.
 */

public class watchCounters extends ActionBarActivity {
    ConnectionDB db;
    ListView viewCounters;
    List<String> item = null;
    Button MainMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_counters);
        viewCounters = (ListView)findViewById(R.id.lv_Counters);
        DisplayCounters();
        MainMenu = (Button) findViewById(R.id.btn_menu);
        MainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(watchCounters.this, OptionsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void DisplayCounters(){
        db = new ConnectionDB(this);
        Cursor c = db.getCounters();
        item = new ArrayList<String>();
        int machineId;
        Long totalIn, totalOut, handPaid, bill, jackpot, ticketIn, ticketOut;
        if (c.moveToFirst()) {
            do {
                machineId   = c.getInt(0);
                totalIn     = c.getLong(1);
                totalOut    = c.getLong(2);
                handPaid    = c.getLong(3);
                bill        = c.getLong(4);
                jackpot     = c.getLong(5);
                ticketIn    = c.getLong(6);
                ticketOut   = c.getLong(7);

                item.add(machineId+" - "+totalIn+" - "+totalOut+" - "+handPaid+" - "+bill+" - "+jackpot+" - "+ticketIn+" - "+ticketOut);
            } while (c.moveToNext());
        }
        ArrayAdapter<String> Adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        viewCounters.setAdapter(Adaptador);
    }
}
