package com.example.pertemuan13

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan13.database.Pemilih
import com.example.pertemuan13.databinding.ItemPemilihBinding

class PemilihAdapter(
    private val onEditClick: (Pemilih) -> Unit,
    private val onDeleteClick: (Pemilih) -> Unit,
    private val onShowClick: (Pemilih) -> Unit
) : ListAdapter<Pemilih, PemilihAdapter.PemilihViewHolder>(PemilihDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemilihViewHolder {
        val binding = ItemPemilihBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PemilihViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PemilihViewHolder, position: Int) {
        val pemilih = getItem(position)
        holder.bind(pemilih, onEditClick, onDeleteClick, onShowClick)
    }

    class PemilihViewHolder(private val binding: ItemPemilihBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            pemilih: Pemilih,
            onEditClick: (Pemilih) -> Unit,
            onDeleteClick: (Pemilih) -> Unit,
            onShowClick: (Pemilih) -> Unit
        ) {
            binding.numberPemilih.text = pemilih.id.toString()
            binding.namePemilih.text = pemilih.nama

            // Set click listeners for each icon
            binding.deletePemilih.setOnClickListener {
                onDeleteClick(pemilih)
            }

            binding.editPemilih.setOnClickListener {
                onEditClick(pemilih)
            }

            binding.showPemilih.setOnClickListener {
                onShowClick(pemilih)
            }
        }
    }

    class PemilihDiffCallback : DiffUtil.ItemCallback<Pemilih>() {
        override fun areItemsTheSame(oldItem: Pemilih, newItem: Pemilih): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pemilih, newItem: Pemilih): Boolean {
            return oldItem == newItem
        }
    }
}