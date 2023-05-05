package com.bav.ordermanagementsystem.adapter.activeOrders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.OrderRecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActiveOrdersViewHolder extends RecyclerView.ViewHolder implements OrderRecyclerViewHolderInterface {
    final Button itemTitle;
    final ImageButton backButton, completeButton;
    private View view;

    public ActiveOrdersViewHolder(View view) {
        super(view);
        this.view = view;
        itemTitle = view.findViewById(R.id.activeOrdersOrderButton);
        backButton = view.findViewById(R.id.activeOrdersOrderBack);
        completeButton = view.findViewById(R.id.activeOrdersOrderComplete);
    }

    @Override
    public void setOrder(Order item) {
        itemTitle.setText(item.getTitle());
        itemTitle.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putLong("orderId", item.getId());
            Navigation.findNavController(view).navigate(R.id.nav_order_info_employee, bundle);
        });

        backButton.setOnClickListener(v -> {
            item.setStatus(OrderStatus.PENDING);
            item.setEmployee_id(null);
            Completable.fromAction(() -> DatabaseClient.getInstance(view.getContext()).getAppDatabase().orderDao().update(item))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            Toast.makeText(view.getContext(), R.string.orderCanceled, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        });

        completeButton.setOnClickListener(v -> {
            item.setStatus(OrderStatus.COMPLETED);
            Completable.fromAction(() -> DatabaseClient.getInstance(view.getContext()).getAppDatabase().orderDao().update(item))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            Toast.makeText(view.getContext(), R.string.orderComplete, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        });
    }
}
