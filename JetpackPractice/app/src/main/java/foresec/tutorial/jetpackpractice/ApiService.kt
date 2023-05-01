package foresec.tutorial.jetpackpractice


// ApiService: 개 이미지를 가져오는 REST API를 호출하는 Retrofit 인터페이스
import foresec.tutorial.jetpackpractice.dog.Dog
import foresec.tutorial.jetpackpractice.ph.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    // Dog
    @GET("breeds/image/random")
    suspend fun getRandomDog(): Dog


    // ph
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>

//    @PUT("posts/{postId}")
//    @Headers("Content-Type: application/json")
//    suspend fun updatePost(@Path("postId") postId: Int, @Body post: Post): Post
//
//    @DELETE("posts/{postId}")
//    suspend fun deletePost(@Path("postId") postId: Int)
}