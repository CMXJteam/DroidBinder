package me.drborges.droidbinder.models

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

data class Pet(var name: String, var age: Int) : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putInt("age", age)
        dest?.writeBundle(bundle)
    }

    override fun describeContents() = 0
}
