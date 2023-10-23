package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    errorCode: String? = "user.not.found",
    root: String? = "duplicate key value violates unique constraint",
    message: String? = null
) : BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = errorCode,
        root = root,
        message = message
    )
)