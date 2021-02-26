package com.example.projekti1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    EditText textInput;
    EditText textInput2;
    String tiedostoinput;
    String tuloste;
    TextView input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        textInput = (EditText) findViewById(R.id.syottokentta);
        input = (TextView) findViewById(R.id.textView);
        textInput2 = (EditText) findViewById(R.id.tiedostonimi);
        System.out.println(context.getFilesDir());
    }

    public void setTiedostoinput(View v) {
        tiedostoinput = textInput2.getText().toString();
    }

    public void setTeksti(View v){
        tuloste = textInput.getText().toString();
    }

    public void readFile(View v){
        try {
            InputStream ins = context.openFileInput(tiedostoinput);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null){
                System.out.println(s);
                input.setText(s);
            }
            ins.close();
        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä.");
        } finally {
            System.out.println("Luettu");
        }

    }

    public void writeFile(View v) {
        tiedostoinput = textInput2.getText().toString();
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(tiedostoinput, Context.MODE_PRIVATE));
            String s = tuloste;
            osw.write(s);
            osw.close();
        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä.");
        } finally {
            System.out.println("Kirjoitettu");
        }
    }


}

