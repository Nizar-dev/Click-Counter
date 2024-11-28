package apps.nb.working.clickcounter.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import apps.nb.working.clickcounter.IMAGE_URL
import apps.nb.working.clickcounter.R
import coil.compose.AsyncImage

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
){
    val imageUrl by mainViewModel.sourceImage.collectAsStateWithLifecycle()
    val counter by mainViewModel.counter.collectAsStateWithLifecycle()
    Scaffold {
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Text(
                modifier = modifier.padding( 8.dp),
                text = stringResource(R.string.introduction),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                modifier = modifier
                    .size(width = 300.dp, height = 400.dp)
                    .clickable {
                        mainViewModel.updateSourceImage()
                    }
                    ,
                contentDescription = stringResource(R.string.random_image),
                model = imageUrl,
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = "Number of clicks: $counter",
                style = MaterialTheme.typography.headlineSmall,
            )

        }

    }
}
