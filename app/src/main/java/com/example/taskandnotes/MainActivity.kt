package com.example.taskandnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.taskandnotes.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy{ AnimationUtils.loadAnimation(this, R.anim.to_botton_anim) }

    private var clicked = false
    private var lastClicked = R.id.btnNotes

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddBtn.setOnClickListener{
            onAddButtonClicked()
        }
        binding.fabAddNote.setOnClickListener{
            Toast.makeText(this, "Agregar Nota", Toast.LENGTH_LONG).show()
        }
        binding.fabAddTask.setOnClickListener{
            Toast.makeText(this, "Agregar Tarea", Toast.LENGTH_LONG).show()
        }

        binding.tglBtnGrp.check(R.id.btnNotes)

        binding.tglBtnGrp.addOnButtonCheckedListener { tglBtnGrp, checkedId, isChecked ->
            if(isChecked){
                when(checkedId){
                    R.id.btnNotes -> { Toast.makeText(this,"Aqui se muestran las notas",Toast.LENGTH_SHORT).show()
                    lastClicked = R.id.btnNotes }
                    R.id.btnTask -> { Toast.makeText(this,"Aqui se muestran las tareas",Toast.LENGTH_SHORT).show()
                    lastClicked = R.id.btnTask }
                }
            }else{
                if(tglBtnGrp.checkedButtonId == View.NO_ID){
                    tglBtnGrp.check(lastClicked)
                }
            }
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked){
            binding.fabAddNote.visibility = View.VISIBLE
            binding.fabAddTask.visibility = View.VISIBLE
        }else{
            binding.fabAddNote.visibility = View.INVISIBLE
            binding.fabAddTask.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked){
            binding.fabAddNote.startAnimation(fromBottom)
            binding.fabAddTask.startAnimation(fromBottom)
            binding.fabAddBtn.startAnimation(rotateOpen)
        } else {
            binding.fabAddNote.startAnimation(toBottom)
            binding.fabAddTask.startAnimation(toBottom)
            binding.fabAddBtn.startAnimation(rotateClose)
        }
    }
}