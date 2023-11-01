package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    errorCode: String? = "not.found",
    root: String? = null,
    message: String? = "item not found"
) : BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = errorCode,
        root = root,
        message = message
    )
)