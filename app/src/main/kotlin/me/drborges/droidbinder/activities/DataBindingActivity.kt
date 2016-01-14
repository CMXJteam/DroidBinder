package me.drborges.droidbinder.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import me.drborges.droidbinder.R
import me.drborges.droidbinder.bindadapters.TwoWayDataBindings
import me.drborges.droidbinder.databinding.ActivityDataBinderBinding
import me.drborges.droidbinder.models.Pet
import me.drborges.droidbinder.viewmodels.NewPetViewModel

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setDefaultComponent { TwoWayDataBindings }

        val binding = DataBindingUtil.setContentView<ActivityDataBinderBinding>(this, R.layout.activity_data_binder)
        binding.viewModel = NewPetViewModel(Pet("Diego", 0))
        binding.petsList.layoutManager = LinearLayoutManager(this)
    }
}