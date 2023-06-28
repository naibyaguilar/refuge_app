package com.example.refuge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.refuge.ui.home.onboarding.OnboardingScreen
import com.example.refuge.ui.home.restrooms.RestroomsScreen
import com.example.refuge.ui.home.restrooms.RestroomsViewModel
import com.example.refuge.ui.home.restrooms.RestroomsViewModelFactory
import com.example.refuge.ui.theme.RefugeTheme
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : ComponentActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: RestroomsViewModelFactory by instance()

    private val viewModel: RestroomsViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RefugeTheme {
                MyApp(modifier = Modifier.fillMaxSize(), viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, viewModel: RestroomsViewModel) {

    val restrooms by viewModel.restrooms.collectAsState(initial = emptyList())
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            RestroomsScreen(restrooms = restrooms)
        }
    }
}


