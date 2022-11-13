package com.example.taskandnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.to_botton_anim) }

    private var clicked = false

    lateinit var addBtn: FloatingActionButton
    lateinit var addNoteBtn: FloatingActionButton
    lateinit var addTaskBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBtn = findViewById(R.id.fabAddBtn)
        addNoteBtn = findViewById(R.id.fabAddNote)
        addTaskBtn = findViewById(R.id.fabAddTask)

        addBtn.setOnClickListener{
            onAddButtonClicked()
        }
        addNoteBtn.setOnClickListener{
            Toast.makeText(this, "Agregar Nota", Toast.LENGTH_LONG).show()
        }
        addTaskBtn.setOnClickListener{
            Toast.makeText(this, "Agregar Tarea", Toast.LENGTH_LONG).show()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        addNoteBtn = findViewById(R.id.fabAddNote)
        addTaskBtn = findViewById(R.id.fabAddTask)
        if (!clicked){
            addNoteBtn.visibility = View.VISIBLE
            addTaskBtn.visibility = View.VISIBLE
        }else{
            addNoteBtn.visibility = View.INVISIBLE
            addTaskBtn.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        addNoteBtn = findViewById(R.id.fabAddNote)
        addTaskBtn = findViewById(R.id.fabAddTask)
        if (!clicked){
            addNoteBtn.startAnimation(fromBottom)
            addTaskBtn.startAnimation(fromBottom)
            addBtn.startAnimation(rotateOpen)
        } else {
            addNoteBtn.startAnimation(toBottom)
            addTaskBtn.startAnimation(toBottom)
            addBtn.startAnimation(rotateClose)
        }
    }
}