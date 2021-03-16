package com.example.musicPlayer;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.example.musicPlayer.Fragments.AlbumFragments;
import com.example.musicPlayer.Fragments.ArtistsFragment;
import com.example.musicPlayer.Fragments.FolderFragment;
import com.example.musicPlayer.Fragments.GenersFragment;
import com.example.musicPlayer.Fragments.PlayListsFragment;
import com.example.musicPlayer.Fragments.SongFragment;
import com.example.musicPlayer.NavItemClasses.Equalizer;
import com.example.roomwrapperimplement.R;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MPMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.fab), "Tap The Shuffler", "To Shuffle Your Music And Enjoy Your Journey  ")
                        .outerCircleColor(R.color.red)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.6f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(15)            // Specify the size (in sp) of the description text
                        //.descriptionTextColor(R.color.red)  // Specify the color of the description text
                        .textColor(R.color.black)            // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)                     // Specify a custom drawable to draw as the target
                        .targetRadius(60),
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional

                    }
                });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AlbumFragments(), "  ALBUMS    ");
        adapter.addFragment(new SongFragment(),"  SONGS    ");
        adapter.addFragment(new ArtistsFragment(),"  ARTISTS    ");
        adapter.addFragment(new GenersFragment(),"  GENERS    ");
        adapter.addFragment(new FolderFragment(),"  FOLDERS    ");
        adapter.addFragment(new PlayListsFragment(),"  PLAYLISTS");
        viewPager.setAdapter(adapter);

    }
    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mp_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingintent = new Intent(MPMainActivity.this, Settings.class);
            startActivity(settingintent);
        }else if (id == R.id.system_equalizer){
            Intent intent = new Intent();
            intent.setAction("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
            if ((intent.resolveActivity(getPackageManager()) != null)) {
                startActivity(intent);
            }

        }else if (id == R.id.rescan_library){

        }else if (id == R.id.sort_by){

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_remove_ads) {
            // Handle the camera action
        } else if (id == R.id.nav_sleep_time) {

        } else if (id == R.id.nav_equalizer) {
            Intent intent = new Intent(MPMainActivity.this, Equalizer.class);
            startActivity(intent);

        } else if (id == R.id.nav_download) {
            Intent intent= new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.muskurahat.com"));
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("Share Via");
            String shareBody = "Here is the share content body";
            String shareSub = "Your Subject here";
            sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            startActivity(Intent.createChooser(sharingIntent,"Share Using"));

        } else if (id == R.id.nav_setting) {
            Intent settingintent = new Intent(MPMainActivity.this, Settings.class);
            startActivity(settingintent);

        }else if (id == R.id.nav_feedback) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            String subject = "Your Subject here";
            String text = "Your text is here";
            String email = "abdulwajid9997@gmail.com";
            intent.putExtra(Intent.EXTRA_EMAIL,email);
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,text);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent,"feedback"));

        }else if (id == R.id.nav_rate_on_store) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store?hl=en"));
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
