package ir.parsa2820.terminator.ui.agenda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgendaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AgendaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is agenda fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}