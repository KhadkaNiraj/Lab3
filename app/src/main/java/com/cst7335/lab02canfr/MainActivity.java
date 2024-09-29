package com.cst7335.lab02canfr;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear); // Use whichever layout you're testing

        // Switch reference
        Switch mySwitch = findViewById(R.id.mySwitch);

        // Set a checked change listener
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String switchState = isChecked ? "on" : "off";

                // Show the Snackbar
                Snackbar snackbar = Snackbar.make(buttonView, "The switch is now " + switchState, Snackbar.LENGTH_LONG);

                // Set an "Undo" action in the Snackbar
                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Revert the switch state
                        mySwitch.setChecked(!isChecked);
                    }
                });

                snackbar.show();
            }
        });
    }
}