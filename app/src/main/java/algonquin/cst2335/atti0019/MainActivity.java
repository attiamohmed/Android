package algonquin.cst2335.atti0019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.myText);
        Button b = findViewById(R.id.myButton);
        EditText ed = findViewById(R.id.myEdit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("U clicked the button");
                b.setText("Why?");
                ed.setText("Tell me why, NOW");

            }
        });
    }
}