package foresec.tutorial.jetpackpractice


// FirstFragment: ViewModel을 사용하여 UI를 업데이트
// Glide를 사용하여 이미지를 설정,
// 개 이미지를 가져오는 데 실패하면 errorMessage LiveData에 실패 메시지가 저장

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import foresec.tutorial.jetpackpractice.databinding.FragmentFirstBinding
import foresec.tutorial.jetpackpractice.dog.DogViewModel

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: DogViewModel by viewModels()

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

        // 변화 관찰 중, 변화 시 해당 URL을 가져와 Glide로 이미지 설정
        viewModel.dogImage.observe(viewLifecycleOwner, Observer { imageUrl ->
            Glide.with(requireContext())
                .load(imageUrl)
                .into(binding.dogImageView)
        })

        // DogViewModel의 errorMessagfe 변화 관찰 중 변화가 일어나면 해당 에러 메시지 처리
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            // 에러 메시지 처리
        })

        // DogViewModel의 fetchRandomDogImage 함수를 호출해 랜덤한 개 이미지를 가져옴,
        // 이미지 URL이 변경되면 dogImage 변수가 update, 실패하면 errorMessage 변수가 업데이트
        viewModel.fetchRandomDogImage()
    }
}