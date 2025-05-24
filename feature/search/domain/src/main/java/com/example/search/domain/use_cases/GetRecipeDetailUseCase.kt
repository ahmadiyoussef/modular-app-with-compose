package com.example.search.domain.use_cases

import com.example.common.utils.Resource
import com.example.search.domain.model.RecipeDetails
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(private val repository: SearchRepository) {

    operator fun invoke(id: String) = flow<Resource<RecipeDetails>> {
        emit(Resource.Loading())
        val response = repository.getRecipeDetail(id)
        if (response.isSuccess) {
            emit(Resource.Success(response.getOrThrow()))
        } else {
            emit(Resource.Error(response.exceptionOrNull().toString()))
        }
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}

