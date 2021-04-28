package com.jgji.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

@SuppressLint(value = "StaticFieldLeak")
public class MainActivity extends AppCompatActivity {

    private MemoDatabase db;
    private List<MemoEntity> memoList;
    private MemoDAO memoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MemoDatabase.getInstance(this);
        memoDAO = db.memoDAO();

//        final Button button = findViewById(R.id.button_add);
//        final EditText editText = findViewById(R.id.edittext_memo);
//        button.setOnClickListener(v -> {
//            final MemoEntity memo = new MemoEntity(-1, editText.getText().toString());
//            insertMemo(memo);
//        });

//        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void insertMemo(MemoEntity memo) {
        final AsyncTask insertTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.memoDAO().insert(memo);
                return null;
            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                getAllMemos();
            }
        };

        insertTask.execute();
    }

    public void getAllMemos() {
        final AsyncTask getTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                memoList = db.memoDAO().getAll();
                return null;
            }

            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                setRecyclerView(memoList);
            }
        };

        getTask.execute();
    }

    public void setRecyclerView(List<MemoEntity> memoList) {
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setAdapter(new MyAdapter(this, memoList));
    }



}