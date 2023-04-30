package foresec.tutorial.jetpackpractice.dog


// ApiService: 개 이미지를 가져오는 REST API를 호출하는 Retrofit 인터페이스
import retrofit2.http.GET

interface ApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDog():Dog
}