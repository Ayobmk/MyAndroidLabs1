package algonquin.cst2335.elma0076;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    private final String FILE_NAME = "Picture.png";
    private Bitmap capturedBitmap;
    private static final String SHARED_PREFS_FILE = "MyData";
    private static final String PHONE_NUMBER_KEY = "PhoneNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);

        // Retrieve the email address
        Intent intent = getIntent();
        if (intent != null) {
            String emailAddress = intent.getStringExtra("EmailAddress");
            String phoneNumber = intent.getStringExtra("PhoneNumber");



            AppCompatTextView welcomeTextView = findViewById(R.id.textViewWelcome);
            welcomeTextView.setText("Welcome back " + emailAddress);

            AppCompatEditText phoneNumberEditText = findViewById(R.id.editTextPhone);
            phoneNumberEditText.setText(phoneNumber);


        }

        String storedPhoneNumber = prefs.getString(PHONE_NUMBER_KEY, "");
        AppCompatEditText editTextPhone = findViewById(R.id.editTextPhone);
        editTextPhone.setText(storedPhoneNumber);


        Button callButton = findViewById(R.id.buttonCall);
        AppCompatEditText phoneNumberEditText = findViewById(R.id.editTextPhone);

        callButton.setOnClickListener(view -> {
            // Get the phone number from the EditText
            String phoneNumber = phoneNumberEditText.getText().toString();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PHONE_NUMBER_KEY, phoneNumber);
            editor.apply();


            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));

            // Start the dialer activity
            startActivity(callIntent);
        });


        ImageView profileImage = findViewById(R.id.imageView);


        // Check if the file exists
        File file = new File(getFilesDir(), FILE_NAME);
        if (file.exists()) {
            // If the file exists, load it into the bitmap
            Bitmap loadedBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            // Set the loaded bitmap to the ImageView
            profileImage.setImageBitmap(loadedBitmap);
        }







        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Register for the result using the ActivityResultLauncher
        ActivityResultLauncher<Intent> cameraResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // The image capture was successful
                            Intent data = result.getData();
                            if (data != null && data.getExtras() != null) {
                                // Retrieve the captured image bitmap from the extras
                                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");


                                // Update the profile image with the captured image
                                profileImage.setImageBitmap(thumbnail);
                                saveBitmapToFile(thumbnail);
                            }
                        }
                    }
                });

        // Set up a button click listener to launch the camera intent
        findViewById(R.id.buttonChangePicture).setOnClickListener(view -> {
            // Start the camera activity using the ActivityResultLauncher
            cameraResultLauncher.launch(cameraIntent);
        });





    }

    private void saveBitmapToFile(Bitmap capturedBitmap){
        try (FileOutputStream fOut = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            if (capturedBitmap != null) {
                capturedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}