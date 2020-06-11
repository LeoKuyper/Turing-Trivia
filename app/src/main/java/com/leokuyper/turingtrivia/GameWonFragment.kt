package com.leokuyper.turingtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.leokuyper.turingtrivia.databinding.FragmentGameWonBinding
import com.leokuyper.turingtrivia.databinding.FragmentTitleBinding

class GameWonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game_won, container, false)

        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.goHome.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_gameWonFragment_to_titleFragment2)
        )
        return  binding.root
    }




}