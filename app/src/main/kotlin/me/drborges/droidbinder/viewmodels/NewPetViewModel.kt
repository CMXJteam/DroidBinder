package me.drborges.droidbinder.viewmodels

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import me.drborges.droidbinder.BR
import me.drborges.droidbinder.R
import me.drborges.droidbinder.adapters.RecyclerViewDataProvider
import me.drborges.droidbinder.models.Pet
import me.drborges.droidbinder.observables.ResponsiveObservable

class NewPetViewModel : BaseObservable(), Parcelable {
    val pet = Pet("", 0)
    val petName = ResponsiveObservable(pet.name) { pet.name = it }

    val pets = object : RecyclerViewDataProvider<Pet>() {
        override val itemBindID = BR.pet
        override val items = ObservableArrayList<Pet>()
        override fun itemViewType(position: Int) = 0
        override fun itemLayoutID(viewType: Int) = R.layout.pet_cell
    }

    fun addPet(view: View) {
        val newPet = pet.copy()
        pet.name = ""
        pet.age = 0
        petName.set("")
        pets.items.add(newPet)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        val bundle = Bundle()
        pet.writeToParcel(dest, flags)
        bundle.putParcelableArrayList("pets", pets.items.toArrayList())
        dest?.writeBundle(bundle)
    }

    override fun describeContents() = 0
}