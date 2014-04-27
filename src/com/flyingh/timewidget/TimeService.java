package com.flyingh.timewidget;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class TimeService extends Service {

	protected static final int REQUEST_CODE = 0;
	private ScheduledExecutorService scheduledExecutorService;

	@Override
	public void onCreate() {
		super.onCreate();
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				RemoteViews views = new RemoteViews(getPackageName(), R.layout.time_widget_layout);
				views.setTextViewText(R.id.date_time, String.format("%1$tF %1$tT", Calendar.getInstance()));
				views.setOnClickPendingIntent(R.id.date_time, PendingIntent.getActivity(getApplicationContext(),
						REQUEST_CODE, new Intent(android.provider.Settings.ACTION_DATE_SETTINGS), 0));
				AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(
						new ComponentName(getApplicationContext(), TimeWidgetProvider.class), views);
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		scheduledExecutorService.shutdown();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
