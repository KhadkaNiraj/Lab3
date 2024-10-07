package com.cst7335.lab02canfr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        EditText emailEditText = findViewById(R.id.emailEditText);
        Button loginButton = findViewById(R.id.loginButton);

        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString("userEmail", "");
        if (savedEmail.isEmpty()) {
            emailEditText.setHint("Type Your Email Here");
        } else {
            emailEditText.setText(savedEmail);
        }
        Log.d(TAG, "Loaded email" + savedEmail);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get user's email address
                String email = emailEditText.getText().toString();

                // create an Intent to start ProfileActivity，and pass email address
                Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
                goToProfile.putExtra("EMAIL", email);
                startActivity(goToProfile);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();

        // get input
        EditText emailEditText = findViewById(R.id.emailEditText);
        String userEmail = emailEditText.getText().toString();

        //save users input
        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userEmail", userEmail);
        editor.apply();

        Log.d(TAG, "Saved email: " + userEmail);
    }
}
