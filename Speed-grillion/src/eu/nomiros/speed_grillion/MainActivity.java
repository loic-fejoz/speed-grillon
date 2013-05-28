package eu.nomiros.speed_grillion;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {
	private TextView speedView, speedDiff, output;
	private List<Integer> limitList;
    private boolean useMph;
	public static final String TAG = "SpeedGrillion";
    public static final float MILE_KM_RATIO = 1.609344f;
	private final float FILTER_RATIO = 5;
	/**
	 * speed/limit difference from which the user is considered as being too fast or too slow
	 */
	private int threshold;
	private int currentLimit;
	private LocationManager locman;
	private Location lastLocation;
	private float lastSpeed = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		speedView = (TextView) findViewById(R.id.text_speed);
		speedDiff = (TextView) findViewById(R.id.text_speeddiff);
		output = (TextView) findViewById(R.id.text_message);
		output.setText(R.string.initializing);
		
		locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}
	
	@Override
	protected void onStart() {
		super.onStart();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, true);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String defaults = "30, 50, 90", limits = prefs.getString("pref_key_limit_list", defaults);

        if (! LimitList.checkString(limits)) {
            Toast toast = Toast.makeText(this, "Invalid limit list. Restoring default.", Toast.LENGTH_SHORT);
            toast.show();

            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("pref_key_limit_list", defaults);
            edit.commit();

            limits = defaults;
        }

        limitList = LimitList.parseString(limits); // Converting from Set to List to allow accessing elements by index
        currentLimit = limitList.size()/2;
        threshold = Integer.parseInt(prefs.getString("pref_key_alarm_threshold", "6"));
        useMph = prefs.getBoolean("pref_key_unitmph", false);

        updateLimit();
        updateSpeed();

		Criteria crit = new Criteria();
		crit.setAccuracy(Criteria.ACCURACY_FINE);
		crit.setSpeedRequired(true);
		String providerName = locman.getBestProvider(crit, false);
		if (providerName == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.error_no_provider).setCancelable(false);
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					MainActivity.this.finish();
				}
			});
			builder.create().show();
		} else if (! locman.isProviderEnabled(providerName)) {
			// TODO Open settings screen to enable GPS instead of closing app
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.error_provider_disabled).setCancelable(false);
			builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					MainActivity.this.finish();
				}
			});
			builder.create().show();
		} else {
			Log.d(TAG, "Location provider : "+ providerName);
			locman.requestLocationUpdates(providerName, 2000, 10, this);
			output.setText(R.string.waiting_location);
			speedDiff.setTextColor(getResources().getColor(R.color.in_range));
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		LocationManager loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		loc.removeUpdates(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Clicked : "+ item.getTitle() +", id = "+ item.getItemId() +", settings = "+ R.id.button_settings);
		if (item.getItemId() == R.id.button_settings) {
			Intent startPref = new Intent(this, SettingsActivity.class);
			startActivity(startPref);
		}
		return true;
	}

	/**
	 * Updates the label of the 'faster' and 'slower' buttons after changing the limit.
	 */
	private void updateLimit() {
		Button faster = (Button)findViewById(R.id.button_faster), slower = (Button)findViewById(R.id.button_slower);
		if (currentLimit+1 < limitList.size()) {
			faster.setText(limitList.get(currentLimit+1).toString());
			faster.setClickable(true);
		} else {
			faster.setText(R.string.not_applicable);
			faster.setClickable(false);
		}
		
		if (currentLimit > 0) {
			slower.setText(limitList.get(currentLimit-1).toString());
			slower.setClickable(true);
		} else { 
			slower.setText(R.string.not_applicable);
			slower.setClickable(false);
		}
	}
	
	/**
	 * Updates the UI after receiving an update on the user's speed from GPS.
	 */
	private void updateSpeed() {
        int limit = limitList.get(currentLimit);
        if (lastLocation != null && lastLocation.getSpeed() != 0) {
            float speedVal = lastLocation.getSpeed()*3.6f; // m/s -> km/h
            int speed = (int)(speedVal *(useMph ? MILE_KM_RATIO : 1));
            speedView.setText(speed +"/"+ limit + " "+ getResources().getString(useMph ? R.string.unit_mph : R.string.unit_kmph));
            int diff = Math.abs(speed - limit);
            speedDiff.setText((speed > limit ? "+" : "-") + String.valueOf(diff) + " "+ getResources().getString(useMph ? R.string.unit_mph : R.string.unit_kmph));
            if (diff > threshold) {
                speedDiff.setTextColor(getResources().getColor(R.color.out_of_range));
            } else if (diff < (threshold - (threshold/2))) { // Small hack to get a value rounded up instead of down
                speedDiff.setTextColor(getResources().getColor(R.color.in_range));
            }
        } else {
            speedView.setText("?/"+ limit + getResources().getString(useMph ? R.string.unit_mph : R.string.unit_kmph));
            speedDiff.setText("?");
            speedDiff.setTextColor(getResources().getColor(R.color.neutral));
        }
		// TODO Audio alert when the user's speed exceed threshold
	}
	
	/**
	 * Function called by the 'faster' and 'slower' buttons.
	 * @param button The input button calling the function
	 */
	public void changeLimit(View button) {
		if (button == findViewById(R.id.button_faster)) {
			if (currentLimit < limitList.size()) {
				currentLimit ++;
			}
		} else {
			if (currentLimit > 0) {
				currentLimit --;
			}
		}
		updateLimit();
        updateSpeed();
	}

	@Override
	public void onLocationChanged(Location loc) {
		Log.d(TAG, "Location obtained : "+ loc.getLatitude() +":"+ loc.getLongitude() +":"+ loc.getSpeed());
		if (loc.hasSpeed()) {
			updateSpeed();
		} else if (lastLocation != null) {
			// Time difference in seconds
			float delta = (float)(loc.getTime() - lastLocation.getTime())/1000f;
			// Distance in meters
			float dist = loc.distanceTo(lastLocation);
			// Speed in m/s
			float speed = dist / delta;
			lastSpeed = filter(lastSpeed, speed);
			Log.d(TAG, "Calculated speed. Distance "+ dist +", delta "+ delta +", average "+ speed*3.6f +"km/h, filtered : "+ lastSpeed*3.6f);
			loc.setSpeed(lastSpeed);
			lastLocation = loc;
			updateSpeed();
		}
		lastLocation = loc;
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Display message before exiting.
		finish();
	}

	@Override
	public void onProviderEnabled(String provider) {
		// That shouldn't happen.
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO manage temporary unavailability.
		if (status == LocationProvider.OUT_OF_SERVICE) {
			finish();
		}
	}
	
    /**
     * Simple recursive filter
     *
     * @param prev Previous value of filter
     * @param curr New input value into filter
     * @return New filtered value
     *
     */
    private float filter(float prev, float curr) {
      // If first time through, initialise digital filter with current values
      if (Float.isNaN(prev))
        return curr;
      // If current value is invalid, return previous filtered value
      if (Float.isNaN(curr))
        return prev;
      // Calculate new filtered value
      return (float) (curr / FILTER_RATIO + prev * (1.0 - 1.0 / FILTER_RATIO));
    }

}
