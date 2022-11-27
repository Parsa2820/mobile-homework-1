package ir.parsa2820.terminator.ui.courses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CoursesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CoursesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is courses fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}