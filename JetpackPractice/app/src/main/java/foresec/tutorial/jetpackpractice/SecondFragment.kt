package foresec.tutorial.jetpackpractice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import foresec.tutorial.jetpackpractice.databinding.FragmentSecondBinding
import foresec.tutorial.jetpackpractice.ph.Post
import foresec.tutorial.jetpackpractice.ph.PostViewModel


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: PostViewModel by viewModels()

    private lateinit var idEditText: EditText
    private lateinit var titleEditText: EditText
    private lateinit var bodyEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // EditText 바인딩
        idEditText = binding.idEditText
        titleEditText = binding.titleEditText
        bodyEditText = binding.bodyEditText

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toThirdBtn.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        binding.createPostBtn.setOnClickListener {
            val id = idEditText.text.toString().toInt()
            val title = titleEditText.text.toString()
            val body = bodyEditText.text.toString()
            viewModel.createPost(id, title, body)
            Log.d("엥 요청됐나", "됐냐")
        }

        viewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            binding.postText.text = posts[1].title
            binding.postBodyText.text = posts[1].body
            Log.d("what??", posts.toString())
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            // 에러 메시지 처리
        })

        viewModel.fetchPosts()



    }
}