package com.example.search.domain.use_cases

import com.example.common.utils.NetworkResult
import com.example.search.domain.model.RecipeDomainModel
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetAllRecipeUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(q:String) = flow<NetworkResult<List<RecipeDomainModel>>> {  }
}