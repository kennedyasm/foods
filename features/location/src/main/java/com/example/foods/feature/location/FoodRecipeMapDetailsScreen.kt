package com.example.foods.feature.location

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foods.domain.State
import com.example.foods.domain.State.Companion.to
import com.example.foods.domain.models.FoodRecipeMapDetailUi
import com.example.foods.features.location.R
import com.example.foods.ui.common.CircleProgress
import com.example.foods.ui.common.ErrorScreen
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

private const val CAMERA_ZOOM = 8f

@Composable
fun FoodRecipeMapDetailsScreen(viewModel: FoodRecipeDetailMapViewModel = hiltViewModel()) {
    val composeState = viewModel.mapDetailState.collectAsStateWithLifecycle()
    when (val state = composeState.value) {
        is State.Success -> FoodRecipeMapDetailsScreenView(state.to())
        is State.Loading -> CircleProgress()
        is State.Error -> ErrorScreen(errorMessage = stringResource(id = R.string.no_available_map))
    }
}

@Composable
fun FoodRecipeMapDetailsScreenView(mapDetailUi: FoodRecipeMapDetailUi) {
    val locationLatLong = LatLng(mapDetailUi.latitude, mapDetailUi.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationLatLong, CAMERA_ZOOM)
    }
    Card(modifier = Modifier.padding(16.dp)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = locationLatLong),
                title = mapDetailUi.name,
            )
        }
    }
}