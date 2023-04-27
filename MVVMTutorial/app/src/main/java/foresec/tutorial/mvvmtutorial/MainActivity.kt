package foresec.tutorial.mvvmtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import foresec.tutorial.mvvmtutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var upBtn: Button
    private lateinit var downBtn: Button

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.maindata = MainActivityData("UP", "DOWN",0 )


        upBtn = binding.upBtn
        downBtn = binding.downBtn

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.currentValue.observe(this, Observer {
            binding.numberText.text = it.toString()
        })
    }

    override fun onClick(view: View?) {
        when (view) {
            upBtn ->
                mainViewModel.updateValue(ActionType.UP)

            downBtn ->
                mainViewModel.updateValue(ActionType.DOWN)
        }
    }
}


