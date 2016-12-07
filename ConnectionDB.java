package com.example.jsandoval.countersofmachine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jsandoval on 07/11/16.
 */

public class ConnectionDB extends SQLiteOpenHelper {
    private static final String DATABASE  = "CountersDB";
    private static final String Tmachine  = "Machine";
    private static final String MachineId = "ComputerId", TotalIn="TotalCoinIn", TotalOut="TotalCoinOut",
            HandPaid="TotalHandPaid", Bill="TotalBillIn", Jackpot="TotalJackpot", TicketIn="TotalTicketIn", TicketOut="TotalTicketOut";
    public ConnectionDB(Context context){
        super(context, DATABASE, null, 1);
    }
    public void onCreate(SQLiteDatabase db){
        try {
            db.execSQL("CREATE TABLE "+ Tmachine +" ("+ MachineId +" INTEGER PRIMARY KEY NOT NULL,"
                    + TotalIn +" BIGINT,"+ TotalOut +" BIGINT,"+ HandPaid +" BIGINT,"+ Bill +" BIGINT,"+ Jackpot +" BIGINT,"+ TicketIn +" BIGINT,"+ TicketOut +" BIGINT)");
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Tmachine);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    /*public void close(){
        this.getWritableDatabase().setTransactionSuccessful();
        this.getWritableDatabase().endTransaction();
        this.getWritableDatabase().close();
    }*/
    public void addCounters(int machineId, Long totalIn, Long totalOut, Long handPaid, Long bill, Long jackpot, Long ticketIn, Long ticketOut){
        ContentValues values = new ContentValues();
        values.put(MachineId, machineId);
        values.put(TotalIn,   totalIn);
        values.put(TotalOut,  totalOut);
        values.put(HandPaid,  handPaid);
        values.put(Bill,      bill);
        values.put(Jackpot,   jackpot);
        values.put(TicketIn,  ticketIn);
        values.put(TicketOut, ticketOut);
        this.getWritableDatabase().insert(Tmachine,null,values);
    }
    public Cursor getCounters(){
        String columnas[]={MachineId,TotalIn,TotalOut,HandPaid,Bill,Jackpot,TicketIn,TicketOut};
        Cursor c = this.getReadableDatabase().query(Tmachine,columnas,null,null,null,null,null);
        return c;
    }
    public void updateCounters(String machineId, String totalIn, String totalOut, String handPaid, String bill, String jackpot, String ticketIn, String ticketOut){
        ContentValues values = new ContentValues();
        values.put(TotalIn,   totalIn);
        values.put(TotalOut,  totalOut);
        values.put(HandPaid,  handPaid);
        values.put(Bill,      bill);
        values.put(Jackpot,   jackpot);
        values.put(TicketIn,  ticketIn);
        values.put(TicketOut, ticketOut);
        this.getWritableDatabase().update(Tmachine,values,"ComputerId='"+machineId+"'",null);
        this.close();
    }
    public void deleteCounters(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Tmachine,null,null);
        db.close();
    }
}
