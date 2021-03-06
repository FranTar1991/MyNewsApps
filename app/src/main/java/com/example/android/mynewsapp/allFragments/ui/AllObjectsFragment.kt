package com.example.android.mynewsapp.allFragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.mynewsapp.MyNewsApp
import com.example.android.mynewsapp.allFragments.viewModel.Factory
import com.example.android.mynewsapp.allFragments.viewModel.MyViewModel
import com.example.android.mynewsapp.databinding.FragmentAllObjectsBinding


class AllObjectsFragment : Fragment() {
    private val viewModel by viewModels<MyViewModel>{
        Factory((requireContext().applicationContext as MyNewsApp).repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentAllObjectsBinding.inflate(layoutInflater,container,false)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = AllObjectsAdapter(AllObjectsAdapter.OnObjectClickListener{
            viewModel.onNavigateToSingleArticleStarted(it)
        })

        viewModel.navigateToSingleArticle.observe(viewLifecycleOwner, Observer { domainObject ->
            domainObject?.let {
               // this.findNavController().navigate(R.id.action_allObjectsFragment_to_singleObjectFragment)
              Toast.makeText(context,"Touched ${it.title}",Toast.LENGTH_LONG).show()
                viewModel.onNavigateToSingleArticleComplete()
            }

        })



        return binding.root
    }


}