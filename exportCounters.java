package com.example.jsandoval.countersofmachine;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsandoval on 05/12/16.
 */

public class exportCounters extends ActionBarActivity{
    ConnectionDB db;
    String Date_Time;
    List<String> item = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ExtractCounters();
        finish();
    }

    public void ExtractCounters(){
        //Storage Folder
        File root = new File(Environment.getExternalStorageDirectory(), "Casino_Counters");
        //Date format to use in the name of the file
        Date_Time = DateFormat.format("dd"+"MM"+"yy", System.currentTimeMillis()).toString();
        //Name and extension of the export file
        File filepath = new File(root, "CO" + Date_Time + ".txt");
        try {
            //Check if the folder exists
            if (!root.exists()) {
                root.mkdirs();
            }
            ExplorerDB();
            //Create the export file
            FileOutputStream writer = new FileOutputStream(filepath);
            OutputStreamWriter os = new OutputStreamWriter(writer);
            //Insert data to the file
            for (int k = 0; k < item.size(); k++){
            os.write(item.get(k));}
            os.close();
            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "File didn't written!", Toast.LENGTH_LONG).show();
        }
    }
    public void ExplorerDB(){
        db = new ConnectionDB(this);
        Cursor curTxT = db.getCounters();
        item = new ArrayList<String>();
        int machineId;
        Long totalIn, totalOut, handPaid, bill, jackpot, ticketIn, ticketOut;
        if (curTxT.moveToFirst()) {
            do {
                machineId   = curTxT.getInt(0);
                totalIn     = curTxT.getLong(1);
                totalOut    = curTxT.getLong(2);
                handPaid    = curTxT.getLong(3);
                bill        = curTxT.getLong(4);
                jackpot     = curTxT.getLong(5);
                ticketIn    = curTxT.getLong(6);
                ticketOut   = curTxT.getLong(7);

                item.add(machineId+","+totalIn+","+totalOut+","+handPaid+","+bill+","+jackpot+","+ticketIn+","+ticketOut+"\n");
            } while (curTxT.moveToNext());
        }
    }
}
