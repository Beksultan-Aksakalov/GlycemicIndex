package com.example.glycemicindex;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.glycemicindex.fragment.FavouriteFragment;
import com.example.glycemicindex.fragment.GreenFragment;
import com.example.glycemicindex.fragment.OrangeFragment;
import com.example.glycemicindex.fragment.RedFragment;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ViewPagerAdapter viewPagerAdapter;

    public ActionBarDrawerToggle mToggle;

    FavouriteFragment favouriteFragment;
    GreenFragment greenFragment;
    OrangeFragment orangeFragment;
    RedFragment redFragment;

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        favouriteFragment = new FavouriteFragment();
        greenFragment = new GreenFragment();
        orangeFragment = new OrangeFragment();
        redFragment = new RedFragment();

        viewPagerAdapter.AddFragment(favouriteFragment, "");
        viewPagerAdapter.AddFragment(greenFragment, "");
        viewPagerAdapter.AddFragment(orangeFragment, "");
        viewPagerAdapter.AddFragment(redFragment, "");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_favorite);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_green);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.ic_orange);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(R.drawable.ic_red);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                greenFragment.beginSearch(s);
                orangeFragment.beginSearch(s);
                redFragment.beginSearch(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_by_name:
                greenFragment.sortByName();
                orangeFragment.sortByName();
                redFragment.sortByName();
                return true;
            case R.id.action_sort_by_index:
                greenFragment.sortByIndex();
                orangeFragment.sortByIndex();
                redFragment.sortByIndex();
                return true;
            case R.id.action_sort_reverse:
                greenFragment.reverseByName();
                orangeFragment.reverseByName();
                redFragment.reverseByName();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        displaySelectedItem(id);
        return true;
    }

    private void displaySelectedItem(int id) {
        switch (id) {

            case R.id.item_order:
                startActivity(new Intent(MainActivity.this,AddProductActivity.class));
                break;

            case R.id.item_exit:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Действительно хотите выйти ?")
                        .setPositiveButton("Да", ((dialog, which) -> finish()))
                        .setNegativeButton("Нет", ((dialog, which) -> {
                        }))
                        .show();
                break;
            default:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
