/**
 * Package algonquin.cst2335.atti0019
 * This package contains the classes for the Password Complexity Checker Application.
 */

package algonquin.cst2335.atti0019;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class represents the main activity of the application.
 * It checks the complexity of a password and displays a message accordingly.
 *
 * @author Mohamed Attia
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private EditText tp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        Button bb = findViewById(R.id.bb);
        tp = findViewById(R.id.tp);

        bb.setOnClickListener(clk -> {
            String password = tp.getText().toString();
            boolean isComplex = checkPasswordComplexity(password);

            if (isComplex) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
        });
    }

    /**
     * Checks the complexity of a password based on uppercase, lowercase, digit, and special character requirements.
     *
     * @param pw The password to be checked.
     * @return true if the password meets complexity requirements, false otherwise.
     */
    boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase = false, foundLowerCase = false, foundNumber = false, foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);

            switch (c) {
                case '#':
                case '$':
                case '%':
                case '^':
                case '&':
                case '*':
                case '!':
                case '@':
                    foundSpecial = true;
                    break;
                default:
                    if (Character.isLowerCase(c)) {
                        foundLowerCase = true;
                    } else if (Character.isUpperCase(c)) {
                        foundUpperCase = true;
                    } else if (Character.isDigit(c)) {
                        foundNumber = true;
                    }
            }
        }

        if (!foundUpperCase) {
            Toast.makeText(this, "Missing an uppercase letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "Missing a lowercase letter", Toast.LENGTH_SHORT).show();
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
}