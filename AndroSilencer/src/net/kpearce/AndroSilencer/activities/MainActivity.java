package net.kpearce.AndroSilencer.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.AndroSilencer.R;
import net.kpearce.AndroSilencer.services.WifiLocationSilenceService;
import net.kpearce.AndroSilencer.setttings.SettingsFragment;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        boolean running = false;
        for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(WifiLocationSilenceService.class.equals(serviceInfo.service.getClass())){
                running = true;
                break;
            }
        }
        if (!running) {
            Intent intent = new Intent(this, WifiLocationSilenceService.class);
            startService(intent);
        }

    }

    public void newLocationClick(View view){
        Intent newLocationIntent = new Intent(view.getContext(),LocationTypeActivity.class);
        startActivity(newLocationIntent);
    }

    public void manageLocationsClick(View view){
        Intent manageLocationIntent = new Intent(view.getContext(),ManageLocationsActivity.class);
        startActivity(manageLocationIntent);
    }

    public void openSettings(View view){
        getFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new SettingsFragment())
                        .commit();
    }
}
