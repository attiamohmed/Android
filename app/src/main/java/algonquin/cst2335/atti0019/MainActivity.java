package algonquin.cst2335.atti0019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.w( "MainActivity", "In onStart() - the app is now visible on the screen" );
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.w( "MainActivity", "In onResume() - the app is now responding to user input " );
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.w( "MainActivity", "In onPause() - the app is now is still visible to the user but its moved to the background " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i( "MainActivity", "In onStop() - the app now is no longer visible to the user but still in the memory" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i( "MainActivity", "In onDestroy() - the app now is no longer in the memory" );
    }
}