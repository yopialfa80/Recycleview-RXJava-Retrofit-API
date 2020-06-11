package com.github.recycleviewrxjava.post;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.github.recycleviewrxjava.APIClient;
import com.github.recycleviewrxjava.Service;
import com.github.recycleviewrxjava.User;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RXJavaPost {
    public Activity activity;
    public RXJavaPost(Activity activity){
        this.activity = activity;
    }

    public void request(final Context context, String name, String stats, String namaPicture, String picture){
        Service postService = APIClient.getRetrofitInstance().create(Service.class);
        postService.savepost(name, stats, namaPicture, picture).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(User User) {
                        Toast.makeText(activity,User.toString(),Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
