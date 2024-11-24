package com.example.pertemuan13

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pertemuan13.database.PemilihDatabase
import com.example.pertemuan13.databinding.ActivityShowBinding
import kotlinx.coroutines.launch

class ShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding
    private lateinit var pemilihDatabase: PemilihDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pemilihDatabase = PemilihDatabase.getDatabase(this)

        val pemilihId = intent.getIntExtra("pemilih_id", -1)

        if (pemilihId != -1) {
            loadPemilihData(pemilihId)
        } else {
            binding.showNama.text = "Pemilih tidak ditemukan"
        }

        binding.btnKembali.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadPemilihData(pemilihId: Int) {
        lifecycleScope.launch {
            val pemilih = pemilihDatabase.pemilihDao().getPemilihById(pemilihId)
            if (pemilih != null) {
                binding.showNama.text = "Name: ${pemilih.nama}"
                binding.showNik.text = "NIK: ${pemilih.nik}"
                binding.showGender.text = "Gender: ${pemilih.gender}"
                binding.showAlamat.text = "Alamat: ${pemilih.alamat}"
            } else {
                binding.showNama.text = "Pemilih tidak ditemukan"
            }
        }
    }
}