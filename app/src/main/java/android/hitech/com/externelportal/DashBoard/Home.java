package android.hitech.com.externelportal.DashBoard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hitech.com.externelportal.FragmentClicks.ProfileDescription;
import android.hitech.com.externelportal.LocationOperation.ShowMap;
import android.hitech.com.externelportal.LoginOperation.MainActivity;
import android.hitech.com.externelportal.Navigation.PortalHome;
import android.hitech.com.externelportal.R;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements MenuItem.OnMenuItemClickListener, View.OnClickListener {
    MenuItem mI1, mI2, mI3, mI4, mI5, mI6, mI7;
    NavigationView navigationView;
    int changer;
    TextView nTv0, nTv1, nTv2;
    SharedPreferences sf;
    DrawerLayout drawerLayout;
    ProgressBar pBar;
    RelativeLayout navProfile;
    ArrayList<String> arrayList;
    Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);
        bundle = getIntent().getExtras();
        BarChart barChart = (BarChart) findViewById(R.id.barChart);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(4f, 3));
        entries.add(new BarEntry(8f, 4));
        entries.add(new BarEntry(6f, 5));
        BarDataSet dataset = new BarDataSet(entries, "Calls");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("");
        labels.add("");
        labels.add("");
        labels.add("");
        labels.add("");
        labels.add("");
        BarData data = new BarData(labels, dataset);
        barChart.setData(data);
        barChart.animateXY(1000, 1000);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navgationView);
        menuOperation();
        pBar = (ProgressBar) navigationView.getHeaderView(0).findViewById(R.id.pBar);
        nTv0 = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nTv0);
        nTv1 = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nTv1);
        pBar.setMax(100);
        pBar.setProgress(20);
        if (bundle!=null){
            arrayList = bundle.getStringArrayList("user");
            if (arrayList != null) {

            }
        }
        navProfile = (RelativeLayout) navigationView.getHeaderView(0).findViewById(R.id.navProfile);
        navProfile.setOnClickListener(this);
    }

    private void menuOperation() {
        Menu m = navigationView.getMenu();
        mI1 = m.findItem(R.id.home);
        mI2 = m.findItem(R.id.jobs);
        mI3 = m.findItem(R.id.locate);
        mI4 = m.findItem(R.id.help);
        mI5 = m.findItem(R.id.contact);
        mI6 = m.findItem(R.id.setting);
        mI7 = m.findItem(R.id.logOut);
        mI1.setOnMenuItemClickListener(this);
        mI2.setOnMenuItemClickListener(this);
        mI3.setOnMenuItemClickListener(this);
        mI4.setOnMenuItemClickListener(this);
        mI5.setOnMenuItemClickListener(this);
        mI6.setOnMenuItemClickListener(this);
        mI7.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        sf = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        switch (item.getItemId()) {
            case R.id.home:
                changer = 1;
                break;
            case R.id.jobs:
                changer = 2;
                startActivity(new Intent(this, PortalHome.class));
                break;
            case R.id.locate:
                Toast.makeText(this, "Locate", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ShowMap.class));
                break;
            case R.id.help:
                changer = 4;
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact:
                changer = 5;
                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                changer = 6;
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logOut:
                changer = 7;
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pBtn:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.oBtn:
                Toast.makeText(this, "Menu Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navProfile:
                startActivity(new Intent(this, ProfileDescription.class));
        }
    }
}
