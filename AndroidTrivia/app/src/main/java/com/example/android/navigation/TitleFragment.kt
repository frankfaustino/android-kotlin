package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
        binding.playButton.setOnClickListener { view: View ->
            view.findNavController()
                    .navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        binding.rulesButton.setOnClickListener { view: View -> view.findNavController().navigate(R.id.action_titleFragment_to_rulesFragment) }
        binding.aboutButton.setOnClickListener { view: View -> view.findNavController().navigate(R.id.action_titleFragment_to_aboutFragment) }

        // Indicates that this fragment wants to populate the options menu
        // https://developer.android.com/reference/android/support/v4/app/Fragment#sethasoptionsmenu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // Inflate the menu resource file
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}