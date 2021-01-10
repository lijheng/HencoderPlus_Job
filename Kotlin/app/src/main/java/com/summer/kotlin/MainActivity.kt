package com.summer.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.summer.core.utils.CacheUtils
import com.summer.core.utils.Utils
import com.summer.kotlin.entity.User
import com.summer.kotlin.widget.CodeView
import com.summer.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val userNameKey = "username"
    private val passwordKey = "password"

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etCode = findViewById(R.id.et_code)

        etUsername.setText(CacheUtils.get(userNameKey))
        etPassword.setText(CacheUtils.get(passwordKey))

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val imgCode = findViewById<CodeView>(R.id.code_view)

        btnLogin.setOnClickListener(this)
        imgCode.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val code = etCode.text.toString()

        val user = User(username, password, code)
        if (verify(user)) {
            CacheUtils.save(userNameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean {
        //var length = user.username?.length ?: 0
        //if length  < 4
        if (user.username?.length ?: 0 < 4) {
            Utils.toast("用户名不合法")
            return false
        }
        if (user.password?.length ?: 0 < 4) {
            Utils.toast("密码不合法")
            return false
        }
        return true
    }
}