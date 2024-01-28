package com.example.todolistapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentAddToDoPopupBinding
import com.example.todolistapp.utils.ToDoData
import com.google.android.material.textfield.TextInputEditText

class AddToDoPopupFragment : DialogFragment() {

    private lateinit var binding: FragmentAddToDoPopupBinding
    private lateinit var listener: OnDialogNextBtnClickListener
    private var toDoData: ToDoData? = null

    fun setListener(listener: OnDialogNextBtnClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "AddToDoPopupFragment"

        @JvmStatic
        fun newInstance(taskId: String, task: String) = AddToDoPopupFragment().apply {
            arguments = Bundle().apply {
                putString("taskId", taskId)
                putString("task", task)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddToDoPopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            toDoData = ToDoData(
                arguments?.getString("taskId").toString(),
                arguments?.getString("task").toString(),
                selectedPriority
            )

            binding.todoEt.setText(toDoData?.task)
        }

        val prioritySpinner: Spinner = view.findViewById(R.id.prioritySpinner)
        val priorities = listOf(getString(R.string.choose_priority_hint), "Hign", "Medium", "Low")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        prioritySpinner.adapter = adapter

        prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedPriority = priorities[position]
            }
        }

        registerEvents()
    }

    private fun registerEvents() {
        binding.todoNextBtn.setOnClickListener {
            val todoTask = binding.todoEt.text.toString()

            if (todoTask.isNotEmpty()) {
                if(toDoData == null){
                    listener.onsaveTask(todoTask, binding.todoEt)
                }else{
                    toDoData?.task = todoTask
                    listener.onUpdateTask(toDoData!! , binding.todoEt)
                }
                listener.onsaveTask(todoTask, binding.todoEt)
            } else {
                Toast.makeText(context, "Please type your task", Toast.LENGTH_SHORT).show()
            }
        }

        binding.todoClose.setOnClickListener {
            dismiss()
        }
    }

    interface OnDialogNextBtnClickListener {
        fun onsaveTask(todo: String, todoEt: TextInputEditText)
        fun onUpdateTask(toDoData: ToDoData, todoEt: TextInputEditText)
    }

}