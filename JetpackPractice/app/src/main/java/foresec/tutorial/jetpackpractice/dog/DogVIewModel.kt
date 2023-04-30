package foresec.tutorial.jetpackpractice.dog


// DogViewModel: DogRepository 클래스의 getRandomDog() 메서드를 호출하여 이미지를 가져옴
// 또한 LiveData를 사용하여 UI에 결과값을 전달하고, 코루틴을 이용한 비동기 작업을 구현

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



class DogViewModel : ViewModel() {

    // 개 이미지를 저장하기 위한 MutableLiveData 객체 생성
    private val _dogImage = MutableLiveData<String>()
    val dogImage: LiveData<String>
        get() = _dogImage

    // 에러 메시지를 저장하기 위한 MutableLiveData 객체 생성
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    // DogRepository 객체 생성
    private val repository = DogRepository()

    // 랜덤 개 이미지를 가져오는 메서드
    fun fetchRandomDogImage() {
        // 코루틴 블록 생성
        viewModelScope.launch {
            try {
                // DogRepository에서 getRandomDog() 메서드 호출하여 결과값을 가져옴
                val dog = repository.getRandomDog()
                // 가져온 결과값을 _dogImage에 저장
                _dogImage.value = dog.message
            } catch (e: Exception) {
                // 에러 발생 시 에러 메시지를 _errorMessage에 저장
                _errorMessage.value = "Failed to fetch random dog image"
            }
        }
    }
}
