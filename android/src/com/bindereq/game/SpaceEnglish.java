package com.bindereq.game;

import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class SpaceEnglish extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		// Запрет на гашение экрана
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// Отключение лишнего для экономии энергопотребления
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		config.useRotationVectorSensor = false;
		config.useImmersiveMode = false;

		// Сглаживание
		config.numSamples = 2;

		// Пуск
		initialize(new SpaceEnglishCore(), config);
	}
}
