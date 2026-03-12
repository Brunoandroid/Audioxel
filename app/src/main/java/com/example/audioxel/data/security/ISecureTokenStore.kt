package com.example.audioxel.data.security

interface ISecureTokenStore {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
    fun clearAccessToken()
}
