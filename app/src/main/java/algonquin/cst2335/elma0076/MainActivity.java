package algonquin.cst2335.elma0076;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.text.BreakIterator;


public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    Switch sw;


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private static final String SHARED_PREFS_FILE = "MyData";
    private static final String PHONE_NUMBER_KEY = "PhoneNumber";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("LoginName", "");


        EditText editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setText(emailAddress);

        Log.w("MainActivity", "In onCreate() - Loading Widgets");


        Button loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(clk -> {
            String enteredEmail = editTextEmail.getText().toString();

            // Save the email address to shared preferences
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName", enteredEmail);

            editor.apply();

            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);


            EditText et = findViewById(R.id.editTextEmail);


            nextPage.putExtra("EmailAddress", et.getText().toString());
            Intent fromPrevious = getIntent();
            String EmailAddress = fromPrevious.getStringExtra("EmailAddress");

            startActivity(nextPage);
        });



    }

}