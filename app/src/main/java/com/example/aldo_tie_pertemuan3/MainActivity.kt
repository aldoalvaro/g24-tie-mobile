package com.example.aldo_tie_pertemuan3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aldo_tie_pertemuan3.Pertemuan4.FourthActivity
import com.example.aldo_tie_pertemuan3.Pertemuan5.FifthActivity
import com.example.aldo_tie_pertemuan3.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnToFourth.setOnClickListener {
            val i = Intent(this@MainActivity, FourthActivity::class.java)
            i.putExtra("name", "Politeknik Caltex Riau")
            i.putExtra("from", "Rumbai")
            i.putExtra("age", 25)
            startActivity(i)
        }
        binding.btnP5.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }

        // Akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus semua data sharedPreferences (isLogin, username)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // Dinonaktifkan: otomatis membuka FifthActivity setiap MainActivity dibuka
        // (mengganggu alur Splash -> Auth -> Main). Tombol "Pertemuan5" tetap berfungsi.
        // val i = Intent(this@MainActivity, FifthActivity::class.java)
        // startActivity(i)
    }
}