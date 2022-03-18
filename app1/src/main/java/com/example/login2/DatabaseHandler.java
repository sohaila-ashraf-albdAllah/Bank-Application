package com.example.login2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

import Model.CreditCard;
import Model.Transaction;
import Model.User;
import Model.Utils;
import Model.Request;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String name="bank_database";
    private SQLiteDatabase bankdatabase;

    public DatabaseHandler(Context context){
        super(context,name,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(accnumber number primary key,username text unique not null,password text not null,name text not null,adress text not null,Email text unique ,phone number unique not null,age integer not null)");
        db.execSQL("create table creditcard(cardnumber number primary key,balance intger not null,accnumber number not null )");
        db.execSQL("create table transactions(id number primary key,toacc not null,fromacc not null,balance integer not null,date date not null )");
        db.execSQL("create table " + Utils.TABLE_NAME + " (" + Utils.KEY_JOB + " text primary key," + Utils.KEY_NAME + " text not null, " + Utils.KEY_SALARY + " integer not null, " + Utils.KEY_MONTHS + " integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void NewUser(User user)  {

        ContentValues table = new ContentValues();
        String s=getnewaccnumber();
        table.put("name", user.getName());
        table.put("UserName",user.getUsername());
        table.put("Email",user.getEmail());
        table.put("Password",user.getPassword());
        table.put("adress",user.getAdress());
        table.put("Phone",user.getPhone());
        table.put("age",user.getAge());
        table.put("accnumber",s);
        ContentValues credit=new ContentValues();
        credit.put("cardnumber",getCardNumber());
        credit.put("balance",1000);
        credit.put("accnumber",s);
        bankdatabase = getWritableDatabase();
        bankdatabase.insert("users",null,table);
        bankdatabase.insert("creditcard",null,credit);
         credit=new ContentValues();
        credit.put("cardnumber",getCardNumber());
        credit.put("balance",1000);
        credit.put("accnumber",s);
        bankdatabase.insert("creditcard",null,credit);
        credit=new ContentValues();
        credit.put("cardnumber",getCardNumber());
        credit.put("balance",1000);
        credit.put("accnumber",s);
        bankdatabase.insert("creditcard",null,credit);
        bankdatabase.close();
    }
    public Cursor getUserCreditCards(String accnumber)
    {
        bankdatabase=this.getReadableDatabase();
        Cursor cursor=bankdatabase.rawQuery("select * from creditcard where accnumber like ?",
                new String[]{accnumber});
        cursor.moveToFirst();
        bankdatabase.close();
        return cursor;
    }
    public void newRequest ( Request request){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( Utils.KEY_JOB, request.getJob());
        contentValues.put( Utils.KEY_NAME, request.getName());
        contentValues.put( Utils.KEY_SALARY, request.getSalary());
        contentValues.put(Utils.KEY_MONTHS , request.getNumofmonths());
        database.insert(Utils.TABLE_NAME,null , contentValues);
        database.close();
    }
    public void deleteuserCreditCards(String accnumber)
    {
        bankdatabase=this.getWritableDatabase();
       // bankdatabase.rawQuery("delete from creditcard where accnumber like ?",new String[]{accnumber});
        bankdatabase.delete("creditcard","accnumber"+"="+accnumber,null);
        bankdatabase.close();
    }
    public void deleteCreditCard(String number)
    {
        bankdatabase=this.getWritableDatabase();
        /*bankdatabase.rawQuery("delete from creditcard where cardnumber like ?",new String[]{number});
        bankdatabase.rawQuery("commit",new String[]{});*/
        bankdatabase.delete("creditcard","cardnumber"+"="+number,null);
        bankdatabase.close();
    }
    public void deleteuser(String accnumber)
    {
        bankdatabase=this.getWritableDatabase();
        //bankdatabase.rawQuery("delete from users where accnumber like ?",new String[]{accnumber});
        bankdatabase.delete("users","accnumber"+"="+accnumber,null);
        bankdatabase.close();
    }
    public boolean checkLogin(String userName,String password)
    {
        bankdatabase=getReadableDatabase();
        Cursor cursor=bankdatabase.rawQuery("select username,password" +
                " from users where username like ? and password like ?"
                ,new String[]{userName,password});
        if(cursor.getCount()!=0)
        {
            bankdatabase.close();
            return true;
        }
        bankdatabase.close();
        return false;
    }
    public String getnewaccnumber()
    {
        bankdatabase=getReadableDatabase();
        Cursor cursor=bankdatabase.rawQuery("select accnumber from users",new String[]{});
        if(cursor.getCount()==0)
            return "1";
        else
        {
            cursor.moveToLast();
            int s=Integer.parseInt(cursor.getString(0));
            s++;
            return String.valueOf(s);
        }
    }
    public String getCardNumber()
    {
        bankdatabase=getReadableDatabase();
        Cursor cursor=bankdatabase.rawQuery("select cardnumber from creditcard",new String[]{});
        if(cursor.getCount()==0)
            return "1";
        else
        {
            cursor.moveToLast();
            int s=Integer.parseInt(cursor.getString(0));
            s++;
            return String.valueOf(s);
        }
    }
    public boolean checkregister(String username,String email,String phone)
    {
        bankdatabase=getReadableDatabase();
        Cursor cursor=bankdatabase.rawQuery("select username,Email,phone" +
                        " from users where username like ? or Email like ? or phone like ?"
                ,new String[]{username,email,phone});
        if(cursor.getCount()==0)
        {
            bankdatabase.close();
            return true;
        }
        bankdatabase.close();
        return false;
    }
    public void addtransaction(Transaction transaction)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("toacc",transaction.getToacc());
        contentValues.put("fromacc",transaction.getFromacc());
        contentValues.put("balance",transaction.getBalance());
        contentValues.put("date",transaction.getDate());
        database.insert("transactions" , null,contentValues);
        database.close();
    }
    public CreditCard getcreditcard(String cardnumber)
    {
        //test();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor=database.query("creditcard"
                ,new String[]{"cardnumber","balance","accnumber"}
                ,"cardnumber=?",
                new String[]{String.valueOf(cardnumber)},
                null,null,null,null);
        if(cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            CreditCard creditCard=new CreditCard(cursor.getString(0)
                    ,Double.parseDouble(cursor.getString(1)),
                    cursor.getString(2));
            database.close();
            return creditCard;
        }
        database.close();
        return null;
    }
    public Cursor transactions(String cardno){

        bankdatabase = this.getReadableDatabase();
        Cursor match= bankdatabase.rawQuery("select * from transactions  where fromacc like? or toacc like? order by date DESC ",new String[]{cardno,cardno});
        if (match.getCount()!=0)
            match.moveToFirst();
        return match;
    }
    public Cursor getbalance(String cardno){

        bankdatabase = this.getReadableDatabase();
        Cursor match= bankdatabase.rawQuery("select * from creditcard  where cardnumber like?  ",new String[]{cardno});
        if (match.getCount()!=0)
            match.moveToFirst();
        return match;
    }
    public User getUser(String userName)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from users where username like ?"
        ,new String[]{userName});
        if(cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            User user=new User(cursor.getString(1),cursor.getString(2)
                    ,cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),cursor.getString(7));
            user.setAccnumber(cursor.getString(0));
            database.close();
            return user;
        }
        database.close();
        return null;
    }
    public void changeCreditCardBalance(String cardNumber,double newbalance)
    {
        bankdatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("balance", newbalance);
        bankdatabase.update("creditcard",contentValues,"cardnumber"+"=?",new String[]{cardNumber});
        bankdatabase.close();
    }
    public void updatepassword(String accnumber,String newpassword)
    {
        bankdatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newpassword);
        bankdatabase.update("users",contentValues,"accnumber"+"=?",new String[]{accnumber});
        bankdatabase.close();
    }
    public void updateUserData(Vector<String> cols,Vector<String> values, String username)
    {
        bankdatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(int i=0;i< cols.size();i++)
        {
            contentValues.put(cols.get(i),values.get(i));
        }
        bankdatabase.update("users",contentValues,"username"+"=?",new String[]{username});
        bankdatabase.close();
    }
    public boolean checkCreditCard(String accnumber,String cardnumber)
    {
        bankdatabase=this.getReadableDatabase();
        Cursor cursor = bankdatabase.rawQuery("select * from creditcard where accnumber like ? and cardnumber like ?"
                ,new String[]{accnumber,cardnumber});
        if(cursor.getCount()!=0)
        {
            bankdatabase.close();
            return true;
        }
        bankdatabase.close();
        return false;
    }
    public void test(){
        bankdatabase = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        /*contentValues.put("name" , "moh");
        contentValues.put("Email" , "samdkas");
        contentValues.put("adress" , "nsajkd");
        contentValues.put("age" , "21");
        contentValues.put("phone" , "010222223131");
        contentValues.put("accnumber","52356512523");
        contentValues.put("username","admin");
        contentValues.put("password","admin");
        bankdatabase.insert("users", null,contentValues);*/
        contentValues = new ContentValues();
       /* contentValues.put("cardnumber", "23565565");
        contentValues.put("balance", "2500");
        contentValues.put("accnumber" , "52356512523");*/
        contentValues.put("cardnumber", "23566665");
        contentValues.put("balance", "2500");
        contentValues.put("accnumber" , "52356512523");
        bankdatabase.insert("creditcard", null,contentValues);
       /* contentValues = new ContentValues();
        contentValues.put("id","20");
        contentValues.put("toacc","52356512523");
        contentValues.put("fromacc","23565565");
        contentValues.put("balance", "2500");
        contentValues.put("date", "20/4/2020");
        bankdatabase.insert("transactions", null,contentValues);*/
        bankdatabase.close();
    }
    public boolean checkUser(String accnumber,String username)
    {
        bankdatabase=this.getReadableDatabase();
        Cursor cursor = bankdatabase.rawQuery("select * from users where username like ? and accnumber like ?"
                ,new String[]{username,accnumber});
        if(cursor.getCount()!=0)
        {
            bankdatabase.close();
            return true;
        }
        bankdatabase.close();
        return false;
    }
}
