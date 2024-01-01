package com.jiostb.myapplication.domain.usecases

import com.jiostb.myapplication.data.model.relevance.Relevance

sealed class RelevanceState {
    object LOADING : RelevanceState()
    data class SUCCESS(val relevance: Relevance) : RelevanceState()
    data class ERROR(val error: String) : RelevanceState()
}