package com.example.friends.ui.messenger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friends.R
import com.example.friends.adapter.AdapterPeople
import com.example.friends.databinding.ConversationToolbarBinding
import com.example.friends.databinding.FragmentConversationBinding

import com.example.friends.databinding.FragmentPeopleBinding
import com.example.friends.ui.messenger.ViewModelMessenger
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject


@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private val viewModel by viewModels<ViewModelMessenger>()


    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!




    @Inject
    lateinit var peopleAdapter: AdapterPeople


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayout = LinearLayoutManager(activity)
        binding.friendsRv.layoutManager=linearLayout



        viewModel.peopleAll.observe(viewLifecycleOwner) { people ->
            peopleAdapter.differ.submitList(people)
            binding.friendsRv.adapter = peopleAdapter
        }

        peopleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("user", it)
            }
            findNavController().navigate(
                R.id.action_peopleFragment_to_conversationFragment,
                bundle
            )
        }

    }
}