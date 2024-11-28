package apps.nb.working.clickcounter.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import apps.nb.working.clickcounter.R
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ManualLoadingScreen(
    paddingValues: PaddingValues
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val imageUrl by mainViewModel.sourceImage.collectAsStateWithLifecycle()
    val counter by mainViewModel.counter.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    var preloadedImage by remember { mutableStateOf<Any?>(null) }
    LaunchedEffect(imageUrl) {
        try {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .build()
            val result = loader.execute(request)
            preloadedImage = result.drawable
        } catch (e: Exception) {
            isError = true
        } finally {
            isLoading = false
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
            text = stringResource(R.string.manual_loading_description),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        if (isLoading) {
            CircularProgressIndicator()
        } else if ( isError) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Error loading image")
            Button(
                onClick = {
                    mainViewModel.updateSourceImage()
                    isLoading = true
                    isError = false
                },
                modifier = Modifier.padding(8.dp)
                ) {
                Text("Retry")
            }
        } else {
            AsyncImage(
                modifier = Modifier
                    .clickable {
                        mainViewModel.updateSourceImage()
                    }
                    .size(width = 300.dp, height = 400.dp)
                    .padding(8.dp),
                model = preloadedImage,
                contentDescription = stringResource(R.string.random_image),
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = "Number of clicks: $counter",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}