package com.bav.ordermanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.service.UserDetails;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private EditText login, password;
    private Button btnLogin, btnRegistration;
    private ImageView icon;
    private TextView title;
    private UserService userService;
    private DatabaseClient databaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userService = UserService.getInstance(getApplicationContext());
        userService.setUserDetails(null);
        databaseClient = DatabaseClient.getInstance(getApplicationContext());

        login = findViewById(R.id.editTextLoginLoginPage);
        password = findViewById(R.id.editTextPasswordLoginPage);
        btnLogin = findViewById(R.id.buttonLoginLoginPage);
        btnRegistration = findViewById(R.id.buttonRegistrationLoginPage);
        icon = findViewById(R.id.imageViewLoginPage);
        title = findViewById(R.id.textViewTitleLoginPage);

        btnLogin.setOnClickListener(v -> {
            getClient();
        });

        btnRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), RegistrationActivity.class);
            startActivity(intent);
        });
    }

    private void getClient(){
        databaseClient.getAppDatabase().clientDao().getByLoginAndPassword(login.getText().toString(), password.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<Client>() {
                    @Override
                    public void onSuccess(Client client) {
                        loginSuccessfully(client);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getEmployee();
                    }
                });
    }
    private void getEmployee(){
        databaseClient.getAppDatabase().employeeDao().getByLoginAndPassword(login.getText().toString(), password.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<Employee>() {
                    @Override
                    public void onSuccess(Employee employee) {
                        loginSuccessfully(employee);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(), R.string.errorLoginPage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void loginSuccessfully(UserDetails user){
        userService.setUserDetails(user);
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
    }

    /*private void deleteAllUsers(){
       databaseClient.getAppDatabase().clientDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clients -> {
                    for (Client x : clients){
                        //Toast.makeText(getApplicationContext(), "Client with login:  " + x.getLogin(), Toast.LENGTH_LONG).show();
                        Completable.fromAction(() -> DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().clientDao().delete(x))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new DisposableCompletableObserver() {
                                    @Override
                                    public void onComplete() {
                                        Toast.makeText(getApplicationContext(), "Client with id " + x.getId() + "has been deleted", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getApplicationContext(), "Client with id " + x.getId() + " hasn't been deleted", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });
    }*/

    /*private void getAllUsers(){
        databaseClient.getAppDatabase().clientDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clients -> {
                    //Действия со списком clients
                });
    }*/
}
