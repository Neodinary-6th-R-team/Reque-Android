package com.neodinary.reque.dopamine

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.neodinary.reque.common.ui.base.BaseFragment
import com.neodinary.reque.dopamine.databinding.FragmentDopamineBinding
import java.util.Calendar
import java.util.Date

class DopamineFragment : BaseFragment<FragmentDopamineBinding, CalendarViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_dopamine

    override val viewModel: CalendarViewModel by viewModels()

    override fun initView() {
        binding.calendarRecyclerView.layoutManager = GridLayoutManager(context, 7) // 7 columns for days of the week

        val adapter = CalendarAdapter(viewModel)
        binding.calendarRecyclerView.adapter = adapter

        viewModel.dates.observe(viewLifecycleOwner, Observer { dates ->
            dates?.let {
                adapter.submitList(it)
            }
        })

        binding.btnPreviousMonth.setOnClickListener {
            viewModel.previousMonth()
        }

        binding.btnNextMonth.setOnClickListener {
            viewModel.nextMonth()
        }
    }

    override fun initDataBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initObserving() {
        // ViewModel 관찰 설정
    }
}