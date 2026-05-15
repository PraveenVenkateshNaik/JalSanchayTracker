package com.praveen.jalsanchay.ui.screens.reports

import androidx.lifecycle.ViewModel
import com.praveen.jalsanchay.data.local.entity.RainfallEntry
import com.praveen.jalsanchay.repository.RainfallRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    rainfallRepository: RainfallRepository
) : ViewModel() {

    val entries: Flow<List<RainfallEntry>> = rainfallRepository.getAllEntries()

}
