package com.example.signup

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class SignUpRequest(
    val userName: String,
    val mobileNo: String,
    val emailId: String,
    val city: String,
    val password: String
)

data class SignUpResponse(val success: Boolean, val message: String)

interface ApiService {
    @POST("api/save")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
}
