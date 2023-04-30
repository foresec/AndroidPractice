package foresec.tutorial.jetpackpractice.dog


// DogRepository: ApiService 인터페이스를 구현하여 개 이미지를 가져오는 클래스
// suspend 키워드를 사용하여 코루틴을 이용한 비동기 작업을 구현
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Dog API와 통신을 하는 Repository 클래스
class DogRepository {

    // Retrofit 라이브러리를 사용하여 ApiService 인터페이스 구현체를 생성
    private val apiService: ApiService = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/") // API의 base URL 설정
        .addConverterFactory(GsonConverterFactory.create()) // GsonConverter 사용하여 JSON 응답을 Kotlin 객체로 변환
        .build()
        .create(ApiService::class.java)

    // suspend 함수로 정의된 getRandomDog() 함수
    // ViewModel에서 이 함수를 호출하면 코루틴을 사용하여 비동기적으로 REST API를 호출하여 결과를 반환
    // getRandomDog() 함수가 완료될 때까지 ViewModel의 다른 코드는 block되지 않고 계속 실행됨
    suspend fun getRandomDog(): Dog {
        return apiService.getRandomDog() // ApiService 인터페이스에서 랜덤 개 이미지를 받아오는 REST API 호출하여 반환
    }
}
