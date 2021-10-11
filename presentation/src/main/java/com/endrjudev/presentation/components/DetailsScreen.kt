package com.endrjudev.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.endrjudev.domain.model.RepositoryModel
import com.endrjudev.presentation.DetailViewModel
import com.endrjudev.presentation.R
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailsScreen(
    viewModel: DetailViewModel = getViewModel { parametersOf(itemId) },
    itemId: Int
) {
    val state = viewModel.state.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is DetailViewModel.UiState.Content -> DetailContent(state.repositoryModel)
            is DetailViewModel.UiState.Error -> Text(text = "Error")
            is DetailViewModel.UiState.Loading -> Loading()
        }
    }
}

@Composable
fun DetailContent(repositoryModel: RepositoryModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePicture(imageUrl = repositoryModel.ownerPicture)
            Text(text = repositoryModel.name)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = repositoryModel.description)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = repositoryModel.cloneUrl)
        }
    }
}

@Composable
fun ProfilePicture(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                transformations(CircleCropTransformation())
                error(R.drawable.github_mark)
            }
        ),
        contentDescription = null,
        modifier = modifier
            .padding(8.dp)
            .size(48.dp)
    )
}