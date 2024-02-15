package algonquin.cst2335.elma0076;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w( TAG, "In onCreate() - Loading Widgets" );
        Log.w(TAG, "Inside onCreate() - This is called when the activity is first created.");

        Button loginButton;
        loginButton = findViewById(R.id.login_btn);

        TextView EmailEditTxt;
        EmailEditTxt = findViewById(R.id.email_editTxt);

        loginButton.setOnClickListener(click ->{
            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra("EmailAddress", EmailEditTxt.getText().toString());
            startActivity(nextPage);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        super.onStart();
        Log.w(TAG, "Inside onStart() - This is called when the activity becomes visible to the user.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "Inside onStop() - This is called when the activity is no longer visible to the user.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "Inside onPause() - The paused activity is called when the current activity is being paused and the previous activity is being resumed.");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "Inside onResume() - This is called when the user starts interacting with the application.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "Inside onDestroy() - This is called before the activity is destroyed by the system.");
    }
}