package com.taplytics.example;

import android.app.Application;

import com.taplytics.sdk.Taplytics;

/**
 * Overriding the application is necessary. This is so application context is passed to the sdk, as well as to set up the sdk before any
 * other content loads.
 * 
 * It is possible to do this in your application's main activity onCreate(), however for maximum functionality, instantiate Taplytics in an
 * application class.
 * 
 * Do not forget to add the name descriptor to your application tag in androidmanifest.xml!
 * 
 * @author vicv
 * 
 */
public class ExampleApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Supply your api key here and thats it! You're set up with basic tracking and features!
		// Way more is to come down the line. Expect this to catch up to the iOS sdk soon!
		Taplytics.startTaplytics(this, "Your API key");
	}

}
