package com.arash.altafi.cachemanagment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arash.altafi.cachemanagment.databinding.ActivityMainBinding
import com.arash.altafi.cachemanagment.model.EnumSample
import com.arash.altafi.cachemanagment.model.User
import com.arash.altafi.cachemanagment.utils.Cache
import com.arash.altafi.cachemanagment.utils.SharedPreferencesUtils
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

//        supportActionBar?.title = "Cache Management"
        supportActionBar?.hide()

        init()
    }

    private fun init() = binding.apply {
        //region SharedPreferences
        SharedPreferencesUtils.sharedPrefName(this@MainActivity)

        btnPutSharedPreferences.setOnClickListener {
            SharedPreferencesUtils.save("test string SharedPreferences")
            successFullySaved("String SharedPreferences Saved SuccessFully")
        }

        btnGetSharedPreferences.setOnClickListener {
            scrollUp()
            tvShow.text = SharedPreferencesUtils.get()
        }
        //endregion

        //region DataStore

        //region object data store
        btnPutObjectDataStore.setOnClickListener {
            val users = User(1, "arash", "altafi", 25)
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                cache.sampleObjectDataStore.putObject(users)
            }
            successFullySaved("Object DataStore Saved SuccessFully")
        }

        btnGetObjectDataStore.setOnClickListener {
            scrollUp()
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                tvShow.text = cache.sampleObjectDataStore.getObject<User>().toString()
            }
        }
        //endregion

        //region string data store
        btnPutStringDataStore.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                cache.sampleStringDataStore.put("test string")
            }
            successFullySaved("String DataStore Saved SuccessFully")
        }

        btnGetStringDataStore.setOnClickListener {
            scrollUp()
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                tvShow.text = cache.sampleStringDataStore.get()
            }
        }
        //endregion

        //region Int data store
        btnPutIntDataStore.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                cache.sampleIntDataStore.put(25)
            }
            successFullySaved("Int DataStore Saved SuccessFully")
        }

        btnGetIntDataStore.setOnClickListener {
            scrollUp()
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                tvShow.text = cache.sampleIntDataStore.get().toString()
            }
        }
        //endregion

        //region Long data store
        btnPutLongDataStore.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                cache.sampleLongDataStore.put(25)
            }
            successFullySaved("Long DataStore Saved SuccessFully")
        }

        btnGetLongDataStore.setOnClickListener {
            scrollUp()
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                tvShow.text = cache.sampleLongDataStore.get().toString()
            }
        }
        //endregion

        //region Boolean data store
        btnPutBooleanDataStore.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                cache.sampleBooleanDataStore.put(true)
            }
            successFullySaved("Boolean DataStore Saved SuccessFully")
        }

        btnGetBooleanDataStore.setOnClickListener {
            scrollUp()
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                tvShow.text = cache.sampleBooleanDataStore.get().toString()
            }
        }
        //endregion

        //endregion

        //region Reactor

        //region object AES reactor
        btnPutObjectAESReactor.setOnClickListener {
            val users = User(1, "arash", "altafi", 25)
            cache.sampleObjectAES = users
            successFullySaved("object AES Saved SuccessFully")
        }

        btnGetObjectAESReactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleObjectAES.toString()
        }
        //endregion

        //region object Base64 reactor
        btnPutObjectBase64Reactor.setOnClickListener {
            val users = User(1, "arash", "altafi", 25)
            cache.sampleObjectBas64 = users
            successFullySaved("object Base64 Saved SuccessFully")
        }

        btnGetObjectBase64Reactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleObjectBas64.toString()
        }
        //endregion

        //region Enum AES reactor
        btnPutEnumAESReactor.setOnClickListener {
            cache.sampleEnumAES = EnumSample.Sample2
            successFullySaved("Enum AES Saved SuccessFully")
        }

        btnGetEnumAESReactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleEnumAES.toString()
        }
        //endregion

        //region Enum Base64 reactor
        btnPutEnumBase64Reactor.setOnClickListener {
            cache.sampleEnumBase64 = EnumSample.Sample3
            successFullySaved("Enum Base64 Saved SuccessFully")
        }

        btnGetEnumBase64Reactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleEnumBase64.toString()
        }
        //endregion

        //region Array AES reactor
        btnPutArrayAESReactor.setOnClickListener {
            val arrayList: ArrayList<User> = arrayListOf()
            arrayList.add(User(1, "Arash 1", "Altafi 1", 25))
            arrayList.add(User(2, "Arash 2", "Altafi 2", 26))
            cache.sampleArrayListAES = arrayList
            successFullySaved("ArrayList AES Saved SuccessFully")
        }

        btnGetArrayAESReactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleArrayListAES.toString()
        }
        //endregion

        //region Array Base64 reactor
        btnPutArrayBase64Reactor.setOnClickListener {
            val arrayList: ArrayList<User> = arrayListOf()
            arrayList.add(User(1, "Arash 1", "Altafi 1", 25))
            arrayList.add(User(2, "Arash 2", "Altafi 2", 26))
            cache.sampleArrayListBase64 = arrayList
            successFullySaved("ArrayList Base64 Saved SuccessFully")
        }

        btnGetArrayBase64Reactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleArrayListBase64.toString()
        }
        //endregion

        //region string AES reactor
        btnPutStringAESReactor.setOnClickListener {
            cache.sampleStringAES = "test string AES"
            successFullySaved("String AES Saved SuccessFully")
        }

        btnGetStringAESReactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleStringAES
        }
        //endregion

        //region string Base64 reactor
        btnPutStringBase64Reactor.setOnClickListener {
            cache.sampleStringBase64 = "test string Base64"
            successFullySaved("String Base64 Saved SuccessFully")
        }

        btnGetStringBase64Reactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleStringBase64
        }
        //endregion

        //region int AES reactor
        btnPutIntAESReactor.setOnClickListener {
            cache.sampleIntAES = 25
            successFullySaved("Int AES Saved SuccessFully")
        }

        btnGetIntAESReactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleIntAES.toString()
        }
        //endregion

        //region int Base64 reactor
        btnPutIntBase64Reactor.setOnClickListener {
            cache.sampleIntBase64 = 25
            successFullySaved("Int Base64 Saved SuccessFully")
        }

        btnGetIntBase64Reactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleIntBase64.toString()
        }
        //endregion

        //region long AES reactor
        btnPutLongAESReactor.setOnClickListener {
            cache.sampleLongAES = 25
            successFullySaved("Long AES Saved SuccessFully")
        }

        btnGetLongAESReactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleLongAES.toString()
        }
        //endregion

        //region long Base64 reactor
        btnPutLongBase64Reactor.setOnClickListener {
            cache.sampleLongBase64 = 25
            successFullySaved("Long Base64 Saved SuccessFully")
        }

        btnGetLongBase64Reactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleLongBase64.toString()
        }
        //endregion

        //region boolean AES reactor
        btnPutBooleanAESReactor.setOnClickListener {
            cache.sampleBooleanAES = true
            successFullySaved("Boolean AES Saved SuccessFully")
        }

        btnGetBooleanAESReactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleBooleanAES.toString()
        }
        //endregion

        //region boolean Base64 reactor
        btnPutBooleanBase64Reactor.setOnClickListener {
            cache.sampleBooleanBase64 = true
            successFullySaved("Boolean Base64 Saved SuccessFully")
        }

        btnGetBooleanBase64Reactor.setOnClickListener {
            scrollUp()
            tvShow.text = cache.sampleBooleanBase64.toString()
        }
        //endregion

        //endregion

        //region EraseAll
        btnEraseAll.setOnClickListener {
            scrollUp()
            CoroutineScope(Dispatchers.Main).launch { //use Dispatchers.IO
                cache.eraseAllData()
            }
            SharedPreferencesUtils.clear()
            successFullySaved("Erase All Data SuccessFully")
        }
        //endregion
    }

    private fun successFullySaved(message: String) = binding.apply {
        tvShow.text = ""
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun scrollUp() {
        binding.nestedScrollView.scrollTo(0, 0)
    }

}