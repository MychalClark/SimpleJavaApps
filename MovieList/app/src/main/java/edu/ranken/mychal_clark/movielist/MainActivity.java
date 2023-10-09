package edu.ranken.mychal_clark.movielist;

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

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> movies;
    private TextView results;
    private EditText inputMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Views
        results = findViewById(R.id.results);
        inputMovie = findViewById(R.id.addMovieInput);

        //Initial Movies
        movies = new ArrayList<>(Arrays.asList("Shrek1", "Shrek2", "Shrek3"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < movies.size(); i++) {
            sb.append("\n").append(movies.get(i));
        }
        results.setText(sb);


        // Register Listeners
        inputMovie.setOnEditorActionListener((TextView view, int actionId, KeyEvent event) -> {

            addMovie(results);
            return false;
        });

        try{}
        catch(Exception ex){
            Log.e("TAG", ex.toString());
        }
    }

    public void addMovie(View view) {

        hideKeyboard(this, results);

        String movie = inputMovie.getText().toString().trim();
        //Add Movie if


        if (!movie.isEmpty()) {
            movies.add(movie);
            if (movies.size() > 10) {
                movies.remove(0);
            }
        } else {
            Toast.makeText(MainActivity.this, "Can't leave movie blank", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < movies.size(); i++) {
            sb.append("\n").append(movies.get(i));
        }
        results.setText(sb);
        inputMovie.setText("");

    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}