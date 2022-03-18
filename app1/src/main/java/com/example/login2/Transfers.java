package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.CreditCard;
import Model.Transaction;

public class Transfers extends AppCompatActivity {
    EditText from,to,amount;
    Button transfer;
    DatabaseHandler db;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);
        db=new DatabaseHandler(this);
        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        from=findViewById(R.id.from_creditcard_number_id);
        to=findViewById(R.id.to_creditcard_number_id);
        amount=findViewById(R.id.amount_of_money_id);
        transfer=findViewById(R.id.transfer_id);

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromstring=from.getText().toString();
                String tostring=to.getText().toString();
                String amountstring=amount.getText().toString();
                boolean flag =true;
                if(fromstring.isEmpty()||tostring.isEmpty()||amountstring.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter All Required Information",Toast.LENGTH_LONG).show();
                    flag=false;
                }
                if(flag)
                {
                    if(tostring.equals(fromstring))
                    {
                        Toast.makeText(getApplicationContext(),"You Have Entered The Same CreditCard",Toast.LENGTH_LONG).show();
                        flag=false;
                    }
                }
                double amountOfMoney=0.0;
                CreditCard toCreditCard = null,fromCreditCard=null;
                if(flag)
                {
                    fromCreditCard=db.getcreditcard(fromstring);
                    toCreditCard=db.getcreditcard(tostring);

                    if(fromCreditCard==null)
                    {
                        Toast.makeText(getApplicationContext(),"From CreditCard Not Found",Toast.LENGTH_LONG).show();
                        flag=false;
                    }
                    else
                    {
                        if(toCreditCard==null)
                        {
                            Toast.makeText(getApplicationContext(),"To CreditCard Not Found",Toast.LENGTH_LONG).show();
                            flag=false;
                        }
                    }
                }
                if(flag)
                {
                    if(!db.checkCreditCard(db.getUser(username).getAccnumber(),fromstring))
                    {
                        flag=false;
                        Toast.makeText(getApplicationContext(), "The From CreditCard Doesn't Belong To You ", Toast.LENGTH_SHORT).show();
                    }
                }
                if(flag)
                {
                    amountOfMoney =Double.parseDouble(amountstring);
                    if(db.getcreditcard(fromstring).getBalance()<amountOfMoney)
                    {
                        Toast.makeText(getApplicationContext(),"The Amount Of Money Is Not Enough",Toast.LENGTH_LONG).show();
                        flag=false;
                    }
                }
                if(flag)
                {
                    db.changeCreditCardBalance(tostring,toCreditCard.getBalance()+amountOfMoney);
                    db.changeCreditCardBalance(fromstring,fromCreditCard.getBalance()-amountOfMoney);
                    Transaction transaction=new Transaction(tostring,fromstring,amountOfMoney,java.time.LocalDate.now().toString());
                    db.addtransaction(transaction);
                    Toast.makeText(getApplicationContext(),"Transfer Completed Successfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}