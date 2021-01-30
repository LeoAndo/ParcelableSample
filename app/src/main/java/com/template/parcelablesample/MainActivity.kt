package com.template.parcelablesample

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var user: User? = null

    /**
     * 実行結果ログ:
     *
     * 初回起動時:
     * W/MainActivity: user name: Hello Parcelable user age: 10
     * W/MainActivity: randomAge: 744174
     * W/MainActivity: user name: Hello Parcelable user age: 744174
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = if (savedInstanceState == null) {
            User(name = "Hello Parcelable", age = 10)
        } else {
            savedInstanceState.getParcelable<User>(KEY_USER)
        }
        user.apply {
            Log.w(TAG, "user name: " + this?.name + " user age: " + this?.age)
            text_1.text = "user name: " + this?.name + " age: " + this?.age
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val randomAge = Random.nextInt()
        Log.w(TAG, "randomAge: $randomAge")
        outState.putParcelable(KEY_USER, user?.copy(age = randomAge))
    }

    @Parcelize
    private data class User(
            val name: String,
            val age: Int
    ) : Parcelable

    companion object {
        const val TAG = "MainActivity"
        const val KEY_USER = "user"
    }
}
