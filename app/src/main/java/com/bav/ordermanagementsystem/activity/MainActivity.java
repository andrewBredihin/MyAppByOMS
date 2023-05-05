package com.bav.ordermanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.entity.UserRole;
import com.bav.ordermanagementsystem.service.UserDetails;
import com.bav.ordermanagementsystem.service.UserService;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bav.ordermanagementsystem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private UserService userService;
    private UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userService = UserService.getInstance(getApplicationContext());
        user = userService.getUserDetails().getValue();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        View header = navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.navHeaderFullName);
        TextView addInfo = header.findViewById(R.id.navHeaderAddInfo);
        ImageView image = header.findViewById(R.id.userImage);
        image.setOnClickListener(v -> {
            navController.navigate(R.id.nav_user_info);
            drawer.close();
        });

        userService.getUserDetails().observe(this, new Observer<UserDetails>() {
            @Override
            public void onChanged(UserDetails userDetails) {
                name.setText(userDetails.getFullName());
                addInfo.setText(userDetails.getAdditionalInformation());
            }
        });

        if (user.getClass().equals(Client.class)) {
            navigationView.inflateMenu(R.menu.activity_main_drawer_client);
            navController.setGraph(R.navigation.mobile_navigation_client);
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_my_orders)
                    .setOpenableLayout(drawer)
                    .build();
        } else if (user.getClass().equals(Employee.class)){
            if (user.getRole().equals(UserRole.EMPLOYEE)){
                navigationView.inflateMenu(R.menu.activity_main_drawer_employee);
                navController.setGraph(R.navigation.mobile_navigation_employee);
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.nav_active_orders,
                        R.id.nav_pending_orders)
                        .setOpenableLayout(drawer)
                        .build();
            }
            else if(user.getRole().equals(UserRole.MANAGER)){
                navigationView.inflateMenu(R.menu.activity_main_drawer_manager);
                navController.setGraph(R.navigation.mobile_navigation_manager);
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.nav_employees_page,
                        R.id.nav_create_order_item)
                        .setOpenableLayout(drawer)
                        .build();
            }
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.action_logout).setOnMenuItemClickListener(item -> {
            userService.setUserDetails(null);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            return true;
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}