package me.drborges.droidbinder.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import me.drborges.droidbinder.BR
import me.drborges.droidbinder.R
import me.drborges.droidbinder.adapters.DataBoundRecyclerViewAdapter
import me.drborges.droidbinder.bindings.TwoWayDataBindings
import me.drborges.droidbinder.databinding.ActivityDataBinderBinding
import me.drborges.droidbinder.models.Pet
import me.drborges.droidbinder.viewmodels.NewPetViewModel

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = NewPetViewModel()
        viewModel.pets.add(Pet("Rex", 5))
        viewModel.pets.add(Pet("Dolly", 2))
        viewModel.pets.add(Pet("Puppy", 15))
        viewModel.pets.add(Pet("Scooby", 7))

        DataBindingUtil.setDefaultComponent { TwoWayDataBindings }
        val binding = DataBindingUtil.setContentView<ActivityDataBinderBinding>(this, R.layout.activity_data_binder)
        binding.viewModel = viewModel
        binding.petsList.layoutManager = LinearLayoutManager(this)
        binding.petsList.adapter = DataBoundRecyclerViewAdapter<Pet>(BR.pet, R.layout.pet_cell, viewModel.pets)
    }
}