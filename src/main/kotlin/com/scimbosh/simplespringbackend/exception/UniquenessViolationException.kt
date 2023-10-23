package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class UniquenessViolationException(
    errorCode: String? = "id.create.conflict",
    root: String? = "duplicate key value violates unique constraint",
    message: String? = null
) : BaseException(
    HttpStatus.CONFLICT,
    ApiError(
        errorCode = errorCode,
        root = root,
        message = message
    )
)