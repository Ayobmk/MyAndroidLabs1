package algonquin.cst2335.elma0076;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This is the main activity for the application.
 * @author Ayoub El makhchouni
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editTextTextPassword);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(click ->{
            String password = et.getText().toString();

            boolean complex = checkPasswordComplexity(password);

            if (!complex){
                tv.setText("You shall not pass");
            }
        });

    }

    /**
     * This method checks the complexity of a password. It checks if the password contains at least one uppercase letter, one lowercase letter, one digit, and one special character.
     * @param password The password to be checked.
     * @return true if the password meets the complexity requirements, false otherwise.
     */
    boolean checkPasswordComplexity(String password) {
        boolean foundUpperCase = false, foundLowerCase = false,
                foundNumber = false, foundSpecial = false;

        for (char ch : password.toCharArray()){
            if (Character.isUpperCase(ch))
                foundUpperCase = true;
            else if (Character.isLowerCase(ch))
                foundLowerCase = true;
            else if (Character.isDigit(ch))
                foundNumber = true;
            else if (isSpecialCharacter(ch))
                foundSpecial = true;

            if(foundUpperCase && foundLowerCase && foundNumber && foundSpecial)
                return true;
        }
        if (!foundUpperCase){
            Toast.makeText(this, "Password is missing an upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!foundLowerCase){
            Toast.makeText(this, "Password is missing a lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!foundNumber){
            Toast.makeText(this, "Password is missing a number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!foundSpecial){
            Toast.makeText(this, "Password is missing a special character", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    /**
     * This method checks if a character is a special character.
     * @param ch The character to be checked.
     * @return true if the character is a special character, false otherwise.
     */
    boolean isSpecialCharacter (char ch){
        return (ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*' || ch == '!' || ch == '@' || ch =='?');
    }
}