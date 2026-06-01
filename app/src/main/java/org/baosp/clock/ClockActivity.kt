package org.baosp.clock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Chronometer
import android.widget.NumberPicker
import android.widget.TabHost
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class ClockActivity : AppCompatActivity() {

    private var tts: TextToSpeech? = null
    private var timer: CountDownTimer? = null
    private var isTimerRunning = false
    private var isStopwatchRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)
        tts = TextToSpeech(this) { if (it == TextToSpeech.SUCCESS) tts?.language = Locale.getDefault() }
        val host = findViewById<TabHost>(R.id.tab_host); host.setup()
        host.addTab(host.newTabSpec("alarm").setIndicator(getString(R.string.tab_alarm)).setContent(R.id.tab_alarm))
        host.addTab(host.newTabSpec("timer").setIndicator(getString(R.string.tab_timer)).setContent(R.id.tab_timer))
        host.addTab(host.newTabSpec("stopwatch").setIndicator(getString(R.string.tab_stopwatch)).setContent(R.id.tab_stopwatch))

        val hp = findViewById<NumberPicker>(R.id.picker_hour).apply { minValue = 0; maxValue = 23; value = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
        val mp = findViewById<NumberPicker>(R.id.picker_minute).apply { minValue = 0; maxValue = 59; value = Calendar.getInstance().get(Calendar.MINUTE) + 1; if (value > 59) value = 0 }
        findViewById<Button>(R.id.btn_set_alarm).setOnClickListener { setAlarm(hp.value, mp.value) }
        findViewById<Button>(R.id.btn_dismiss).setOnClickListener { dismissAlarm() }
        findViewById<Button>(R.id.btn_start_timer).setOnClickListener { toggleTimer() }
        findViewById<Button>(R.id.btn_start_stopwatch).setOnClickListener { toggleStopwatch() }
        findViewById<Button>(R.id.btn_lap).setOnClickListener { findViewById<Chronometer>(R.id.stopwatch_display)?.let { speak("Lap: ${it.text}") } }
        findViewById<Button>(R.id.btn_reset_stopwatch).setOnClickListener { resetStopwatch() }
        updateClock()
    }

    private fun updateClock() {
        findViewById<TextView>(R.id.tv_current_time)?.let {
            val c = Calendar.getInstance()
            val t = String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))
            it.text = t; it.contentDescription = getString(R.string.current_time, t)
            it.postDelayed({ updateClock() }, 30000)
        }
    }

    private fun setAlarm(hour: Int, minute: Int) {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour); set(Calendar.MINUTE, minute); set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) add(Calendar.DAY_OF_MONTH, 1)
        }
        val pi = PendingIntent.getBroadcast(this, 0, Intent(this, AlarmReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        (getSystemService(Context.ALARM_SERVICE) as AlarmManager).setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pi)
        speak(getString(R.string.alarm_set, hour, minute))
    }

    private fun dismissAlarm() {
        val pi = PendingIntent.getBroadcast(this, 0, Intent(this, AlarmReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        (getSystemService(Context.ALARM_SERVICE) as AlarmManager).cancel(pi)
        speak(getString(R.string.alarm_off))
    }

    private fun toggleTimer() {
        if (isTimerRunning) { timer?.cancel(); isTimerRunning = false; findViewById<Button>(R.id.btn_start_timer).text = getString(R.string.start_timer); speak(getString(R.string.stop_timer)); return }
        val total = listOf(
            findViewById<NumberPicker>(R.id.picker_timer_hours).value * 3600000L,
            findViewById<NumberPicker>(R.id.picker_timer_minutes).value * 60000L,
            findViewById<NumberPicker>(R.id.picker_timer_seconds).value * 1000L
        ).sum()
        if (total <= 0) { speak("Set a time"); return }
        val ch = findViewById<Chronometer>(R.id.timer_display)
        ch.base = SystemClock.elapsedRealtime() + total; ch.start()
        timer = object : CountDownTimer(total, 1000) {
            override fun onTick(m: Long) { val s = m / 1000; ch.text = String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, s % 60) }
            override fun onFinish() { ch.stop(); isTimerRunning = false; findViewById<Button>(R.id.btn_start_timer).text = getString(R.string.start_timer); speak(getString(R.string.timer_done)) }
        }.start()
        isTimerRunning = true; findViewById<Button>(R.id.btn_start_timer).text = getString(R.string.stop_timer)
    }

    private fun toggleStopwatch() {
        val ch = findViewById<Chronometer>(R.id.stopwatch_display)
        if (isStopwatchRunning) { ch.stop(); isStopwatchRunning = false } else { ch.base = SystemClock.elapsedRealtime(); ch.start(); isStopwatchRunning = true }
    }

    private fun resetStopwatch() {
        findViewById<Chronometer>(R.id.stopwatch_display).apply { stop(); base = SystemClock.elapsedRealtime(); text = "00:00" }
        isStopwatchRunning = false; speak("Reset")
    }

    private fun speak(text: String) { tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null) }

    override fun onDestroy() { tts?.stop(); tts?.shutdown(); super.onDestroy() }
}
