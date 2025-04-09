package com.example.softwaredesign.week6.practice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.softwaredesign.R
import com.example.softwaredesign.databinding.Week6Ex2ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val MENU_VOLLEY_ID = 1
        const val MENU_RETROFIT_ID = 2
    }

    lateinit var binding: Week6Ex2ActivityMainBinding
    lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    var mode = "volley"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Week6Ex2ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyFragment = VolleyFragment()
        retrofitFragment = RetrofitFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, volleyFragment)
            .commit()
        supportActionBar?.title = "Volley Test"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // XML 없이 메뉴를 프로그래밍적으로 추가합니다.
        menu?.add(Menu.NONE, MENU_VOLLEY_ID, Menu.NONE, "Volley")?.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        menu?.add(Menu.NONE, MENU_RETROFIT_ID, Menu.NONE, "Retrofit")?.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
        return true  // true를 반환하면 메뉴가 정상적으로 표시됩니다.
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // volley mode 선택 시
        if (item.itemId == MENU_VOLLEY_ID && mode != "volley") {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, volleyFragment)
                .commit()
            mode = "volley"
            supportActionBar?.title = "Volley Test"
        }
        // retrofit mode 선택 시
        else if (item.itemId == MENU_RETROFIT_ID && mode != "retrofit") {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, retrofitFragment)
                .commit()
            mode = "retrofit"
            supportActionBar?.title = "Retrofit Test"
        }
        return super.onOptionsItemSelected(item)
    }
}
