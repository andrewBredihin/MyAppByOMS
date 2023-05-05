package com.bav.ordermanagementsystem.ui.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.entity.Client;

public class RegistrationFragment extends Fragment {

    private EditText login, password, confirmPassword, firstName, lastName, secondName, email, phone;
    private View view;

    public RegistrationFragment(){
        super(R.layout.fragment_registration);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        login = view.findViewById(R.id.editTextLoginRegistrationPage);
        password = view.findViewById(R.id.editTextPasswordRegistrationPage);
        confirmPassword = view.findViewById(R.id.editTextConfirmPasswordRegistrationPage);
        firstName = view.findViewById(R.id.editTextFirstNameRegistrationPage);
        lastName = view.findViewById(R.id.editTextLastNameRegistrationPage);
        secondName = view.findViewById(R.id.editTextSecondNameRegistrationPage);
        email = view.findViewById(R.id.editTextEmailRegistrationPage);
        phone = view.findViewById(R.id.editTextPhoneRegistrationPage);
    }

    public Client getClient(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (firstName.getText().toString().length() < 1){
            Toast.makeText(view.getContext(), R.string.errorFirstNameRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }
        else if (lastName.getText().toString().length() < 1){
            Toast.makeText(view.getContext(), R.string.errorLastNameRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }
        else if (secondName.getText().toString().length() < 1){
            Toast.makeText(view.getContext(), R.string.errorSecondNameRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }
        else if (!email.getText().toString().matches(emailPattern)){
            Toast.makeText(view.getContext(), R.string.errorMailRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }
        else if (phone.getText().toString().length() != 10){
            Toast.makeText(view.getContext(), R.string.errorPhoneRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }
        else if (login.getText().toString().length() < 6){
            Toast.makeText(view.getContext(), R.string.errorLoginRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }
        else if (password.getText().toString().length() < 6){
            Toast.makeText(view.getContext(), R.string.errorPasswordRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }
        else if (!confirmPassword.getText().toString().equals(password.getText().toString())){
            Toast.makeText(view.getContext(), R.string.errorPasswordConfirmRegistrationPage, Toast.LENGTH_SHORT).show();
            return null;
        }

        Client client = new Client();
        client.setFirstName(firstName.getText().toString());
        client.setLastName(lastName.getText().toString());
        client.setSecondName(secondName.getText().toString());
        client.setEmail(email.getText().toString());
        client.setPhone(Long.valueOf(phone.getText().toString()));
        client.setLogin(login.getText().toString());
        client.setPassword(password.getText().toString());
        return client;
    }

    public String getPasswordConfirm(){
        return confirmPassword.getText().toString();
    }
}
