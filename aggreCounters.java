package com.example.jsandoval.countersofmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jsandoval on 07/11/16.
 */

public class aggreCounters extends ActionBarActivity {
    EditText             MachineId, TotalIn, TotalOut, HandPaid, Bill, Jackpot, TicketIn,TicketOut;
    Button               SAVE;
    int                  machineId;
    Long                 totalIn,totalOut,handPaid,bill,jackpot,ticketIn,ticketOut;
    ConnectionDB         db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_counters);

        SAVE        = (Button)      findViewById(R.id.btn_save);
        MachineId   = (EditText)    findViewById(R.id.actv_Id);
        TotalIn     = (EditText)    findViewById(R.id.actv_TotalIn);
        TotalOut    = (EditText)    findViewById(R.id.actv_TotalOut);
        HandPaid    = (EditText)    findViewById(R.id.actv_HP);
        Bill        = (EditText)    findViewById(R.id.actv_Bill);
        Jackpot     = (EditText)    findViewById(R.id.actv_Jackpot);
        TicketIn    = (EditText)    findViewById(R.id.actv_TicketIn);
        TicketOut   = (EditText)    findViewById(R.id.actv_TicketOut);

        SAVE.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (MachineId.getText().toString().isEmpty()){
                    do {
                       MachineId.setError("ID Can not be null");
                        return;
                    }while (MachineId.getText().toString().isEmpty());
                }
                if (Integer.parseInt(MachineId.getText().toString()) == 0){
                    do {
                        MachineId.setError("ID Can not be Zero");
                        return;
                    }while (Integer.parseInt(MachineId.getText().toString()) == 0);
                }
                if (TotalIn.getText().toString().isEmpty()){
                    do {
                        TotalIn.setError("Can not be null");
                        return;
                    }while (TotalIn.getText().toString().isEmpty());
                }
                if (TotalOut.getText().toString().isEmpty()){
                    do {
                        TotalOut.setError("Can not be null");
                        return;
                    }while (TotalOut.getText().toString().isEmpty());
                }
                if (HandPaid.getText().toString().isEmpty()){
                    do {
                        HandPaid.setError("Can not be null");
                        return;
                    }while (HandPaid.getText().toString().isEmpty());
                }
                if (Bill.getText().toString().isEmpty()){
                    do {
                        Bill.setError("Can not be null");
                        return;
                    }while (Bill.getText().toString().isEmpty());
                }
                if (Jackpot.getText().toString().isEmpty()){
                    do {
                        Jackpot.setError("Can not be null");
                        return;
                    }while (Jackpot.getText().toString().isEmpty());
                }
                if (TicketIn.getText().toString().isEmpty()){
                    do {
                        TicketIn.setError("Can not be null");
                        return;
                    }while (TicketIn.getText().toString().isEmpty());
                }
                if (TicketOut.getText().toString().isEmpty()){
                    do {
                        TicketOut.setError("Can not be null");
                        return;
                    }while (TicketOut.getText().toString().isEmpty());
                }
                AddCounters();
            }
        });
        PositionCursor();
    }
    private void AddCounters(){
            machineId   = Integer.parseInt(MachineId.getText().toString());
            totalIn     = Long.parseLong(TotalIn.getText().toString());
            totalOut    = Long.parseLong(TotalOut.getText().toString());
            handPaid    = Long.parseLong(HandPaid.getText().toString());
            bill        = Long.parseLong(Bill.getText().toString());
            jackpot     = Long.parseLong(Jackpot.getText().toString());
            ticketIn    = Long.parseLong(TicketIn.getText().toString());
            ticketOut   = Long.parseLong(TicketOut.getText().toString());
            db = new ConnectionDB(this);
            db.addCounters(machineId, totalIn, totalOut, handPaid, bill, jackpot, ticketIn, ticketOut);
            Toast.makeText(getBaseContext(), "Successfully added machine counters!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(aggreCounters.this, watchCounters.class);
            startActivity(intent);
    }
    private void PositionCursor(){
        MachineId.setSelection(MachineId.getText().length());
        TotalIn.setSelection(TotalIn.getText().length());
        TotalOut.setSelection(TotalOut.getText().length());
        HandPaid.setSelection(HandPaid.getText().length());
        Bill.setSelection(Bill.getText().length());
        Jackpot.setSelection(Jackpot.getText().length());
        TicketIn.setSelection(TicketIn.getText().length());
        TicketOut.setSelection(TicketOut.getText().length());
    }
}
