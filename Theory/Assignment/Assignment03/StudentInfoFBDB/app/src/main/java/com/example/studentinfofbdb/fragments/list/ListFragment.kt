package com.example.studentinfofbdb.fragments.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.studentinfofbdb.NavigationHost
import com.example.studentinfofbdb.R
import com.example.studentinfofbdb.databinding.FragmentListBinding
import com.example.studentinfofbdb.fragments.add.AddFragment


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        // set up the toolbar
        (activity as AppCompatActivity).setSupportActionBar(binding.appBar)


        // floating action button
        binding.floatingActionButton.setOnClickListener {
            (activity as NavigationHost).navigateTo(AddFragment(), true)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}