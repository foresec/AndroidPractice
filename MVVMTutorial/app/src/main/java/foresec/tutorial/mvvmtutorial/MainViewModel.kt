package foresec.tutorial.mvvmtutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType{
    UP, DOWN
}

data class MainActivityData (val upBtn: String, val downBtn:String, var currentValue: Int)

// 데이터의 변경사항을 알려주는 라이브 데이터를 가지는 뷰모델
class MainViewModel : ViewModel() {


    // 변경가능한 Mutable 타입의 LiveData
    private val _currentValue = MutableLiveData<Int>()

    // 무결성을 위한 Getter
    val currentValue : LiveData<Int>
        get() = _currentValue

    // 초기값
    init{
        _currentValue.value = 0
    }

    // Setter
    fun updateValue(type : ActionType){
        when(type){
            ActionType.UP ->
                _currentValue.value = _currentValue.value?.plus(1)
            ActionType.DOWN ->
                _currentValue.value = _currentValue.value?.minus(1)
        }
    }
}