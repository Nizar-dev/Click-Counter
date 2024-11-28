package apps.nb.working.clickcounter.ui.screens

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import apps.nb.working.clickcounter.R
import apps.nb.working.clickcounter.model.ImageState
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun StatefulScreen(
    paddingValues: PaddingValues
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val imageUrl by mainViewModel.sourceImage.collectAsStateWithLifecycle()
    val counter by mainViewModel.counter.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var imageState by remember { mutableStateOf<ImageState>(ImageState.Loading) }

    LaunchedEffect(imageUrl) {
        try {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .build()
            loader.execute(request)
            imageState = ImageState.Success(imageUrl)
        } catch (e: Exception) {
            imageState = ImageState.Error
        }
    }

    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            modifier = Modifier.padding( 8.dp),
            text = stringResource(R.string.stateful_loading_description),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        when (imageState) {
            is ImageState.Loading -> CircularProgressIndicator(modifier = Modifier)
            is ImageState.Success -> AsyncImage(
                model = (imageState as ImageState.Success).url,
                contentDescription = null,
                modifier = Modifier
                    .size(width = 300.dp, height = 400.dp)
                    .clickable {
                    mainViewModel.updateSourceImage()
                }
            )
            is ImageState.Error -> Text("Error loading image", modifier = Modifier)
        }
        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = "Number of clicks: $counter",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}
