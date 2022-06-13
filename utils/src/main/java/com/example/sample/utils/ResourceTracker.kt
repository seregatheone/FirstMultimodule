package com.example.sample.utils

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class ResourceTracker<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {
        fun <T> success(data: T): ResourceTracker<T> =
            ResourceTracker(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ResourceTracker<T> =
            ResourceTracker(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ResourceTracker<T> =
            ResourceTracker(status = Status.LOADING, data = data, message = null)
    }
}