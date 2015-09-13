package org.iti.eyescare.util;

import java.util.Hashtable;

import android.util.Log;

public class MyLogger {

	private final static String LOG_TAG = "APP_LOG_TAG";
	private final static boolean APP_LOG_OUTPUT = true;
	private static Hashtable<String, MyLogger> sLoggerTable;

	static {
		sLoggerTable = new Hashtable<String, MyLogger>();
	}

	private String mClassName;

	public static MyLogger getLogger(String className) {
		MyLogger classLogger = (MyLogger) sLoggerTable.get(className);
		if (classLogger == null) {
			classLogger = new MyLogger(className);
			sLoggerTable.put(className, classLogger);
		}
		return classLogger;
	}

	private MyLogger(String name) {
		mClassName = name;
	}

	public void v(String log) {
		if (APP_LOG_OUTPUT) {
			Log.v(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}"
					+ "[" + mClassName + ":] " + log);
		}
	}

	public void d(String log) {
		if (APP_LOG_OUTPUT) {
			Log.d(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}"
					+ "[" + mClassName + ":] " + log);
		}
	}

	public void i(String log) {
		if (APP_LOG_OUTPUT) {
			Log.i(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}"
					+ "[" + mClassName + ":] " + log);
		}
	}

	public void i(String log, Throwable tr) {
		if (APP_LOG_OUTPUT) {
			Log.i(LOG_TAG,
					"{Thread:" + Thread.currentThread().getName() + "}" + "["
							+ mClassName + ":] " + log + "\n"
							+ Log.getStackTraceString(tr));
		}
	}

	public void w(String log) {
		if (APP_LOG_OUTPUT) {
			Log.w(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}"
					+ "[" + mClassName + ":] " + log);
		}
	}

	public void w(String log, Throwable tr) {
		if (APP_LOG_OUTPUT) {
			Log.w(LOG_TAG,
					"{Thread:" + Thread.currentThread().getName() + "}" + "["
							+ mClassName + ":] " + log + "\n"
							+ Log.getStackTraceString(tr));
		}
	}

	public void e(String log) {
		if (APP_LOG_OUTPUT)
			Log.e(LOG_TAG, "{Thread:" + Thread.currentThread().getName() + "}"
					+ "[" + mClassName + ":] " + log);
	}

	public void e(String log, Throwable tr) {
		if (APP_LOG_OUTPUT)
			Log.e(LOG_TAG,
					"{Thread:" + Thread.currentThread().getName() + "}" + "["
							+ mClassName + ":] " + log + "\n"
							+ Log.getStackTraceString(tr));
	}

}
