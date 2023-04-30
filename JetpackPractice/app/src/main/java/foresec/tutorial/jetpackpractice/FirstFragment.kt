package foresec.tutorial.jetpackpractice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide


import foresec.tutorial.jetpackpractice.databinding.FragmentFirstBinding
import foresec.tutorial.jetpackpractice.dog.ApiService
import foresec.tutorial.jetpackpractice.dog.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toSecondBtn.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment2_to_secondFragment)
        }


        // Retrofit 객체를 생성하는 블록을 코루틴으로 처리
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            val response = apiService.getRandomDog().execute()

            // 응답을 처리하는 블록을 메인 스레드에서 실행하도록 처리
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val dog = response.body()
                    val dogImageUrl = dog?.message

                    // Glide를 사용하여 이미지 로드 및 설정
                    Glide.with(requireContext())
                        .load(dogImageUrl)
                        .into(binding.dogImageView)
                    Log.d("HERE!!!!!!!!!!!!!!", "Random dog image URL: ${dog?.message}")
                } else {
                    Log.e("HERE!!!!!!!!!!!!!!", "Failed to fetch random dog image. Response code: ${response.code()}")
                }
            }
        }

    }

}