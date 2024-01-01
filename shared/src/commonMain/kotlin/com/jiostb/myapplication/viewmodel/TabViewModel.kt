package com.jiostb.myapplication.viewmodel

import androidx.compose.foundation.gestures.DraggableState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TabViewModel() : ViewModel()  {


    private val _tabIndex = MutableStateFlow<Int>(0)
    val tabIndex: StateFlow<Int> = _tabIndex.asStateFlow()



    val tabs = listOf("Home", "About", "Settings")

    var isSwipeToTheLeft: Boolean = false
    private val draggableState = DraggableState { delta ->
        isSwipeToTheLeft = delta > 0
    }
    private val _dragState = MutableStateFlow<DraggableState>(draggableState)
    val dragState: StateFlow<DraggableState> = _dragState.asStateFlow()

    fun updateTabIndexBasedOnSwipe() {
        _tabIndex.value = when (isSwipeToTheLeft) {
            true -> floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }
   private fun floorMod(x: Int, y: Int): Int {
        var mod = x % y

        if ((mod xor y) < 0 && mod != 0) {
            mod += y
        }
        return mod
    }
}