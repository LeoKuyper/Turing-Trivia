package com.leokuyper.turingtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leokuyper.turingtrivia.data.Question
import com.leokuyper.turingtrivia.databinding.FragmentHomeBinding
import com.leokuyper.turingtrivia.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), OnCategoryCLickListener {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
//        binding.startGame.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_gameFragment)
//        )
        val categoryList = viewModel.generateDummyList(3)

        binding.categoryList.adapter = CategoryAdapter(viewModel.categories, this)
        binding.categoryList.layoutManager = LinearLayoutManager(context)
        binding.categoryList.setHasFixedSize(true)
        return  binding.root
    }

    override fun onCategoryClick(category: CategoryItem, position: Int, view: View) {
//        Toast.makeText(context, "Category ${category.text} with id ${category.id}", Toast.LENGTH_SHORT).show()
        viewModel.setupGame(category.id)
        view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
    }

}
