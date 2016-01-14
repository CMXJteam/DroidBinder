package me.drborges.droidbinder.viewmodels

import me.drborges.droidbinder.models.Pet
import me.drborges.droidbinder.observables.ResponsiveObservable

class NewPetViewModel(val pet: Pet) {
    val petName = ResponsiveObservable(pet.name) { pet.name = it }
}