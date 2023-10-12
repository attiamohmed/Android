package algonquin.cst2335.atti0019;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import algonquin.cst2335.atti0019.databinding.ActivityMain2Binding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 123;
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load email from the first page
        String email = getIntent().getStringExtra("Email");
        binding.textView4.setText(email);

        // Load and set telephone number from SharedPreferences
        loadPhoneNumberFromSharedPreferences();

        // Load and set a saved bitmap image from SharedPreferences
        loadSavedImageFromSharedPreferences();

        // Set an OnClickListener for the "Call number" button
        binding.wbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhoneNumber();
            }
        });

        // Set an OnClickListener for the "Change Picture" button
        binding.wbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCameraActivity();
            }
        });
    }

    // Load and set telephone number from SharedPreferences
    private void loadPhoneNumberFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");
        binding.editTextPhone.setText(phoneNumber);
    }

    // Load and set a saved bitmap image from SharedPreferences
    private void loadSavedImageFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String imageUri = sharedPreferences.getString("imageUri", "");
        if (!imageUri.isEmpty()) {
            // Load and set the image using the URI
            Uri uri = Uri.parse(imageUri);
            binding.imageView.setImageURI(uri);
        }
    }

    // Load a phone call intent
    private void callPhoneNumber() {
        String phoneNumber = binding.editTextPhone.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    // Start the camera activity to take a picture
    private void startCameraActivity() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Get the image data from the intent
            Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            if (capturedImage != null) {
                // Update the ImageView
                binding.imageView.setImageBitmap(capturedImage);

                // Save the captured image to SharedPreferences and disk
                Uri imageUri = saveImageToDisk(capturedImage);
                saveImageUriToSharedPreferences(imageUri);
            }
        }
    }

    private Uri saveImageToDisk(Bitmap image) {
        // Create a file to save the image
        File imageFile = new File(getFilesDir(), "captured_image.png");

        try (FileOutputStream outputStream = new FileOutputStream(imageFile)) {
            image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return Uri.fromFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveImageUriToSharedPreferences(Uri imageUri) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("imageUri", imageUri.toString());
        editor.apply();
    }
}
