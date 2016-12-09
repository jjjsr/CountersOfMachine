package com.example.jsandoval.countersofmachine;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jsandoval on 05/12/16.
 */

public class eraserCounters extends ActionBarActivity{
    ConnectionDB db;
    RadioButton     ALL, ONE;
    Button          Del_all, Delete;
    EditText        MachineId;
    TextView        Name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_counters);
        InitialElementInterface();
        AmountDelete();
        Del_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteCounters();
                finish();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PositionCursor();
                DeleteOneCounter();
                BlankCounters();
            }
        });
    }
    private void InitialElementInterface(){
        ALL        = (RadioButton)      findViewById(R.id.rb_AllMachines);
        ONE        = (RadioButton)      findViewById(R.id.rb_OneMachine);
        Name       = (TextView)         findViewById(R.id.tv_MachineId);
        MachineId  = (EditText)         findViewById(R.id.et_machineId);
        Del_all    = (Button)           findViewById(R.id.btn_All);
        Delete     = (Button)           findViewById(R.id.btn_delete);
    }
    private void PositionCursor() {
        MachineId.setSelection(MachineId.getText().length());
    }
    private void BlankCounters(){
        MachineId.setText("");
    }
    private void AmountDelete(){
        if(ALL.isChecked()){
            Del_all.setVisibility(View.VISIBLE);
        }
        if (ONE.isChecked()){
            Name.setVisibility(View.VISIBLE);
            MachineId.setVisibility(View.VISIBLE);
            Delete.setVisibility(View.VISIBLE);
        }
    }
    public void DeleteCounters(){
        db = new ConnectionDB(this);
        db.deleteCounters();
        Toast.makeText(getBaseContext(), "Deleted Counters!", Toast.LENGTH_SHORT).show();
    }
    public void DeleteOneCounter(){
        db = new ConnectionDB(this);
        db.deleteOneCounter(Integer.parseInt(MachineId.getText().toString()));
        Toast.makeText(getBaseContext(), "Deleted Counter of Machine: "+MachineId.getText().toString()+"...!!!", Toast.LENGTH_SHORT).show();
    }
}
