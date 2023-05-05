package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.Client;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface ClientDao {

    @Query("SELECT * FROM client")
    Flowable<List<Client>> getAll();

    @Query("SELECT * FROM client WHERE id = :clientId")
    Maybe<Client> getById(Long clientId);

    @Query("SELECT * FROM client WHERE login = :login AND password = :password")
    Maybe<Client> getByLoginAndPassword(String login, String password);

    @Query("SELECT * FROM client WHERE login = :login")
    Maybe<Client> getByLogin(String login);

    @Insert
    void insert(Client client);

    @Delete
    void delete(Client client);

    @Update
    void update(Client client);
}
