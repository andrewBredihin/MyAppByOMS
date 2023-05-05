package com.bav.ordermanagementsystem.ui.userPage.edit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.databinding.FragmentEmployeeEditBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class EmployeeEditFragment extends Fragment {

    private FragmentEmployeeEditBinding binding;
    private UserService userService;

    private Button save;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEmployeeEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();

        userService = UserService.getInstance(context);
        Employee employee = (Employee) userService.getUserDetails().getValue();
        binding.setEmployee(employee);

        save = binding.btnSave;
        save.setOnClickListener(v -> {
            if (binding.firstName.getText().toString().length() < 1){
                Toast.makeText(getContext(), R.string.errorFirstNameRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (binding.lastName.getText().toString().length() < 1){
                Toast.makeText(getContext(), R.string.errorLastNameRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (binding.phone.getText().toString().length() != 10){
                Toast.makeText(getContext(), R.string.errorPhoneRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }

            employee.setFirstName(binding.firstName.getText().toString());
            employee.setLastName(binding.lastName.getText().toString());
            employee.setPhone(Long.valueOf(binding.phone.getText().toString()));

            Completable.fromAction(() -> DatabaseClient.getInstance(getContext()).getAppDatabase().employeeDao().update(employee))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            userService.setUserDetails(employee);
                            Toast.makeText(getContext(), R.string.user_update, Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(root).navigate(R.id.nav_active_orders);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getContext(), R.string.user_not_update, Toast.LENGTH_LONG).show();
                        }
                    });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
