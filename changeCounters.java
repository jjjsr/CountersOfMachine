package com.example.jsandoval.countersofmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

/**
 * Created by jsandoval on 07/11/16.
 */

public class changeCounters extends ActionBarActivity {
    AutoCompleteTextView MachineId, TotalIn, TotalOut, HandPaid, Bill, Jackpot, TicketIn,TicketOut;
    Button               Update;
    String               machineId,totalIn,totalOut,handPaid,bill,jackpot,ticketIn,ticketOut;
    ConnectionDB         db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_counters);

        Update      = (Button)      findViewById(R.id.btn_save);
        MachineId   = (AutoCompleteTextView)    findViewById(R.id.actv_Id);
        TotalIn     = (AutoCompleteTextView)    findViewById(R.id.actv_TotalIn);
        TotalOut    = (AutoCompleteTextView)    findViewById(R.id.actv_TotalOut);
        HandPaid    = (AutoCompleteTextView)    findViewById(R.id.actv_HP);
        Bill        = (AutoCompleteTextView)    findViewById(R.id.actv_Bill);
        Jackpot     = (AutoCompleteTextView)    findViewById(R.id.actv_Jackpot);
        TicketIn    = (AutoCompleteTextView)    findViewById(R.id.actv_TicketIn);
        TicketOut   = (AutoCompleteTextView)    findViewById(R.id.actv_TicketOut);

        Update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                UpdateCounters();
            }
        });
    }
    private void UpdateCounters(){
        machineId   = MachineId.getText().toString();
        totalIn     = TotalIn.getText().toString();
        totalOut    = TotalOut.getText().toString();
        handPaid    = HandPaid.getText().toString();
        bill        = Bill.getText().toString();
        jackpot     = Jackpot.getText().toString();
        ticketIn    = TicketIn.getText().toString();
        ticketOut   = TicketOut.getText().toString();
        db          = new ConnectionDB(this);
        db.updateCounters(machineId,totalIn,totalOut,handPaid,bill,jackpot,ticketIn,ticketOut);
        Intent intent = new Intent(changeCounters.this, OptionsActivity.class);
        startActivity(intent);
    }
}