package foresec.tutorial.jetpackpractice.dog

import retrofit2.http.GET

interface ApiService {
    @GET("breeds/image/random")
    fun getRandomDog(): retrofit2.Call<Dog>
}