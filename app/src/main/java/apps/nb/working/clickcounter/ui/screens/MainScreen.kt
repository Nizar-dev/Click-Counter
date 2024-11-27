package apps.nb.working.clickcounter.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import apps.nb.working.clickcounter.R

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    mainViewModel.incrementCounter()
                }
            ),
        contentAlignment = Alignment.Center,

    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                modifier = modifier.padding(bottom = 16.dp),
                text = stringResource(R.string.click_to_increment_the_counter),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                modifier = modifier.padding(bottom = 16.dp),
                text = "Number of clicks: ${mainViewModel.counter.value}",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}