package com.example.presentation.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.State
import com.example.domain.State.Companion.to
import com.example.domain.models.FoodRecipeMapDetailUi
import com.example.presentation.common.CircleProgress
import com.example.presentation.common.ErrorScreen
import com.example.presentation.viewmodel.FoodRecipeDetailMapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun FoodRecipeMapDetailsScreen(viewModel: FoodRecipeDetailMapViewModel = hiltViewModel()) {

    val composeState = viewModel.mapDetailState.collectAsStateWithLifecycle()
    when (val state = composeState.value) {
        is State.Success -> FoodRecipeMapDetailsScreenView(state.to())
        is State.Loading -> CircleProgress()
        is State.Error -> ErrorScreen(errorMessage = "Información no disponible")
    }
}

@Composable
fun FoodRecipeMapDetailsScreenView(mapDetailUi: FoodRecipeMapDetailUi) {
    val locationLatLong = LatLng(mapDetailUi.latitude, mapDetailUi.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationLatLong, 8f)
    }
    Column {
        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(16.dp)) {
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


}