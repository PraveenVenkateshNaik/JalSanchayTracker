package com.praveen.jalsanchay.ui.screens.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveen.jalsanchay.data.local.entity.RainfallEntry
import com.praveen.jalsanchay.repository.RainfallRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DataEntryUiState(
    val saveSuccess: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class DataEntryViewModel @Inject constructor(
    private val rainfallRepository: RainfallRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DataEntryUiState())
    val uiState: StateFlow<DataEntryUiState> = _uiState

    fun saveEntry(
        roofArea: String,
        rainfall: String,
        tankCapacity: String,
        runoffCoefficient: String
    ) {
        viewModelScope.launch {
            try {
                val areaVal = roofArea.toDoubleOrNull() ?: throw IllegalArgumentException("Invalid Area")
                val rainfallVal = rainfall.toDoubleOrNull() ?: throw IllegalArgumentException("Invalid Rainfall")
                val capacityVal = tankCapacity.toDoubleOrNull() ?: throw IllegalArgumentException("Invalid Capacity")
                val coeffVal = runoffCoefficient.toDoubleOrNull() ?: throw IllegalArgumentException("Invalid Coefficient")

                // Formula: Area × Rainfall × 0.0929 × Runoff Coefficient (assuming sq ft to sq m conversion approx)
                // Note: Standard formula Area(sqm) * Rainfall(mm) * Coeff = Liters.
                // Since Area is given in sq ft: 1 sq ft = 0.0929 sq meters.
                val litersSaved = areaVal * rainfallVal * 0.0929 * coeffVal
                
                // Get previous tank level to add to it, bounded by capacity
                val previousEntry = rainfallRepository.getLatestEntry().firstOrNull()
                val previousLiters = if (previousEntry != null) (previousEntry.tankLevel / 100) * capacityVal else 0.0
                
                var newLitersInTank = previousLiters + litersSaved
                if (newLitersInTank > capacityVal) newLitersInTank = capacityVal
                
                val tankPercentage = (newLitersInTank / capacityVal) * 100

                val entry = RainfallEntry(
                    date = System.currentTimeMillis(),
                    roofArea = areaVal,
                    rainfall = rainfallVal,
                    runoffCoefficient = coeffVal,
                    litersSaved = litersSaved,
                    tankCapacity = capacityVal,
                    tankLevel = tankPercentage
                )

                rainfallRepository.insertEntry(entry)
                _uiState.value = DataEntryUiState(saveSuccess = true)

            } catch (e: Exception) {
                _uiState.value = DataEntryUiState(errorMessage = e.message ?: "Invalid input")
            }

        }
    }
}
