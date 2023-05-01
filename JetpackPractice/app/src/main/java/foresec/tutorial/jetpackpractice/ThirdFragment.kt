package foresec.tutorial.jetpackpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import foresec.tutorial.jetpackpractice.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private lateinit var binding:FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // popBackStack은 기본적으로 특정 fragment로 이동할 경우, 이전의 모든 Fragment들이 stack에서 제거됨
        // true를 택할 경우 새롭게 그 fragment를 불러옴(초기화)
        // false를 택할 경우 이전에 stack에 넣어둔 fragment를 불러옴(초기화X)
        binding.homeBtn.setOnClickListener {
            findNavController().popBackStack(R.id.firstFragment, false)
        }
    }
}