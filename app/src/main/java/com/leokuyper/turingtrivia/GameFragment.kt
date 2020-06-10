package com.leokuyper.turingtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.leokuyper.turingtrivia.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.score.text = "Score: $newScore"
        })

        viewModel.question.observe(viewLifecycleOwner, Observer { newQuestion ->
            binding.questionText.text = newQuestion.question
            val rGroup = binding.radioGroup2
            rGroup.removeAllViews()
            for ((Index, answer) in newQuestion.answers.withIndex()){
                val newRBtn =  createRadioBtn(answer, Index)
                rGroup.addView(newRBtn)
            }

        })

        binding.submitbutton.setOnClickListener{ view : View ->
            val id = binding.radioGroup2.checkedRadioButtonId
            viewModel.checkQuestion(id)
            Toast.makeText(context, "The current Id $id", Toast.LENGTH_SHORT ).show()

            if (viewModel.currentQuestion.value!! < viewModel.amountOfQuestions.value!! - 1){
                viewModel.updateQuestion(viewModel.currentQuestion.value ?: 0)
            } else {
                if (viewModel.score.value!! == viewModel.amountOfQuestions.value!! - 1){
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                }else{
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameLostFragment)
                }
            }
        }

        return binding.root
    }

    private fun createRadioBtn(answer: String, id: Int) : RadioButton{
        val rdb = RadioButton(context)
        rdb.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rdb.text = answer
        rdb.id = id
        return rdb
    }

}
