package com.neodinary.reque.dopamine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neodinary.reque.common.ui.base.BaseViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarViewModel : BaseViewModel() {

    private val _dates = MutableLiveData<List<Date?>>()
    val dates: LiveData<List<Date?>> get() = _dates

    private val _monthYearText = MutableLiveData<String>()
    val monthYearText: LiveData<String> get() = _monthYearText

    private val _selectedDate = MutableLiveData<Date?>()
    val selectedDate: LiveData<Date?> get() = _selectedDate

    private val calendar = Calendar.getInstance()

    init {
        updateMonthYearText()
        loadDates()
    }

    private fun updateMonthYearText() {
        val dateFormat = SimpleDateFormat("yyyy년 MM월", Locale.getDefault())
        _monthYearText.value = dateFormat.format(calendar.time)
    }

    fun loadDates() {
        val dates = mutableListOf<Date?>()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val month = calendar.get(Calendar.MONTH)

        // 첫 날의 요일 계산 (일요일: 1, 월요일: 2, ...)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // 빈 칸 추가
        for (i in 1 until firstDayOfWeek) {
            dates.add(null)
        }

        // 날짜 추가
        while (calendar.get(Calendar.MONTH) == month) {
            dates.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        _dates.value = dates
        calendar.add(Calendar.MONTH, -1) // Reset calendar to the start of the month
    }

    fun previousMonth() {
        calendar.add(Calendar.MONTH, -1)
        updateMonthYearText()
        loadDates()
    }

    fun nextMonth() {
        calendar.add(Calendar.MONTH, 1)
        updateMonthYearText()
        loadDates()
    }

    fun onDateSelected(date: Date) {
        _selectedDate.value = date
    }
}