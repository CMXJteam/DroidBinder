package me.drborges.droidbinder.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import me.drborges.droidbinder.R
import me.drborges.droidbinder.bindings.TwoWayDataBindings
import me.drborges.droidbinder.databinding.ActivityDataBinderBinding
import me.drborges.droidbinder.viewmodels.NewPetViewModel

class DataBindingActivity : AppCompatActivity() {

    private var viewModel = NewPetViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            val vm = savedInstanceState.getParcelable<NewPetViewModel>("viewmodel")
            vm?.let {
                viewModel = vm
            }
        }

        DataBindingUtil.setDefaultComponent { TwoWayDataBindings }
        val binding = DataBindingUtil.setContentView<ActivityDataBinderBinding>(this, R.layout.activity_data_binder)
        binding.viewModel = viewModel
        binding.petsList.layoutManager = LinearLayoutManager(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable("viewmodel", viewModel)
        super.onSaveInstanceState(outState)
    }
}