package com.example.roomdatabasedemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val etName = findViewById<EditText>(R.id.et_name)
        val etAge = findViewById<EditText>(R.id.et_age)
        val btnAdd = findViewById<Button>(R.id.btn_add)
        val btnUpdate = findViewById<Button>(R.id.btn_update)
        val btnDelete = findViewById<Button>(R.id.btn_delete)
        val svSearch = findViewById<SearchView>(R.id.sv_search)
        val rvUsers = findViewById<RecyclerView>(R.id.rv_users)

        val userAdapter = UserAdapter()
        rvUsers.adapter = userAdapter
        rvUsers.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val user = User(name = name, age = age)

            userViewModel.insert(user)
            etName.text.clear()
            etAge.text.clear()
            viewUsers(userAdapter)
        }

        btnUpdate.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()

            userViewModel.updateUser(name, age)
            etName.text.clear()
            etAge.text.clear()
            viewUsers(userAdapter)
        }

        btnDelete.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()

            userViewModel.deleteUser(name, age)
            etName.text.clear()
            etAge.text.clear()
            viewUsers(userAdapter)
        }

        svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    userViewModel.searchByName(newText.toString()).observe(this@MainActivity) {
                        users -> userAdapter.submitList(users)
                    }
                }
                else {
                    viewUsers(userAdapter)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        viewUsers(userAdapter)
    }

    private fun viewUsers(adapter: UserAdapter) {
        userViewModel.getAllUsers().observe(this) {
            users -> adapter.submitList(users)
        }
    }
}