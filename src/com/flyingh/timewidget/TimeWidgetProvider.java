package com.flyingh.timewidget;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class TimeWidgetProvider extends AppWidgetProvider {

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		context.startService(new Intent(context, TimeService.class));
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		context.stopService(new Intent(context, TimeService.class));
	}
}
