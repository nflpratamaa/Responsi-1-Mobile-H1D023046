package com.responsi1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.responsi1.R
import com.responsi1.data.model.Player
import com.responsi1.ui.fragment.PlayerDetailFragment

class SquadAdapter(private val playerList: List<Player>) :
    RecyclerView.Adapter<SquadAdapter.PlayerViewHolder>() {
    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardRoot: CardView = itemView.findViewById(R.id.card_player_root)
        val tvPlayerName: TextView = itemView.findViewById(R.id.tv_player_name)
        val tvPlayerNationality: TextView = itemView.findViewById(R.id.tv_player_nationality)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }


    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.tvPlayerName.text = player.name
        holder.tvPlayerNationality.text = player.nationality
        val context = holder.itemView.context
        val (cardColorResId, textColorResId) = when (player.position.trim()) {
            // 1. Kategori Goalkeeper (Kuning)
            "Goalkeeper" -> Pair(R.color.colorGK, R.color.black)
            // 2. Kategori Defender (Biru)
            "Defence", "Left-Back", "Centre-Back", "Right-Back" -> Pair(
                R.color.colorDF,
                R.color.white
            )
            // 3. Kategori Midfielder (Hijau)
            "Midfield", "Central Midfield", "Defensive Midfield", "Right Midfield", "Attacking Midfield", "AMF" -> Pair(
                R.color.colorMF,
                R.color.white
            )

            // 4. Kategori Forward (Merah)
            "Offence", "Centre-Forward", "Right Winger", "Left Winger", "Forward", "Attacker" -> Pair(
                R.color.colorFW,
                R.color.white
            )
            // Warna default
            else -> Pair(R.color.white, R.color.black)
        }

        holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(context, cardColorResId))
        holder.tvPlayerName.setTextColor(ContextCompat.getColor(context, textColorResId))
        holder.tvPlayerNationality.setTextColor(ContextCompat.getColor(context, textColorResId))
        holder.itemView.setOnClickListener {
            val clickedPlayer = playerList[position]
            val fragment = PlayerDetailFragment.newInstance(clickedPlayer, cardColorResId)
            val fragmentManager =
                (holder.itemView.context as? AppCompatActivity)?.supportFragmentManager

            if (fragmentManager != null) {
                fragment.show(fragmentManager, "PlayerDetailFragmentTag")
            }
        }
    }
}