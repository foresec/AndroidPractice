package foresec.tutorial.jetpackpractice.ph

import foresec.tutorial.jetpackpractice.ApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    private val apiService: ApiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }


    suspend fun createPost(post: Post): Response<Post> {
        return apiService.createPost(post)
    }

//    suspend fun updatePost(postId: Int, post: Post): Post {
//        return apiService.updatePost(postId, post)
//    }
//
//    suspend fun deletePost(postId: Int) {
//        apiService.deletePost(postId)
//    }
}
