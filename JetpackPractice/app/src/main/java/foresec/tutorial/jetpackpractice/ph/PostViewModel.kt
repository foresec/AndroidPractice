package foresec.tutorial.jetpackpractice.ph

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class PostViewModel : ViewModel() {
    private val repository = ApiRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = repository.getPosts()
                Log.d("PostViewModel", "요청왔다!!!!1")


            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch posts"
            }
        }
    }

    fun createPost(id: Int, title: String, body: String) {
        viewModelScope.launch {
            try {
                val post = Post(id, 2, title, body)
                repository.createPost(post)
                Log.d("PPPPPPPPPPost!", "보냈다22222222222222")
                fetchPosts()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to create post"
            }
        }
    }

}
