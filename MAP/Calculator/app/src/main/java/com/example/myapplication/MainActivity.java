package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView[] cardViews;
    TextView input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardViews = new CardView[16];
        setUp();
        for(int i = 0; i <= 15; i++){
            cardViews[i].setOnClickListener(this);
        }
    }

    public void setUp(){
        input = findViewById(R.id.input);
        cardViews[0] = findViewById(R.id.c0);
        cardViews[1] = findViewById(R.id.c1);
        cardViews[2] = findViewById(R.id.c2);
        cardViews[3] = findViewById(R.id.c3);
        cardViews[4] = findViewById(R.id.c4);
        cardViews[5] = findViewById(R.id.c5);
        cardViews[6] = findViewById(R.id.c6);
        cardViews[7] = findViewById(R.id.c7);
        cardViews[8] = findViewById(R.id.c8);
        cardViews[9] = findViewById(R.id.c9);
        cardViews[10] = findViewById(R.id.cplus);
        cardViews[11] = findViewById(R.id.cminus);
        cardViews[12] = findViewById(R.id.cdiv);
        cardViews[13] = findViewById(R.id.ceq);
        cardViews[14] = findViewById(R.id.cclear);
        cardViews[15] = findViewById(R.id.cmul);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        boolean found = true;
        StringBuilder result = new StringBuilder(input.getText());
        for(int i = 0; i <= 9; i++){
            if(cardViews[i].getId() == id){
                result.append(i);
                found = false;
                break;
            }
        }
        if(found){
            switch (id){
                case R.id.cplus:
                    result.append('+');
                    break;

                case R.id.cminus:
                    result.append('-');
                    break;

                case R.id.cdiv:
                    result.append('/');
                    break;

                case R.id.ceq:
                    String expression = result.toString();
                    result = new StringBuilder("");
                    result.append(evaluate(expression).toString());
                    break;

                case R.id.cclear:
                    result = new StringBuilder("");
                    break;

                default:
                    result.append('*');
            }
        }
        input.setText(result.toString());
    }

    Object evaluate(String expression){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Log.d("Shreyash..", expression);
        Object result;
        try{
            result = engine.eval(expression);
        } catch (Exception e){
            Toast.makeText(this, "Invalid..", Toast.LENGTH_SHORT).show();
            input.setText("");
            return 0;
        }
//        Log.d("Shreyash..", "" + (Integer) result);
        return result;
    }

}