package algonquin.cst2335.atti0019.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.atti0019.R;
import algonquin.cst2335.atti0019.data.MainViewModel;
import algonquin.cst2335.atti0019.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setContentView(binding.getRoot());
        TextView tv = binding.myText;
        Button b = binding.myButton;
        EditText ed = binding.myEdit;
        ed.setText(viewModel.userString.getValue());
        binding.myCheck.setOnCheckedChangeListener(
                ( chc, onOroff ) -> { viewModel.onOroff.postValue(onOroff); }

        );
        binding.mySwitch.setOnCheckedChangeListener(
                (swt, onOroff) -> { viewModel.onOroff.postValue( onOroff);}
        );
        binding.myRadio.setOnCheckedChangeListener(
                ( rdi, onOroff) -> { viewModel.onOroff.postValue(onOroff);}
        );

        viewModel.onOroff.observe( this, newValue ->{
            binding.myCheck.setChecked(newValue);
            binding.mySwitch.setChecked(newValue);
            binding.myRadio.setChecked(newValue);
        });
        viewModel.userString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv.setText("You wrote: " +s);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tv.setText(R.string.hello_message);
                //b.setText(R.string.buttom_message);
                //ed.setText(R.string.edittext_message);
                tv.setText("U clicked the button");
                // b.setText("Why?");
                String string = ed.getText().toString();
                viewModel.userString.postValue(string);

            }
        });
    }
}