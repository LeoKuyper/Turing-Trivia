package com.leokuyper.turingtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.leokuyper.turingtrivia.databinding.FragmentTitleBinding
import kotlinx.android.extensions.ContainerOptions

class TitleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_title, container, false)

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        binding.goHome.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment2_to_homeFragment)
        )
        return  binding.root
    }


}
