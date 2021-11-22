package com.example.batukertasgunting

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.w3c.dom.Text

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {
    lateinit var ivBatu1: ImageView
    lateinit var ivKertas1: ImageView
    lateinit var ivGunting1: ImageView
    lateinit var ivBatu2: ImageView
    lateinit var ivKertas2: ImageView
    lateinit var ivGunting2: ImageView
    lateinit var ivReset: ImageView
    lateinit var ivHasil: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivBatu1 = findViewById(R.id.ivBatu1)
        ivKertas1 = findViewById(R.id.ivKertas1)
        ivGunting1 = findViewById(R.id.ivGunting1)
        ivBatu2 = findViewById(R.id.ivBatu2)
        ivKertas2 = findViewById(R.id.ivKertas2)
        ivGunting2 = findViewById(R.id.ivGunting2)
        ivReset = findViewById(R.id.ivReset)
        ivHasil = findViewById(R.id.ivHasil)

        val btnPemain = arrayOf(
            ivBatu1,
            ivKertas1,
            ivGunting1,
        )
        val btnKomputer = arrayOf(
            ivBatu2,
            ivKertas2,
            ivGunting2,
        )

        btnPemain.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                val hasilKomputer = btnKomputer.random()
                Log.d("${btnPemain[index].contentDescription}", "Pemain click")
                Log.d("$hasilKomputer", "Komputer Memilih")
                hasilKomputer.setBackgroundResource(R.drawable.btn_corner)
                oneClick(ivBatu1, ivKertas1, ivGunting1)
                cekSuit(btnPemain[index].contentDescription, hasilKomputer.contentDescription)
                Toast.makeText(
                    this, "${btnPemain[index].contentDescription}", Toast.LENGTH_SHORT
                ).show()
                btnPemain.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                btnPemain[index].setBackgroundResource(R.drawable.btn_corner)
            }
        }

        ivReset.setOnClickListener {
            Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
            Log.d("Reset", "Reset click")
            btnPemain.forEach {
                it.setBackgroundResource(android.R.color.transparent)
            }
            btnKomputer.forEach {
                it.setBackgroundResource(android.R.color.transparent)
            }
            ivHasil.setText(R.string.vs)
            bisaClick(ivBatu1, ivKertas1, ivGunting1)
        }
    }

    private fun cekSuit(pemain: CharSequence, komputer: CharSequence) {
        if (pemain == komputer) {
            Log.d("Draw", "Click")
            ivHasil.setText(R.string.seri)
            ivHasil.textSize = 32f
        } else if (pemain == ivBatu1.contentDescription && komputer == ivGunting2.contentDescription || pemain == ivKertas1.contentDescription && komputer == ivBatu2.contentDescription || pemain == ivGunting1.contentDescription && komputer == ivKertas2.contentDescription) {
            Log.d("pemain_menang", "click")
            ivHasil.setText(R.string.pemain_menang)
            ivHasil.textSize = 32f
        } else {
            Log.d("Hasil: ", "$pemain vs $komputer")
            ivHasil.setText(R.string.komputer_menang)
            ivHasil.textSize = 32f
        }
    }
// tes push
    private fun oneClick(image1: ImageView, image2: ImageView, image3: ImageView) {
        image1.isEnabled = false
        image2.isEnabled = false
        image3.isEnabled = false
    }

    private fun bisaClick(image1: ImageView, image2: ImageView, image3: ImageView) {
        image1.isEnabled = true
        image2.isEnabled = true
        image3.isEnabled = true
    }
}
