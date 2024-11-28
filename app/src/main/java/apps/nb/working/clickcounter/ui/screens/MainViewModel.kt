package apps.nb.working.clickcounter.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :  ViewModel() {
    private val _sourceImage = MutableStateFlow("https://picsum.photos/300/400")
    val sourceImage: StateFlow<String> = _sourceImage.asStateFlow()
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()
    init {
        updateSourceImage()
    }
    /**
     * Increment the counter by 1
     */
    private fun incrementCounter() {
        _counter.value++
    }
    /**
     * Reset the counter to 0
     */
    fun resetCounter() {
        _counter.value = 0
    }
    /**
     * Set the source image to a random image
     */
    fun updateSourceImage() {
        incrementCounter()

            _sourceImage.value = "https://picsum.photos/300/400?random=${(0..1000).random()}"

    }
}
