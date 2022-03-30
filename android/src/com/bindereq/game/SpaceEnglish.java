package com.bindereq.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class SpaceEnglish extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGyroscope = false;
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useRotationVectorSensor = false;
		config.useWakelock = false;
		initialize(new SpaceEnglishCore(), config);
	}
}