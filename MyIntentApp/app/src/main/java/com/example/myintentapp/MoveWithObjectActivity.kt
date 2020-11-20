package com.example.myintentapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MoveWithObjectActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObject:TextView = findViewById(R.id.tv_object_received)

        val person = intent.getParceLableExtra(EXTRA_PERSON) as Person
        val tet = "Name : ${person.name.toString()}, \nEmail : ${person.email}, \nAge ${person.age}, \nLocation${person.city}"
        tvObject.text = text
    }
}