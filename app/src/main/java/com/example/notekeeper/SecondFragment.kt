package com.example.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notekeeper.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.listNotes.adapter = ArrayAdapter(requireContext(),
                                    android.R.layout.simple_list_item_1,
                                    DataManager.notes)

        binding.listNotes.setOnItemClickListener { _, _, position, _ ->
            val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment(position)
            activity?.findNavController(R.id.nav_host_fragment_content_main)?.navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        (binding.listNotes.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}