package com.neodinary.reque.mission.record

sealed interface MissionRecordAction {
    data object StartCapture : MissionRecordAction
}