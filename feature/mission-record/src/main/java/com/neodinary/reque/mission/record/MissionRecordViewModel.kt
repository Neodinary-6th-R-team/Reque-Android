package com.neodinary.reque.mission.record

import androidx.lifecycle.viewModelScope
import com.neodinary.reque.common.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MissionRecordViewModel : BaseViewModel() {
    private val _action : MutableSharedFlow<MissionRecordAction> = MutableSharedFlow()
    val action : SharedFlow<MissionRecordAction> = _action.asSharedFlow()

    fun startCapture() {
        viewModelScope.launch {
            _action.emit(MissionRecordAction.StartCapture)
        }
    }

}