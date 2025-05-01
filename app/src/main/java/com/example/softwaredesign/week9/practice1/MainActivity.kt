package com.example.softwaredesign.week9.practice1

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwaredesign.databinding.Week9Ex1ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: Week9Ex1ActivityMainBinding
    private var database: SQLiteDatabase? = null
    private val databaseName = "people"
    private val tableName = "student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding 초기화
        binding = Week9Ex1ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 리스너 설정
        binding.doButton1.setOnClickListener { createDatabase() }
        binding.doButton2.setOnClickListener { createTable() }
        binding.doButton3.setOnClickListener { addData() }
        binding.doButton4.setOnClickListener { updateData() }
        binding.doButton5.setOnClickListener { queryData() }
        binding.doButton6.setOnClickListener { deleteData() }
    }

    private fun createDatabase() {
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null)
        binding.output1.append("데이터베이스 생성 또는 오픈함\n")
    }

    private fun isDatabaseNotReady(): Boolean {
        if (database == null) {
            binding.output1.append("데이터베이스를 먼저 오픈하세요.\n")
            return true
        }
        return false
    }

    private fun createTable() {
        if (isDatabaseNotReady()) return
        database?.execSQL("DROP TABLE IF EXISTS $tableName")
        val sql = """
            CREATE TABLE IF NOT EXISTS $tableName (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                age INTEGER,
                mobile TEXT
            )
        """.trimIndent()
        database?.execSQL(sql)
        binding.output1.append("테이블 생성함\n")
    }

    private fun addData() {
        if (isDatabaseNotReady()) return
        val sql = "INSERT INTO $tableName (name, age, mobile) VALUES ('john', 20, '010-0000-0000')"
        database?.execSQL(sql)
        binding.output1.append("데이터 추가함\n")
    }

    private fun updateData() {
        if (isDatabaseNotReady()) return
        val values = ContentValues().apply {
            put("name", "mike")
            put("age", 24)
            put("mobile", "010-4000-4000")
        }
        val whereArgs = arrayOf("john")
        val result = database?.update(tableName, values, "name=?", whereArgs)
        binding.output1.append("데이터 갱신 (${result}건 변경됨)\n")
    }

    private fun queryData() {
        if (isDatabaseNotReady()) return
        val sql = "SELECT _id, name, age, mobile FROM $tableName"
        val cursor = database?.rawQuery(sql, null)

        if (cursor != null) {
            binding.output1.append("데이터 조회 결과\n")
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val mobile = cursor.getString(3)
                binding.output1.append("레코드#${cursor.position} : $id, $name, $age, $mobile\n")
            }
            cursor.close()
        }
    }

    private fun deleteData() {
        if (isDatabaseNotReady()) return

        val sql = """
        DELETE FROM $tableName 
        WHERE _id = (SELECT MAX(_id) FROM $tableName)
    """.trimIndent()
        database?.execSQL(sql)
        binding.output1.append("데이터 삭제함\n")
    }
}
