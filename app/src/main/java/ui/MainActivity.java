package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.widget.Toast;

import algonquin.cst2335.elma0076.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    int duration = Toast.LENGTH_SHORT;

    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        model.editString.observe(this, s ->{
            variableBinding.textview.setText("Your edit text has "+ s);
        });

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

        variableBinding.checkBox.setOnCheckedChangeListener((checkBox, isChecked) ->{
            model.isSelected.postValue(isChecked);
            this.setChecked();
        });
        variableBinding.radioButton.setOnCheckedChangeListener((checkBox, isChecked) ->{
            model.isSelected.postValue(isChecked);
            this.setChecked();
        });
        variableBinding.switch1.setOnCheckedChangeListener((checkBox, isChecked) ->{
            model.isSelected.postValue(isChecked);
            this.setChecked();
        });
    }

//    model.editString.observe(this, new Observe<String>(){
//        @Override
//                public void onChange(String s){
//
//        }
//    });
    public void setChecked(){
        model.isSelected.observe(this, selected ->{
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
        });
        toast = Toast.makeText(this, model.getToustText() , duration);
        toast.show();
    }
}