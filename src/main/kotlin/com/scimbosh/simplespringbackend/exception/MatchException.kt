package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class MatchException(
    errorCode: String? = "values.not.equal",
    cause: String? = null,
    message: String? = "value not equal"
) : BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = errorCode,
        cause = cause,
        message = message
    )
)