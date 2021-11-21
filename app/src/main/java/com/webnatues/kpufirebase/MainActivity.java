package com.webnatues.kpufirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PI> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    SwipeRefreshLayout swipeRefreshLayout ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout =findViewById(R.id.refresh_layout); //새로고침
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();


        database = FirebaseDatabase.getInstance(); //파베 데이터베이스 연동


        databaseReference = database.getReference("PI");//table이름 과 동일하게 연결



        adapter = new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 연결

        //새로고침 아래로 슬라이드
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //firebase 의 데이터를 받아오는 곳
                        arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            PI pi = snapshot.getValue(PI.class); //만들어뒀던 user 객체에 데이터를 담는다
                            arrayList.add(pi); //담은 데이터들을 배열리스트에 넣고 리사클러뷰로 보낼 준비

                        }

                        adapter.notifyDataSetChanged(); // 새로고침
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //db에러시

                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //firebase 의 데이터를 받아오는 곳
                        arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            PI pi = snapshot.getValue(PI.class); //만들어뒀던 user 객체에 데이터를 담는다
                            arrayList.add(pi); //담은 데이터들을 배열리스트에 넣고 리사클러뷰로 보낼 준비

                        }

                        adapter.notifyDataSetChanged(); // 새로고침
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //db에러시

                    }
                });
            }
        };

        timer.schedule(timerTask ,0, 3000);

    }


}