package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.notifications.NotificationReceiver;
import com.example.myapplication.viewModel.MainActivityViewModel;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    Button btnLogin, btnSignup;

    public static final String notiId8h = "notiId8h";

    private AlarmManager notification11;
    private PendingIntent pendingIntent;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainActivityViewModel(this);
        btnLogin = findViewById(R.id.button2);
        btnSignup = findViewById(R.id.button);
        setAlarm();
        createNotificationChannel();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.login();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signup();
            }
        });
    }
    private void setAlarm() {

        notification11 = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8); // Thiết lập giờ kích hoạt
        calendar.set(Calendar.MINUTE, 25); // Thiết lập phút kích hoạt
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        notification11.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.chanel_name);
            String description = getString(R.string.chanel_de);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(notiId8h, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}