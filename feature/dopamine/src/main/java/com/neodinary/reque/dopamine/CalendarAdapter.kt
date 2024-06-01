package com.neodinary.reque.dopamine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neodinary.reque.dopamine.databinding.ItemCalendarDayBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarAdapter(private val viewModel: CalendarViewModel) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var dates: List<Date?> = listOf()

    init {
        viewModel.selectedDate.observeForever {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCalendarDayBinding.inflate(inflater, parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = dates[position]
        holder.bind(date, date == viewModel.selectedDate.value, View.OnClickListener {
            if (date != null) {
                viewModel.onDateSelected(date)
            }
        })
    }

    override fun getItemCount(): Int = dates.size

    fun submitList(dates: List<Date?>) {
        this.dates = dates
        notifyDataSetChanged()
    }

    class CalendarViewHolder(private val binding: ItemCalendarDayBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("d", Locale.getDefault())

        fun bind(date: Date?, isSelected: Boolean, clickListener: View.OnClickListener) {
            binding.date = date?.let { dateFormat.format(it) } ?: ""
            binding.isSelected = date != null && isSelected
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}