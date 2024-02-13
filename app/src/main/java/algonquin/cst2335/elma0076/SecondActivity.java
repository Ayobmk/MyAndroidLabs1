package algonquin.cst2335.elma0076;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        TextView welcomeMessage = findViewById(R.id.textViewTop);
        welcomeMessage.setText("Welcome back "+ emailAddress);



        Button callBtn =  findViewById(R.id.callBtn);
        callBtn.setOnClickListener(clk-> {
            TextView number = findViewById(R.id.phoneNmber);
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + number));
            startActivity(call);
        });

        Button changeBtn = findViewById(R.id.changeBtn);
        changeBtn.setOnClickListener(clk-> {
            ImageView phoneImg = findViewById(R.id.phoneImg);
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            ActivityResultLauncher <Intent> cameraResult = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

                @Override

                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        phoneImg.setImageBitmap( thumbnail);


                    }

                }

            });
            cameraResult.launch(cameraIntent);

        });


    }


}