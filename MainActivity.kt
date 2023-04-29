package com.example.trashgame3

import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.content.Intent
import android.widget.Toast
import android.graphics.Bitmap
import android.widget.TextView
// import com.google.android.gms.maps.OnMapReadyCallback
import androidx.activity.result.contract.ActivityResultContracts

var tokenState = 0

class MainActivity : AppCompatActivity() {

    lateinit var trashImageView: ImageView
    lateinit var trashButton: Button
    val REQUEST_IMAGE_CAPTURE = 1;

    lateinit var binImageView: ImageView
    lateinit var binButton: Button
    val REQUEST_IMAGE_CAPTURE2 = 2;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trashImageView = findViewById(R.id.trash_image)
        trashButton = findViewById(R.id.trash_button)

        binImageView = findViewById(R.id.bin_image)
        binButton = findViewById(R.id.bin_button)

        val goToSecondActivityButton = findViewById<Button>(R.id.rewards_button)
        goToSecondActivityButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        trashButton.setOnClickListener {

            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }catch (e: ActivityNotFoundException) {
                Toast.makeText(this@MainActivity,"Error: " + e.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }

        binButton.setOnClickListener {

            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE2)
            }catch (e: ActivityNotFoundException) {
                Toast.makeText(this@MainActivity,"Error: " + e.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val imageBitmap = data?.extras?.get("data") as Bitmap
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            trashImageView.setImageBitmap(imageBitmap)
        }else if (requestCode == REQUEST_IMAGE_CAPTURE2 && resultCode == RESULT_OK){
            binImageView.setImageBitmap(imageBitmap)
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val reward1 = findViewById<Button>(R.id.button1)
        reward1.setOnClickListener {
            handleClick1()
        }
    }

    private fun handleClick1() {
        val tokenValue = findViewById<TextView>(R.id.tokenVal)
        tokenValue.text = (tokenValue.text.toString().toInt() - 5).toString()
        tokenState = tokenValue.text.toString().toInt()
    }
}

/*
class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maps)
    }
}*/
