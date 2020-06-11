package com.github.recycleviewrxjava.post_get;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.github.recycleviewrxjava.APIClient;
import com.github.recycleviewrxjava.R;
import com.github.recycleviewrxjava.RecycleAdapter;
import com.github.recycleviewrxjava.Service;
import com.github.recycleviewrxjava.User;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RXJavaPostAndGet {
    public Activity activity;
    private ArrayList<User> listUser;
    private RecycleAdapter adapter;
    RecyclerView recyclerView;

    public RXJavaPostAndGet(Activity activity){
        this.activity = activity;
        recyclerView = activity.findViewById(R.id.recycleview);
        listUser = new ArrayList<>();
    }

    public void request(final Context context, String name, String stats, String namaPicture, String picture){
        Service postService = APIClient.getRetrofitInstance().create(Service.class);
        postService.postandget(name, stats, namaPicture, picture).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ArrayList<User>>() {
                        @Override
                        public void onCompleted() {
                            adapter = new RecycleAdapter(activity, listUser);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(activity,e.toString(),Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(ArrayList<User> users) {
                            Toast.makeText(activity,users.toString(),Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < users.size(); i++) {
                                String name = users.get(i).getName();
                                String stats = users.get(i).getStats();
                                String picture = users.get(i).getPicture();
                                listUser.add(new User(name, stats, picture));
                            }Toast.makeText(activity,"Success",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
}
