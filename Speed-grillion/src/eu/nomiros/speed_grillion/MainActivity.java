package eu.nomiros.speed_grillion;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener {
	private TextView speedView, speedDiff, output;
	private List<Integer> limitList;
	/**
	 * speed/limit difference from which the user is considered as being too fast or too slow
	 */
	private int threshold;
	private int currentLimit;
	private Location lastLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		limitList = new ArrayList<Integer>();
		limitList.add(30);
		limitList.add(50);
		limitList.add(90);
		limitList.add(110);
		limitList.add(130);
		currentLimit = limitList.size()/2;
		threshold = 6;
		
		setContentView(R.layout.activity_main);

		speedView = (TextView) findViewById(R.id.text_speed);
		speedDiff = (TextView) findViewById(R.id.text_speeddiff);
		output = (TextView) findViewById(R.id.text_message);
		output.setText(R.string.initializing);
		updateLimit();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LocationManager loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();
		crit.setAccuracy(Criteria.ACCURACY_FINE);
		crit.setSpeedRequired(true);
		String providerName = loc.getBestProvider(crit, false);
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
		} else if (! loc.isProviderEnabled(providerName)) {
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
			loc.requestLocationUpdates(providerName, 2000, 10, this);
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

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */
	
	/**
	 * Updates the label of the 'faster' and 'slower' buttons after changing the limit.
	 */
	public void updateLimit() {
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
	public void updateSpeed() {
		float speed = lastLocation.getSpeed();
		Integer limit = limitList.get(currentLimit);
		speedView.setText(speed +"/"+ limit);
		int diff = (int) Math.abs(speed - limit);
		speedDiff.setText((speed > limit ? "+" : "-") + String.valueOf(diff));
		if (diff > threshold) {
			speedDiff.setTextColor(getResources().getColor(R.color.out_of_range));
		} else if (diff < (threshold - (threshold/2))) { // Small hack to get a value rounded up instead of down
			speedDiff.setTextColor(getResources().getColor(R.color.in_range));
		}
		// TODO Alert when the user's speed exceed threshold
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
	}

	@Override
	public void onLocationChanged(Location loc) {
		if (loc.getSpeed() != 0.0) {
			lastLocation = loc;
			updateSpeed();
		}
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

}
