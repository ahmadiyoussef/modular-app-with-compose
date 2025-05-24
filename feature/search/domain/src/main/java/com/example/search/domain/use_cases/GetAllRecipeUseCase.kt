package com.example.search.domain.use_cases

import com.example.common.utils.Resource
import com.example.search.domain.model.RecipeDomainModel
import com.example.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetAllRecipeUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(q: String) = flow<Resource<List<RecipeDomainModel>>> {
        emit(Resource.Loading())
        val response = searchRepository.getRecipes(q)
        if (response.isSuccess) {
            emit(Resource.Success(response.getOrThrow()))
        } else {
            emit(Resource.Error(response.exceptionOrNull()?.localizedMessage))
        }
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}