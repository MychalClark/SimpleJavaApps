package edu.ranken.mychal_clark.caesarcipher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText messageInput;
    private TextView results;
    private EditText shiftInput;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find views
        messageInput = findViewById(R.id.messageInput);
        shiftInput = findViewById(R.id.shiftInput);
        results = findViewById(R.id.results);

        shiftInput.setOnEditorActionListener((TextView view, int actionId, KeyEvent event)->{
            hideKeyboard(this,shiftInput);
            shiftMessage(shiftInput);
            return false;
        });

    }


    public void shiftMessage(View view) {
        //hideKeyboard(this,results);


        StringBuilder sb = new StringBuilder();
        int shift = shiftInput.getText().toString().trim().isEmpty() ? 0 : Integer.parseInt(shiftInput.getText().toString());

        if(shift < 0 || shift > 26){
            Toast.makeText(MainActivity.this, "Please enter a number 1-26",Toast.LENGTH_LONG).show();
            return;
        }

String message = messageInput.getText().toString();
        for (int i = 0; i < message.length(); i++) {

            char c = Character.toUpperCase(message.charAt(i));
            if (c == ' ') {
                sb.append(c);

            } else if (c + shift > 'Z') {
                c = (char) (c + shift - 26);
                sb.append(c);
            } else {
                c = (char) (c + shift);
                sb.append(c);

            }
            results.setText(sb);
        }

    }
    public void hideKeyboard(Context context, View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}