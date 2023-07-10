package com.sugiartha.juniorandroid;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity {
    TextView proximitySensor, data;
    ImageView statusImage;
    LinearLayout rootView;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Inisialisasi elemen UI
        proximitySensor = findViewById(R.id.proximitySensor);
        data = findViewById(R.id.data);
        statusImage = findViewById(R.id.statusImage);
        rootView = findViewById(R.id.rootView);

        // Inisialisasi SensorManager dan Proximity Sensor
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Periksa apakah Sensor Proximity didukung oleh perangkat
        if (myProximitySensor == null) {
            proximitySensor.setText("Sensor Proximity Tidak Terdeteksi!");
        } else {
            // Daftarkan listener untuk Sensor Proximity
            mySensorManager.registerListener(proximitySensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        // Inisialisasi MediaPlayer untuk memainkan suara notifikasi
        mediaPlayer = new MediaPlayer();
    }

    // Listener untuk Sensor Proximity
    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Metode yang dipanggil saat ada perubahan akurasi pada sensor
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                // Cek nilai proximity
                if (event.values[0] == 0) {
                    // Objek dalam jarak dekat
                    data.setText("Dekat");
                    showProximityToast("Objek Dekat!");
                    animateStatusImage(true);
                    statusImage.setImageResource(R.drawable.ic_status_near);
                    rootView.setBackgroundColor(Color.parseColor("#D3D3D3"));
                    playNotificationSound(R.raw.notification_sound_near); // Memainkan suara notifikasi untuk jarak dekat
                } else {
                    // Objek dalam jarak jauh
                    data.setText("Jauh");
                    showProximityToast("Objek Jauh!");
                    animateStatusImage(false);
                    statusImage.setImageResource(R.drawable.ic_status_far);
                    rootView.setBackgroundColor(Color.parseColor("#FFF8DC"));
                    playNotificationSound(R.raw.notification_sound_far); // Memainkan suara notifikasi untuk jarak jauh
                }
            }
        }
    };

    // Menampilkan toast dengan pesan proximity
    private void showProximityToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Animasi perubahan ukuran statusImage
    private void animateStatusImage(boolean isClose) {
        float targetScale = isClose ? 1.5f : 1.0f;
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(statusImage, "scaleX", targetScale);
        scaleAnimator.setDuration(500);
        scaleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimator.start();
    }

    // Memainkan suara notifikasi
    private void playNotificationSound() {
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // sumber daya MediaPlayer dan batalkan pendaftaran listener
        mediaPlayer.release();
        mySensorManager.unregisterListener(proximitySensorEventListener);
    }

    // Memainkan suara notifikasi berdasarkan resource ID yang diberikan
    private void playNotificationSound(int soundResourceId) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = new MediaPlayer();
            }

            mediaPlayer = MediaPlayer.create(this, soundResourceId);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}