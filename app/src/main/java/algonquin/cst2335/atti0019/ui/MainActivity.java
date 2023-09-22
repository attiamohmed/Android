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
        binding.myImgb.setOnClickListener(
                clik ->{ }
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
        //checToast = findViewById(R.id.myCheck);
        // checToast.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
              //  Toast.makeText(MainActivity.this,"checked", Toast.LENGTH_SHORT).show();
            //}
        //});
        //swtToast = findViewById(R.id.mySwitch);
        //swtToast.setOnClickListener(new View.OnClickListener() {
            //@Override
          //  public void onClick(View view) {
              //  Toast.makeText(MainActivity.this, "Switched", Toast.LENGTH_SHORT).show();
            //}
        //});
        //rdiToast = findViewById(R.id.myRadio);
        //rdiToast.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            //}
      //  });
        checToast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}