package com.bav.ordermanagementsystem.ui.userPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.databinding.FragmentUserPageBinding;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.service.UserDetails;
import com.bav.ordermanagementsystem.service.UserService;

public class UserInfoFragment extends Fragment {

    private FragmentUserPageBinding binding;
    private UserService userService;

    private Button userEdit;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();

        userService = UserService.getInstance(context);
        userService.getUserDetails().observe(getViewLifecycleOwner(), new Observer<UserDetails>() {
            @Override
            public void onChanged(UserDetails userDetails) {
                binding.setUser(userDetails);
            }
        });

        userEdit = binding.btnEdit;
        userEdit.setOnClickListener(v -> {
            if (userService.getUserDetails().getValue().getClass().getName().equals(Client.class.getName())){
                Navigation.findNavController(container).navigate(R.id.nav_client_edit);
            } else {
                Navigation.findNavController(container).navigate(R.id.nav_employee_edit);
            }
        });

        return root;
    }
}
