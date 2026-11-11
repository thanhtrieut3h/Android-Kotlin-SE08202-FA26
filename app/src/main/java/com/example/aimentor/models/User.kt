package com.example.aimentor.models

data class User (
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
    val role: Int,
    val statusUser: Int,
    val createdAt: String,
    val updatedAt: String
)