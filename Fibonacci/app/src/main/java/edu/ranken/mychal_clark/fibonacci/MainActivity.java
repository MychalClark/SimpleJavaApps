package edu.ranken.mychal_clark.fibonacci;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText enterNum;
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Find Views
        enterNum = findViewById(R.id.numberEnter);
        results = findViewById(R.id.fibResults);


    }

    public void calculate(View view) {
        hideKeyboard( this, results);
        int enteredNum = enterNum.getText().toString().trim().isEmpty() ? 0 : Integer.parseInt(enterNum.getText().toString());
        int prevPrevNum = 1;
        int prevNum = 1;
        int num = 2;
        StringBuilder sb = new StringBuilder();


        if (enteredNum == 0) {
            // do nothing
        } else if (enteredNum == 1) {
            sb.append("1");
        } else if (enteredNum == 2) {
            sb.append("1 1");
        } else {
            for(int i = 0; i < enteredNum; i++){
if(i == 0){sb.append("1 1");}
               num = prevPrevNum + prevNum;

                sb.append(" "+num);

                prevPrevNum = prevNum;
                prevNum = num;

            }
        }

        results.setText(sb);
    }

    public void hideKeyboard(Context context, View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);}
}