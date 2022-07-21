package hobby.wei.c.reflow.visual.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TestViewModel(app: Application) : AndroidViewModel(app) {

    init {
        viewModelScope.launch {

        }
    }

}