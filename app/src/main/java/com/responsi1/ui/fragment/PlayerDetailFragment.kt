package com.responsi1.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.responsi1.R
import com.responsi1.data.model.Player

class PlayerDetailFragment : BottomSheetDialogFragment() {

    // Deklarasi View
    private lateinit var tvName: TextView
    private lateinit var tvDob: TextView
    private lateinit var tvNationality: TextView
    private lateinit var tvPosition: TextView
    private lateinit var topBorderView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvName = view.findViewById(R.id.tv_detail_name)
        tvDob = view.findViewById(R.id.tv_detail_dob)
        tvNationality = view.findViewById(R.id.tv_detail_nationality)
        tvPosition = view.findViewById(R.id.tv_detail_position)
        topBorderView = view.findViewById(R.id.top_border_view)

        val player = arguments?.getSerializable("EXTRA_PLAYER") as? Player
        val colorResId = arguments?.getInt("EXTRA_COLOR_RES_ID", R.color.arsenal_dark) ?: R.color.arsenal_dark
        if (player != null) {
            tvName.text = player.name
            tvDob.text = player.dateOfBirth ?: "N/A"
            tvNationality.text = player.nationality
            tvPosition.text = player.position
        }
        topBorderView.setBackgroundColor(ContextCompat.getColor(requireContext(), colorResId))
    }

    companion object {
        fun newInstance(player: Player, colorResId: Int): PlayerDetailFragment {
            val fragment = PlayerDetailFragment()
            val args = Bundle()
            args.putSerializable("EXTRA_PLAYER", player)
            args.putInt("EXTRA_COLOR_RES_ID", colorResId)
            fragment.arguments = args
            return fragment
        }
    }
}