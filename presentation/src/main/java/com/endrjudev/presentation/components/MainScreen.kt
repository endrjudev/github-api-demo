package com.endrjudev.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.endrjudev.presentation.MainViewModel
import com.endrjudev.presentation.R
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = getViewModel(),
    onClick: (Int) -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is MainViewModel.UiState.Content -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(0.dp, 0.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = "Repositories:"
                        )
                    }
                    items(state.repositories) { item ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onClick(item.id) }
                                .padding(8.dp),
                            text = item.name
                        )
                    }
                }
            }
            is MainViewModel.UiState.Error -> {
                Text(text = "Error")
            }
            is MainViewModel.UiState.Loading -> {
                Loading()
            }
        }
    }
}

@Composable
fun Loading() {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(750),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        modifier = Modifier
            .size(48.dp)
            .scale(scale),
        painter = painterResource(id = R.drawable.github_mark),
        contentDescription = null
    )
}