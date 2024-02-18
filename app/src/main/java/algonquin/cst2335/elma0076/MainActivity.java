package algonquin.cst2335.elma0076;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity class represents the main screen of the application.
 * It allows users to input a password and checks its complexity.
 *
 * @author Hichem Khelifi Ahmed
 * @version 1.0
 */

public final class MainActivity extends AppCompatActivity {

    /**
     * This holds the text displayed at the center of the screen.
     */
    private TextView tv = null;

    /**
     * This holds the EditText field where users input their passwords.
     */
    private EditText et = null;

    /**
     * This holds the Button used to trigger the password complexity check.
     */
    private Button btn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.TextViewPasswd);
        EditText et = findViewById(R.id.TextEditPasswd);
        Button btn = findViewById(R.id.BtnLogin);

        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();
            if (checkPasswordComplexity(password)) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
        });
    }

    /**
     * This function checks if the given string meets the password complexity requirements.
     *
     * @param pw The string object that we are checking
     * @return Return true if the password is complex
     */

    boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase) {
            Toast.makeText(this, "Missing an upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "Missing a lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(this, "Missing a number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(this, "Missing a special character", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    boolean isSpecialCharacter(char c) {
        switch (c) {
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '!':
            case '@':
            case '?':
                return true;
            default:
                return false;

        }
    }
}