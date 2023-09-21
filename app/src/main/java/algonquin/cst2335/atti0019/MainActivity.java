package algonquin.cst2335.atti0019;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.atti0019.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView tv = binding.myText;
        Button b = binding.myButton;
        EditText ed = binding.myEdit;

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(R.string.hello_message);
                b.setText(R.string.buttom_message);
                ed.setText(R.string.edittext_message);
                tv.setText("U clicked the button");
                b.setText("Why?");
                ed.setText("Tell me why, NOW");

            }
        });
    }
}