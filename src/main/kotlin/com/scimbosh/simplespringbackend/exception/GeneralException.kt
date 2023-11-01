package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class GeneralException(
    errorCode: String? = "internal.error",
    cause: String? = null,
    message: String? = null,
    httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
) : BaseException(
    httpStatus = httpStatus,
    ApiError(
        errorCode = errorCode,
        cause = cause,
        message = message
    )
)