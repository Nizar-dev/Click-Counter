package apps.nb.working.clickcounter.model

sealed class ImageState {
    object Loading : ImageState()
    data class Success(val url: String) : ImageState()
    object Error : ImageState()
}