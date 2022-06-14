package com.farmersgroup.farmerszone.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.farmersgroup.farmerszone.ui.about.AboutFragment;
import com.google.android.material.navigation.NavigationView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.farmersgroup.farmerszone.R;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ActionBarDrawerToggle toggle;

    @BindView(R.id.buttonStart) Button mButtonName;
    @BindView(R.id.signOutBtn) Button mSignOutBtn;
//    @BindView(R.id.userBtn) Button mUserBtn;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;

//    @BindView(R.id.editTextName) EditText mEditTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ButterKnife.bind(this);

        mButtonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String name = mEditTextName.getText().toString();
                Intent intent = new Intent(MainActivity.this, ActionsActivity.class);
//                intent.putExtra("name", name);
                startActivity(intent);
                Log.d(TAG, "onClick: btnclick");
            }
        });
        mSignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("TropicalFruitandVeg");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();

                if(id==R.id.nav_about){
                    Intent intent=new Intent(getBaseContext(), AboutFragment.class);
                    startActivity(intent);
                } else if (id == R.id.nav_home) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                }else if (id == R.id.nav_account) {
                         Intent intent=new Intent(getBaseContext(),MyUserFragment.class);
                           startActivity(intent);
                }else if (id == R.id.nav_search) {
                    Intent intent=new Intent(getBaseContext(),BrowseAllActivity.class);
                    startActivity(intent); }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}