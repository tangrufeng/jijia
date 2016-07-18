package com.eryiche.frame.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.xhk.wifibox.XHKApplication;

public class PreferenceUtils {

	static final String TAG = PreferenceUtils.class.getSimpleName();

	private static String PREFERENCES_NAME;

	private static Map<String, Object> mCacheItem = new HashMap<String, Object>();

	public synchronized static void putString(String key, String value) {
		mCacheItem.put(key, value);
		getAppSettingPreferenceEditor().putString(key, value).commit();
	}

	public synchronized static String getString(String key) {
		String value = (String) mCacheItem.get(key);
		if (!TextUtils.isEmpty(value)) {
			return value;
		}

		SharedPreferences sp = getAppSettingPreference();
		value = sp.getString(key, null);
		if (!TextUtils.isEmpty(value)) {
			mCacheItem.put(key, value);
		}

		return value;
	}

	public synchronized static void putInt(String key, int value) {
		mCacheItem.put(key, value);
		getAppSettingPreferenceEditor().putInt(key, value).commit();
	}

	public synchronized static int getInt(String key) {
		return getInt(key, 0);
	}

	public synchronized static int getInt(String key, int defaultValue) {
		int value = 0;
		try {
			Object objValue = mCacheItem.get(key);
			if (objValue != null) {
				value = (Integer) objValue;
				if (value != 0) {
					return value;
				}
			}
		} catch (Exception e) {
			// ignore
		}

		SharedPreferences sp = getAppSettingPreference();
		value = sp.getInt(key, defaultValue);
		if (value != 0) {
			mCacheItem.put(key, value);
		}

		return value;
	}

	public synchronized static void putLong(String key, long value) {
		mCacheItem.put(key, value);
		getAppSettingPreferenceEditor().putLong(key, value).commit();
	}

	public synchronized static long getLong(String key) {
		return getLong(key, 0);
	}

	public synchronized static long getLong(String key, long defaultValue) {
		long value = 0;
		try {
			Object objValue = mCacheItem.get(key);
			if (objValue != null) {
				value = (Long) objValue;
				if (value != 0) {
					return value;
				}
			}
		} catch (Exception e) {
			// ignore
		}

		SharedPreferences sp = getAppSettingPreference();
		value = sp.getLong(key, defaultValue);
		if (value != 0) {
			mCacheItem.put(key, value);
		}

		return value;
	}

	public synchronized static void putFloat(String key, float value) {
		mCacheItem.put(key, value);
		getAppSettingPreferenceEditor().putFloat(key, value).commit();
	}

	public synchronized static float getFloat(String key) {
		return getFloat(key, 0);
	}

	public synchronized static float getFloat(String key, float defaultValue) {
		float value = 0;
		try {
			Object objValue = mCacheItem.get(key);
			if (objValue != null) {
				value = (Float) objValue;
				if (value != 0) {
					return value;
				}
			}
		} catch (Exception e) {
			// ignore
		}

		SharedPreferences sp = getAppSettingPreference();
		value = sp.getFloat(key, defaultValue);
		if (value != 0) {
			mCacheItem.put(key, value);
		}

		return value;
	}

	public synchronized static void putBoolean(String key, boolean value) {
		mCacheItem.put(key, value);
		getAppSettingPreferenceEditor().putBoolean(key, value).commit();
	}

	public synchronized static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public synchronized static boolean containsKey(String key){
		if(!mCacheItem.containsKey(key)){
			return  getAppSettingPreference().contains(key);
		}else{
			return true;
		}
	}
	
	public synchronized static boolean getBoolean(String key,
			boolean defaultValue) {
		boolean value = false;
		try {
			Object objValue = mCacheItem.get(key);
			if (objValue != null) {
				value = (Boolean) objValue;
			}
		} catch (Exception e) {
			// ignore
		}

		SharedPreferences sp = getAppSettingPreference();
		value = sp.getBoolean(key, defaultValue);
		mCacheItem.put(key, value);

		return value;
	}

	public synchronized static void remove(String key) {
		mCacheItem.clear();
		getAppSettingPreferenceEditor().remove(key).commit();
	}

	public synchronized static void clear() {
		mCacheItem.clear();
		getAppSettingPreferenceEditor().clear().commit();
	}

	private static Editor getAppSettingPreferenceEditor() {
		return getAppSettingPreference().edit();
	}

	private static SharedPreferences getAppSettingPreference() {
		Context ctx = getContext();
		// if not set init preference name then the preference name is the
		// packagename
		if (TextUtils.isEmpty(PREFERENCES_NAME)) {
			PREFERENCES_NAME = ctx.getPackageName();
		}
		return ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
	}

	
	private static Context getContext() {
		return XHKApplication.getInstance();
	}
}
