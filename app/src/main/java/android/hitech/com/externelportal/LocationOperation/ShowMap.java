package android.hitech.com.externelportal.LocationOperation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hitech.com.externelportal.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;

public class ShowMap extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    ActionBar actionBar;
    String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    GoogleMap gMap;
    View view, view1, view2;
    RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mapFragment = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.map);
        view = mapFragment.getView();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_json));
        googleMap.setMinZoomPreference(3);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permission, 7);
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setMyLocationEnabled(true);
        view1 = (View) view.findViewById(Integer.parseInt("1")).getParent();
        view2 = view1.findViewById(Integer.parseInt("2"));
        layoutParams = (RelativeLayout.LayoutParams) view2.getLayoutParams();
        layoutParams.setMargins(0,20,50,0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);

    }

}
