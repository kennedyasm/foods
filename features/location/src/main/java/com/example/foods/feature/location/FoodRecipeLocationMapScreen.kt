package com.example.foods.feature.location

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foods.domain.State
import com.example.foods.domain.State.Companion.to
import com.example.foods.domain.models.FoodRecipeLocationMapUi
import com.example.foods.ui.common.CircleProgress
import com.example.foods.ui.common.ErrorScreen
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

private const val CAMERA_ZOOM = 7f

@Composable
fun FoodRecipeLocationMapScreen(viewModel: FoodRecipeLocationMapViewModel = hiltViewModel()) {
    val locationMapState = viewModel.locationMap.collectAsStateWithLifecycle()
    when (val state = locationMapState.value) {
        is State.Success -> FoodRecipeLocationMapScreenView(state.to())
        is State.Loading -> CircleProgress()
        is State.Error -> ErrorScreen(state.message)
    }
}

@Composable
fun FoodRecipeLocationMapScreenView(mapDetailUi: FoodRecipeLocationMapUi) {
    val locationLatLong = LatLng(mapDetailUi.latitude, mapDetailUi.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationLatLong, CAMERA_ZOOM)
    }
    Card(modifier = Modifier.padding(16.dp)) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .testTag("food recipe location map"),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = locationLatLong),
                title = mapDetailUi.name
            )
        }
    }
}