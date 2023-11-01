package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class MatchException(
    errorCode: String? = "value.not.equal",
    root: String? = "value not equal",
    message: String? = null
) : BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = errorCode,
        root = root,
        message = message
    )
)