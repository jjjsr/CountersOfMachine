package com.example.jsandoval.countersofmachine;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by jsandoval on 05/12/16.
 */

public class eraserCounters extends ActionBarActivity{
    ConnectionDB db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        DeleteCounters();
        finish();
    }
    public void DeleteCounters(){
        db = new ConnectionDB(this);
        db.deleteCounters();
        Toast.makeText(getBaseContext(), "Deleted Counters!", Toast.LENGTH_SHORT).show();
    }
}
