package eu.nomiros.speed_grillion;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Nomiros on 24/05/13.
 */
public class SettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences); // Marked as deprecated but required by the minimum API set
    }
}