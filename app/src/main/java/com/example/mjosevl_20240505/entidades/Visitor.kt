package com.example.mjosevl_20240505.entidades

data class Visitor(
    val rut: String,
    val firstName: String,
    val lastName: String,
    val entryTime: Long,
    var exitTime: Long?,
    val apartment: String
)
