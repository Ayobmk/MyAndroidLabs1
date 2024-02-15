package algonquin.cst2335.elma0076;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        TextView welcomeBackMsg;
        welcomeBackMsg = findViewById(R.id.welcome_txtView);
        if (emailAddress != null && !emailAddress.isEmpty()) {
            welcomeBackMsg.setText("Welcome Back, " + emailAddress);
        } else {
            welcomeBackMsg.setText("Welcome Back");
        }


        EditText phoneNumber;
        phoneNumber = findViewById(R.id.editTextPhone);

        Button callNumber;
        callNumber = findViewById(R.id.callNumber_btn);

        callNumber.setOnClickListener( click->{
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
            startActivity(call);
        });

        ImageView profileImage;
        profileImage = findViewById(R.id.picture);

        Button changeImg;
        changeImg = findViewById(R.id.changePic_btn);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK){

                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            profileImage.setImageBitmap(thumbnail);

                        }

                    }
                }
        );

        changeImg.setOnClickListener( click->{
            cameraResult.launch(cameraIntent);
        });
    }
}