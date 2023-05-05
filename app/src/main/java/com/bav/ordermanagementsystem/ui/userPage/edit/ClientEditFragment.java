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
import com.bav.ordermanagementsystem.databinding.FragmentClientEditBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ClientEditFragment extends Fragment {

    private FragmentClientEditBinding binding;
    private UserService userService;

    private Button save;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentClientEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();

        userService = UserService.getInstance(context);
        Client client = (Client) userService.getUserDetails().getValue();
        binding.setClient(client);

        save = binding.btnSave;
        save.setOnClickListener(v -> {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if (binding.clientFirstName.getText().toString().length() < 1){
                Toast.makeText(getContext(), R.string.errorFirstNameRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (binding.clientLastName.getText().toString().length() < 1){
                Toast.makeText(getContext(), R.string.errorLastNameRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (binding.clientSecondName.getText().toString().length() < 1){
                Toast.makeText(getContext(), R.string.errorSecondNameRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (!binding.clientMail.getText().toString().matches(emailPattern)){
                Toast.makeText(getContext(), R.string.errorMailRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (binding.clientPhone.getText().toString().length() != 10){
                Toast.makeText(getContext(), R.string.errorPhoneRegistrationPage, Toast.LENGTH_SHORT).show();
                return;
            }

            client.setFirstName(binding.clientFirstName.getText().toString());
            client.setLastName(binding.clientLastName.getText().toString());
            client.setSecondName(binding.clientSecondName.getText().toString());
            client.setEmail(binding.clientMail.getText().toString());
            client.setPhone(Long.valueOf(binding.clientPhone.getText().toString()));

            Completable.fromAction(() -> DatabaseClient.getInstance(getContext()).getAppDatabase().clientDao().update(client))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            userService.setUserDetails(client);
                            Toast.makeText(getContext(), R.string.user_update, Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(root).navigate(R.id.nav_my_orders);
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
