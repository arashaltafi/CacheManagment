package com.arash.altafi.cachemanagment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.cachemanagment.databinding.ActivityMainBinding
import com.arash.altafi.cachemanagment.model.User
import com.arash.altafi.cachemanagment.utils.Cache
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cache: Cache

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        btnPutObjectDataStore.setOnClickListener {
            val users = User(1, "arash", "altafi", 25)
            CoroutineScope(Dispatchers.IO).launch {
                cache.configData.putObject(users)
            }
            Toast.makeText(this@MainActivity, "Saved SuccessFully", Toast.LENGTH_SHORT).show()
        }

        btnGetObjectDataStore.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                tvShow.text = cache.configData.getObject<User>().toString()
            }
        }
    }

}