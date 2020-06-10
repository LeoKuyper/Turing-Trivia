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
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.startGame.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_gameFragment)
        )
        val categoryList = generateDummyList(10)
        binding.categoryList.adapter = CategoryAdapter(categoryList, this)
        binding.categoryList.layoutManager = LinearLayoutManager(context)
        binding.categoryList.setHasFixedSize(true)
        return  binding.root
    }

    private  fun generateDummyList(size: Int): List<CategoryItem> {
        val list = ArrayList<CategoryItem>()
        for (i in 1 until size){
            val item = CategoryItem(i,"Category $i")
            list += item
        }
        return list
    }

    override fun onCategoryClick(category: CategoryItem, position: Int, view: View) {
        Toast.makeText(context, "Category ${category.text} with id ${category.id}", Toast.LENGTH_SHORT).show()
    }

}
