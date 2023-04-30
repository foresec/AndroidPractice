package foresec.tutorial.jetpackpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


import foresec.tutorial.jetpackpractice.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using data binding
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        // Bind data to the view
        binding.lifecycleOwner = viewLifecycleOwner

        // Return the root view of the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind the views to the binding object
        binding.toSecondBtn.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment2_to_secondFragment)
        }
    }

}