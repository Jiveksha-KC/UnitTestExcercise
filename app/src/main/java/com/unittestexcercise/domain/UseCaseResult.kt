package com.unittestexcercise.domain

sealed class UseCaseResult<out T> {
    data class SuccessResult<T>(val value: T) : UseCaseResult<T>()
    object GenericErrorResult : UseCaseResult<Nothing>()
    object NetworkErrorResult : UseCaseResult<Nothing>()
}