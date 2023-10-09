package edu.ranken.mychal_clark.mortgagecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPrincipal;
    private EditText editTextAnnual;
    private EditText editTextTerm;
    private EditText editTextPMI;
    private EditText editTextProperty;
    private EditText editTextHomeOwners;

    private TextView textEstimate;
    private View results;
    private View enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Views
        editTextPrincipal = findViewById(R.id.editTextPrincipal);
        editTextAnnual = findViewById(R.id.editTextAnnual);
        editTextTerm = findViewById(R.id.editTextTerm);
        editTextPMI = findViewById(R.id.editTextPMI);
        editTextProperty = findViewById(R.id.editTextProperty);
        editTextHomeOwners = findViewById(R.id.editTextHomeOwners);
        textEstimate = findViewById(R.id.textEstimate);
        results = findViewById(R.id.results);
        enter = findViewById(R.id.enter);


    }

    public void calculate(View view) {


//This


        try {
            if (!editTextPrincipal.getText().toString().trim().isEmpty()) {
                Double.parseDouble(editTextPrincipal.getText().toString());
            }
        } catch (Exception e) {
            Snackbar.make(editTextAnnual, R.string.errorPrincipal, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }

        double principal = editTextPrincipal.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(editTextPrincipal.getText().toString());

        if (principal > 100000) {

            Snackbar.make(editTextAnnual, R.string.errorPrincipal, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }

        try {
            if (!editTextAnnual.getText().toString().trim().isEmpty()) {
                Double.parseDouble(editTextAnnual.getText().toString());
            }
        } catch (Exception e) {
            Snackbar.make(editTextAnnual, R.string.errorAnnual, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }
        double annualRate = editTextAnnual.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(editTextAnnual.getText().toString());
        if (annualRate > 50 || annualRate <= 0) {

            Snackbar.make(editTextAnnual, R.string.errorAnnual, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }

        try {
            if (!editTextTerm.getText().toString().trim().isEmpty()) {
                Integer.parseInt(editTextTerm.getText().toString());
            }
        } catch (Exception e) {
            Snackbar.make(editTextAnnual, R.string.errorTerm, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }
        int term = editTextTerm.getText().toString().trim().isEmpty() ? 0 : Integer.parseInt(editTextTerm.getText().toString());
        if (term > 30) {

            Snackbar.make(editTextAnnual, R.string.errorTerm, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }

        try {
            if (!editTextPMI.getText().toString().trim().isEmpty()) {
                Double.parseDouble(editTextPMI.getText().toString());
            }
        } catch (Exception e) {Snackbar.make(editTextAnnual, R.string.errorPMI, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }
        double PMI = editTextPMI.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(editTextPMI.getText().toString());
        if (PMI > 2 || PMI < 0) {

            Snackbar.make(editTextAnnual, R.string.errorPMI, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }

        try {
            if (!editTextProperty.getText().toString().trim().isEmpty()) {
                Double.parseDouble(editTextProperty.getText().toString());
            }
        } catch (Exception e) {Snackbar.make(editTextAnnual, R.string.errorProperty, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }
        double property = editTextProperty.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(editTextProperty.getText().toString());
        if (property > 100000) {

            Snackbar.make(editTextAnnual, R.string.errorProperty, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }

        try {
            if (!editTextHomeOwners.getText().toString().trim().isEmpty()) {
                Double.parseDouble(editTextHomeOwners.getText().toString());
            }
        } catch (Exception e) {Snackbar.make(editTextAnnual, R.string.errorHome, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }
        double homeOwners = editTextHomeOwners.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(editTextHomeOwners.getText().toString());
        if (homeOwners > 100000) {

            Snackbar.make(editTextAnnual, R.string.errorHome, Snackbar.LENGTH_SHORT).show();
            results.setVisibility(View.GONE);
            return;
        }




            double r = (annualRate + PMI) / 100 / 12;
            double n = term * 12;
            double pow = 1;
            for (int i = 0; i < n; ++i) {
                pow *= (r + 1.0);


            double interestPayment = principal * (r / (1 - 1 / pow));
            double monthlyPayment = (interestPayment) + (property / 12) + (homeOwners / 12);


            String formattedPayment = NumberFormat.getCurrencyInstance().format(monthlyPayment);
            textEstimate.setText(formattedPayment);
            results.setVisibility(View.VISIBLE);
        }


    }

    public void close(View view) {

        results.setVisibility(View.GONE);
    }
}