package algonquin.cst2335.elma0076;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private static String TAG = "MainActivity";

    @Override
    protected void onStart() {
        super.onStart();
        Log.w( "MainActivity", "The application is now visible on screen" );

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w( "MainActivity", "The application is now responding to user input" );

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( "MainActivity", "The application no longer responds to user input" );

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.w( "MainActivity", "The application is no longer visible" );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( "MainActivity", "Any memory used by the application is freed" );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
        //Log.d(TAG, "Message");

        Button loginBotton = findViewById(R.id.loginBtn);
        loginBotton.setOnClickListener(clk->{sendMessage(clk);});

    }
    public void sendMessage(View view){
        Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        //String message = editText.getText().toString();
        //nextPage.putExtra(EXTRA_MESSAGE, message);

        TextView email= findViewById(R.id.EmailAddress) ;
        nextPage.putExtra("EmailAddress",email.getText().toString());
        startActivity(nextPage);
    }

}