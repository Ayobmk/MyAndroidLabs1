package data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
//    public String editString;
    public MutableLiveData<String> editString = new MutableLiveData<>();
    private String text = "The value is now: ";
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();

    public String getToustText() {
        return this.text + (Boolean.TRUE.equals(this.isSelected.getValue()) ? "isChecked" : "not Checked");
    }

}
