package org.baosp.clock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.speech.tts.TextToSpeech
import java.util.Locale

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        TextToSpeech(context) { tts ->
            if (tts == TextToSpeech.SUCCESS) {
                val t = android.speech.tts.TextToSpeech(context) { }
                t.language = Locale.getDefault()
                t.speak("Alarm ringing", android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
        val v = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        else @Suppress("DEPRECATION") context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val p = longArrayOf(0, 500, 500, 500, 500, 500)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) v.vibrate(VibrationEffect.createWaveform(p, 0))
        else @Suppress("DEPRECATION") v.vibrate(p, 0)
        RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))?.play()
    }
}
