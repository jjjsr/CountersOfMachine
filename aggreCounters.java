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
    EditText             MachineId, TotalIn, TotalOut, Drop, Jackpot, CancelCredit, Bill, TicketIn,TicketOut, Bonus, Hopper;
    Button               SAVE, MainMenu;
    int                  machineId;
    Long                 totalIn, totalOut, drop, jackpot, cancelCredit, bill, ticketIn,ticketOut, bonus, hopper;
    ConnectionDB         db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_counters);
        InitialElementInterface();
        PositionCursor();

        MainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(aggreCounters.this, OptionsActivity.class);
                startActivity(intent);
            }
        });

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
                if (Drop.getText().toString().isEmpty()){
                    do {
                        Drop.setError("Can not be null");
                        return;
                    }while (Drop.getText().toString().isEmpty());
                }
                if (Jackpot.getText().toString().isEmpty()){
                    do {
                        Jackpot.setError("Can not be null");
                        return;
                    }while (Jackpot.getText().toString().isEmpty());
                }
                if (CancelCredit.getText().toString().isEmpty()){
                    do {
                        CancelCredit.setError("Can not be null");
                        return;
                    }while (CancelCredit.getText().toString().isEmpty());
                }
                if (Bill.getText().toString().isEmpty()){
                    do {
                        Bill.setError("Can not be null");
                        return;
                    }while (Bill.getText().toString().isEmpty());
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
                if (Bonus.getText().toString().isEmpty()){
                    do {
                        Bonus.setError("Can not be null");
                        return;
                    }while (Bonus.getText().toString().isEmpty());
                }
                if (Hopper.getText().toString().isEmpty()){
                    do {
                        Hopper.setError("Can not be null");
                        return;
                    }while (Hopper.getText().toString().isEmpty());
                }
                AddCounters();
                BlankCounters();
                PositionCursor();
            }
        });
    }
    private void AddCounters(){
            machineId       = Integer.parseInt(MachineId.getText().toString());
            totalIn         = Long.parseLong(TotalIn.getText().toString());
            totalOut        = Long.parseLong(TotalOut.getText().toString());
            drop            = Long.parseLong(Drop.getText().toString());
            jackpot         = Long.parseLong(Jackpot.getText().toString());
            cancelCredit    = Long.parseLong(CancelCredit.getText().toString());
            bill            = Long.parseLong(Bill.getText().toString());
            ticketIn        = Long.parseLong(TicketIn.getText().toString());
            ticketOut       = Long.parseLong(TicketOut.getText().toString());
            bonus           = Long.parseLong(Bonus.getText().toString());
            hopper          = Long.parseLong(Hopper.getText().toString());
            db = new ConnectionDB(this);
            db.addCounters(machineId, totalIn, totalOut, drop, jackpot, cancelCredit, bill, ticketIn, ticketOut, bonus, hopper);
            Toast.makeText(getBaseContext(), "Successfully added machine counters!", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(aggreCounters.this, watchCounters.class);
            //startActivity(intent);
    }
    private void InitialElementInterface(){
        MainMenu        = (Button)      findViewById(R.id.btn_menu);
        SAVE            = (Button)      findViewById(R.id.btn_save);
        MachineId       = (EditText)    findViewById(R.id.actv_Id);
        TotalIn         = (EditText)    findViewById(R.id.actv_TotalIn);
        TotalOut        = (EditText)    findViewById(R.id.actv_TotalOut);
        Drop            = (EditText)    findViewById(R.id.actv_Drop);
        Jackpot         = (EditText)    findViewById(R.id.actv_Jackpot);
        CancelCredit    = (EditText)    findViewById(R.id.actv_CancelCredit);
        Bill            = (EditText)    findViewById(R.id.actv_Bill);
        TicketIn        = (EditText)    findViewById(R.id.actv_TicketIn);
        TicketOut       = (EditText)    findViewById(R.id.actv_TicketOut);
        Bonus           = (EditText)    findViewById(R.id.actv_Bonus);
        Hopper          = (EditText)    findViewById(R.id.actv_Hopper);
    }
    private void PositionCursor(){
        MachineId.setSelection(MachineId.getText().length());
        TotalIn.setSelection(TotalIn.getText().length());
        TotalOut.setSelection(TotalOut.getText().length());
        Drop.setSelection(Drop.getText().length());
        Jackpot.setSelection(Jackpot.getText().length());
        CancelCredit.setSelection(CancelCredit.getText().length());
        Bill.setSelection(Bill.getText().length());
        TicketIn.setSelection(TicketIn.getText().length());
        TicketOut.setSelection(TicketOut.getText().length());
        Bonus.setSelection(Bonus.getText().length());
        Hopper.setSelection(Hopper.getText().length());
    }
    private void BlankCounters(){
        MachineId.setText("");
        TotalIn.setText("0");
        TotalOut.setText("0");
        Drop.setText("0");
        Jackpot.setText("0");
        CancelCredit.setText("0");
        Bill.setText("0");
        TicketIn.setText("0");
        TicketOut.setText("0");
        Bonus.setText("0");
        Hopper.setText("0");
    }
}
