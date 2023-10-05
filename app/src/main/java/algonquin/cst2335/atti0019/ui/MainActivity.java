package algonquin.cst2335.atti0019.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.atti0019.R;
import algonquin.cst2335.atti0019.data.MainViewModel;
import algonquin.cst2335.atti0019.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    CheckBox checToast;
    Switch swtToast;
    RadioButton rdiToast;

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
        ImageButton Im = binding.myImgb;
        ed.setText(viewModel.userString.getValue());

        binding.myCheck.setOnCheckedChangeListener(
                ( chc, Checked ) -> { viewModel.onOroff.postValue(Checked);
                   }

        );
        binding.mySwitch.setOnCheckedChangeListener(
                (swt, Switched) -> {  viewModel.onOroff.postValue( Switched);
              }
        );
        binding.myRadio.setOnCheckedChangeListener(
                ( rdi, Clicked ) -> {
                    viewModel.onOroff.postValue(Clicked);
                }
        );
        binding.myImgb.setOnClickListener(
                clik ->{

                }
        );
        viewModel.onOroff.observe( this, newValue ->{
            if (newValue){
                Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this,"Unchecked", Toast.LENGTH_SHORT).show();
            }
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
        Im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int width = view.getWidth();
                int height = view.getHeight();
                String message = "The width = " + width + " and height = " + height;
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });

    }


}