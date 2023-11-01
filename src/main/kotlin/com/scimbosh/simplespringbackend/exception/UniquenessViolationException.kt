package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class UniquenessViolationException(
    errorCode: String? = "uniqueness.violation",
    root: String? = null,
    message: String? = "duplicate key value violates unique constraint"
) : BaseException(
    HttpStatus.CONFLICT,
    ApiError(
        errorCode = errorCode,
        root = root,
        message = message
    )
)