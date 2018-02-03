package com.example.hajiboot.web

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CustomerForm(
    @get:NotNull
    @get:Size(min = 1, max = 127)
    var firstName: String? = null,
    @get:NotNull
    @get:Size(min = 1, max = 127)
    var lastName: String? = null
)