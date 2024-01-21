package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.elma0076.R;
import algonquin.cst2335.elma0076.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model.editString.observe(this, s ->{
            variableBinding.textview.setText("Your edit text has "+ s);
        });

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

//        TextView mytext = findViewById(R.id.textview);
//        TextView mytext = variableBinding.textview;
//        variableBinding.textview.setText(model.editString);

//        Button btn = findViewById(R.id.mybutton);
//        Button btn = variableBinding.mybutton;
        variableBinding.mybutton.setOnClickListener(click ->
        {
//          model.editString = variableBinding.myedittext.getText().toString();
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            variableBinding.textview.setText("Your edit text has: " + model.editString);
        });
//        EditText myedit = findViewById(R.id.myedittext);
//        EditText myedit = variableBinding.myedittext;


    }

//    model.editString.observe(this, new Observe<String>(){
//        @Override
//                public void onChange(String s){
//
//        }
//    });
}