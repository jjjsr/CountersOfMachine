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
        ExtractCounters_to_TxT();
        finish();
    }

    public void ExtractCounters_to_TxT(){
        //Storage Folder
        File root = new File(Environment.getExternalStorageDirectory(), "Casino_Counters");
        File audit_root = new File(Environment.getExternalStorageDirectory(), "Audit_Counters");
        //Date format to use in the name of the file
        Date_Time = DateFormat.format("dd"+"MM"+"yy", (System.currentTimeMillis()-(24*60*60*1000))).toString();
        //Name and extension of the export file
        File filepath = new File(root, "CO" + Date_Time + ".txt");
        File audit_filepath = new File(audit_root, "CO" + Date_Time + ".csv");
        try {
            //Check if the folder exists
            if (!root.exists() && !audit_root.exists()) {
                root.mkdirs();
                audit_root.mkdirs();
            }
            ExplorerDB();
            //Create the export file
            FileOutputStream writer = new FileOutputStream(filepath);
            OutputStreamWriter os = new OutputStreamWriter(writer);
            FileOutputStream audit_writer = new FileOutputStream(audit_filepath);
            OutputStreamWriter audit_os = new OutputStreamWriter(audit_writer);
            //Insert data to the file
            for (int k = 0; k < item.size(); k++){
                os.write(item.get(k));}
            os.close();
            audit_os.write("CODIGO,ENTRADA,SALIDA,DROP,JACKPOT,CANCEL,BILLETERO,TI,TO,BONUS,HOPPER\n");
            for (int j = 0; j < item.size(); j++){
                audit_os.write(item.get(j));}
            audit_os.close();

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
        Long totalIn, totalOut, drop, jackpot, cancelCredit, bill, ticketIn, ticketOut, bonus, hopper;
        if (curTxT.moveToFirst()) {
            do {
                machineId       = curTxT.getInt(0);
                totalIn         = curTxT.getLong(1);
                totalOut        = curTxT.getLong(2);
                drop            = curTxT.getLong(3);
                jackpot         = curTxT.getLong(4);
                cancelCredit    = curTxT.getLong(5);
                bill            = curTxT.getLong(6);
                ticketIn        = curTxT.getLong(7);
                ticketOut       = curTxT.getLong(8);
                bonus           = curTxT.getLong(9);
                hopper          = curTxT.getLong(10);

                item.add(machineId+","+totalIn+","+totalOut+","+drop+","+jackpot+","+cancelCredit+","+bill+","+ticketIn+","+ticketOut+","+bonus+","+hopper+"\n");
            } while (curTxT.moveToNext());
        }
    }
}
