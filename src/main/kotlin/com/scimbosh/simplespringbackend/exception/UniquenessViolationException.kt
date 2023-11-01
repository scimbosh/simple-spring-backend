package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class UniquenessViolationException(
    errorCode: String? = "uniqueness.violation",
    cause: String? = null,
    message: String? = "duplicate key value violates unique constraint"
) : BaseException(
    HttpStatus.CONFLICT,
    ApiError(
        errorCode = errorCode,
        cause = cause,
        message = message
    )
)