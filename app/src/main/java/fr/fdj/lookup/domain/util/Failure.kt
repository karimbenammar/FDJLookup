package fr.fdj.lookup.domain.util

sealed class Failure(val message: String) {
    class NetworkError(message: String) : Failure(message)
}