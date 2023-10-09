package edu.ranken.mychal_clark.checkmyreceipt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText priceInput;
    private EditText quantityInput;
    private EditText discountInput;
    private EditText taxInput;
    private ArrayList<product> products;
    private Button btnAdd;
    private TextView addError;
    private TextView productList;
    private TextView taxError;
    private Button btnCal;
    private TextView tenPercent;
    private TextView twentyPercent;
    private TextView thirtyPercent;
    private TextView subTot;
    private TextView saleTax;
    private TextView grandTotal;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set Views
        priceInput = findViewById(R.id.priceInput);
        quantityInput = findViewById(R.id.quantityInput);
        discountInput = findViewById(R.id.discountInput);
        taxInput = findViewById(R.id.salesTaxInput);
        products = new ArrayList<>();
        btnAdd = findViewById(R.id.btnAdd);
        addError =findViewById(R.id.addError);
        productList = findViewById(R.id.productList);
        taxError = findViewById(R.id.taxError);
        btnCal = findViewById(R.id.btnCal);
        tenPercent = findViewById(R.id.tenPercent);
        twentyPercent = findViewById(R.id.twentyPercent);
        thirtyPercent = findViewById(R.id.thirtyPercent);
        subTot = findViewById(R.id.subTotal);
        grandTotal = findViewById(R.id.grandTotal);
        saleTax = findViewById(R.id.saleTax);

        //Register Listeners
        btnAdd.setOnClickListener((view) -> {
            addItem(priceInput);
        });
        btnCal.setOnClickListener((view) -> {
            calculateTotal(priceInput);
        });
        taxInput.setOnEditorActionListener((TextView view, int actionId, KeyEvent event) -> {

            calculateTotal(priceInput);
            return false;
        });

    }

    public class product {
        double price;
        double quantity;
        double discount;

        public product(double price, double discount, double quantity) {
            this.price = price;
            this.quantity = quantity;
            this.discount = discount;
        }
    }


    void addItem(View view) {
addError.setText("");
        double price;
        double quantity;
        double discount;

        try {
            price = priceInput.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(priceInput.getText().toString());
            discount = discountInput.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(discountInput.getText().toString());
            quantity = quantityInput.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(quantityInput.getText().toString());

            if(price > 1000 || price < 0 || price == 0){addError.setText("Price must be 0.01 - 1000"); return;}

            else if(discount > 100||discount < 0){ addError.setText("Discount must be 0 - 100");
            return;
            }
            else if (quantity>100||quantity<0){addError.setText("Quantity must be 0 - 100"); return;}

            else{

                StringBuilder sb = new StringBuilder();

                products.add(new product(price,discount,quantity));

                for (int i = 0; i < products.size() ; i++) {
                    sb.append("Price: ").append(products.get(i).price).append(" Discount: ").append(products.get(i).discount).append(" Quantity: ").append(products.get(i).quantity).append("\n");

                }
//percentages

                //
            productList.setText(sb);

            priceInput.setText("");
            discountInput.setText("");
            quantityInput.setText("");
            priceInput.requestFocus();
            hideKeyboard(getBaseContext(), priceInput);
            calculateTotal(priceInput);};



        } catch (Exception e) {
            Log.i("hey", "hey");
            addError.setText("Price field invalid.");
        }


    }

    void calculateTotal(View view) {

        taxError.setText("");

        try{
            double tax = taxInput.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(taxInput.getText().toString());
            double totalProductCost = 0;
            double subTotal = 0;
            double totalTax = 0;
            double finalTotal = 0;
            double ten = 0;
            double twenty = 0;
            double thirty = 0;
            if (tax> 20 || tax < 0){taxError.setText("Tax must be 0-20"); return;}
            else {


                for (int i = 0; i < products.size(); i++) {

                    //percentage Calculator of all quantity of item.
                   totalProductCost =  products.get(i).quantity*(products.get(i).price * (products.get(i).discount / 100));

                   //Add sell tax.

                   subTotal += totalProductCost;


                }


                totalTax = (subTotal * (tax/100));

                finalTotal = subTotal + totalTax;


//Percentages


                ten = finalTotal / 10 * 100;
                twenty = finalTotal / 20 * 100;
                thirty = finalTotal / 30 * 100;



                hideKeyboard(getBaseContext(), priceInput);

                saleTax.setText(NumberFormat.getCurrencyInstance().format(totalTax));
                grandTotal.setText(NumberFormat.getCurrencyInstance().format(finalTotal));
                subTot.setText(NumberFormat.getCurrencyInstance().format(subTotal));
                tenPercent.setText(NumberFormat.getCurrencyInstance().format(ten));
                twentyPercent.setText(NumberFormat.getCurrencyInstance().format(twenty));
                thirtyPercent.setText(NumberFormat.getCurrencyInstance().format(thirty));
            }

        }catch(Exception e){taxError.setText("Invalid Tax");}

    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm =
            (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}