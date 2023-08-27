package com.scimbosh.simplespringbackend.exception

import org.springframework.http.HttpStatus

class EventNotFoundException(id: Int) : BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "id.not.found",
        description = "Event not found with id=$id"
    )
)