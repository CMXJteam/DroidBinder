package me.drborges.droidbinder.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.view.View
import me.drborges.droidbinder.models.Pet
import me.drborges.droidbinder.observables.ResponsiveObservable

class NewPetViewModel() : BaseObservable() {

    val pet = Pet("", 0)
    val petName = ResponsiveObservable(pet.name) { pet.name = it }

    @Bindable
    val pets = ObservableArrayList<Pet>()

    fun addPet(view: View) {
        val newPet = pet.copy()
        pet.name = ""
        pet.age = 0
        petName.set("")
        pets.add(newPet)
    }
}