package com.example.softwaredesign.week9.practice2

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.softwaredesign.R
import com.example.softwaredesign.databinding.Week9Ex2ActivityMainBinding
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: Week9Ex2ActivityMainBinding
    private var database: SQLiteDatabase? = null
    private val databaseName = "movie0"
    private val tableName = "movie_reserved"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Week9Ex2ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createDatabase()
        createTable()

        binding.savebutton.setOnClickListener { saveMovie() }
        binding.viewbutton.setOnClickListener { loadMovie() }
    }

    private fun createDatabase() {
        database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null)
        println("데이터베이스 생성 또는 오픈함")
    }

    private fun createTable() {

        if (database == null) return

        val sql = """
            CREATE TABLE IF NOT EXISTS $tableName (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                poster_image TEXT,
                director TEXT,
                synopsis TEXT,
                reserved_time TEXT
            )
        """.trimIndent()

        database!!.execSQL(sql)
        println("테이블 생성함\n")
        database!!.execSQL("DELETE FROM $tableName")
        println("앱 실행 시 기존 데이터 삭제함\n")
    }

    private fun saveMovie() {
        val posterImageUri = savePosterToFile(R.drawable.g)

        val name = binding.input1.text.toString()
        val director = binding.input2.text.toString()
        val synopsis = binding.input3.text.toString()
        val reservedTime = binding.input4.text.toString()
        val posterImage = posterImageUri.toString()

        addData(name, posterImage, director, synopsis, reservedTime)
    }

    private fun savePosterToFile(drawableRes: Int): Uri {
        val drawable = ContextCompat.getDrawable(applicationContext, drawableRes)
        val bitmap = (drawable as BitmapDrawable).bitmap

        val wrapper = ContextWrapper(applicationContext)
        val imagesFolder = wrapper.getDir("images", Context.MODE_PRIVATE)
        val file = File(imagesFolder, "dd.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // **요기 수정**
        return Uri.fromFile(file)
    }


    private fun addData(name: String, posterImage: String, director: String, synopsis: String, reservedTime: String) {
        if (database == null) {
            println("데이터베이스를 먼저 오픈하세요\n")
            return
        }

        val sql = "INSERT INTO $tableName (name, poster_image, director, synopsis, reserved_time) VALUES (?, ?, ?, ?, ?)"
        val params = arrayOf(name, posterImage, director, synopsis, reservedTime)
        database!!.execSQL(sql, params)
        println("데이터 추가함\n")
    }

    private fun loadMovie() {
        val movies = queryData()

        val intent = Intent(this, ReservedActivity::class.java)
        intent.putExtra("movies", movies)
        startActivity(intent)
    }

    private fun queryData(): ArrayList<ReservedMovie>? {
        if (database == null) {
            println("데이터베이스를 먼저 오픈하세요.\n")
            return null
        }

        val sql = "SELECT _id, name, poster_image, director, synopsis, reserved_time FROM $tableName"
        val list = ArrayList<ReservedMovie>()
        val cursor = database?.rawQuery(sql, null)

        cursor?.use {
            while (it.moveToNext()) {
                val movie = ReservedMovie(
                    _id = it.getInt(0),
                    name = it.getString(1),
                    poster_image = it.getString(2),
                    director = it.getString(3),
                    synopsis = it.getString(4),
                    reserved_time = it.getString(5)
                )
                println("레코드# ${it.position}: $movie")
                list.add(movie)
            }
        }

        println("데이터 조회함\n")
        return if (list.isEmpty()) null else list
    }

}
