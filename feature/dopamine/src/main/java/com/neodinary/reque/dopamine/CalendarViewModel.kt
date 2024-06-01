package com.neodinary.reque.dopamine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neodinary.reque.common.ui.base.BaseViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Random

class CalendarViewModel : BaseViewModel() {

    private val _dates = MutableLiveData<List<Date?>>()
    val dates: LiveData<List<Date?>> get() = _dates

    private val _monthYearText = MutableLiveData<String>()
    val monthYearText: LiveData<String> get() = _monthYearText

    private val _selectedDate = MutableLiveData<Date?>()
    val selectedDate: LiveData<Date?> get() = _selectedDate

    private val _todayDateText = MutableLiveData<String>()
    val todayDateText: LiveData<String> get() = _todayDateText

    private val _todayMissionText = MutableLiveData<String>()
    val todayMissionText: LiveData<String> get() = _todayMissionText

    private val _todayContentText = MutableLiveData<String>()
    val todayContentText: LiveData<String> get() = _todayContentText

    private val _todayDate = MutableLiveData<String>()
    val todayDate: LiveData<String> get() = _todayDate

    private val _checkText = MutableLiveData<String>()
    val checkText: LiveData<String> get() = _checkText

    private val calendar = Calendar.getInstance()

    private val randomMissions = listOf(
        "지금 연락처를 열어서\n5번째 사람에게 전화걸기",
        "오늘 하루 1시간 독서하기",
        "근처 공원 산책하기",
        "새로운 레시피로 요리하기",
        "친구에게 감사 메시지 보내기",
        "가족과 함께 저녁 식사하기",
        "명상 20분 하기",
        "운동 30분 하기",
        "나만의 창작물 만들기",
        "하루 계획 세우기"
    )

    private val randomContents = listOf(
        "나만의 행복을 찾는중",
        "자기 개발 중",
        "긍정적인 에너지 충전",
        "자기 사랑 실천",
        "작은 목표 달성 중",
        "감사하는 마음 키우기",
        "새로운 도전 시작",
        "성장하는 중",
        "마음의 평화 찾기",
        "즐거운 하루 만들기"
    )

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

    fun onDateSelected(date: Date?) {
        if (date != null) {
            _selectedDate.value = date
            updateTodayInfo(date)
        }
    }

    private fun updateTodayInfo(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dateFormat = SimpleDateFormat("MM월 dd일", Locale.getDefault())
        _todayDateText.value = dateFormat.format(date)

        val dateYearFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        _todayDate.value = dateYearFormat.format(date)

        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        //val month = calendar.get(Calendar.MONTH)

        when (dayOfMonth) {
            7 -> {
                _todayMissionText.value = "네이버 지도를 켜서\n'카페' 탭 10번째 장소에 방문해보기"
                _todayContentText.value = "1일 연속 성장중"
                _checkText.value = "완료"
            }
            8 -> {
                _todayMissionText.value = "유튜브를 켜서\n제일 첫번째 영상 보기"
                _todayContentText.value = "2일 연속 성장중"
                _checkText.value = "완료"
            }
            9 -> {
                _todayMissionText.value = "지금 연락처를 열어서\n5번째 사람에게 전화걸기"
                _todayContentText.value = "3일 연속 성장중"
                _checkText.value = "완료"
            }
            else -> {
                val random = Random()
                _checkText.value = "미완료"
                _todayMissionText.value = randomMissions[random.nextInt(randomMissions.size)]
                _todayContentText.value = randomContents[random.nextInt(randomContents.size)]
            }
        }
    }
}