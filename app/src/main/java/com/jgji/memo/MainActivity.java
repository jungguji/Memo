package com.jgji.memo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

@SuppressLint(value = "StaticFieldLeak")
public class MainActivity extends Activity {

    private MemoDatabase db;
    private List<MemoEntity> memoList;
    private RecyclerView recyclerView;
    private MemoDAO memoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MemoDatabase.getInstance(this);
        memoDAO = db.memoDAO();

        final Button button = findViewById(R.id.button_add);
        final EditText editText = findViewById(R.id.edittext_memo);
        button.setOnClickListener(v -> {
            final MemoEntity memo = new MemoEntity(null, editText.getText().toString());
            insertMemo(memo);
        });

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void insertMemo(MemoEntity memo) {
        final AsyncTask insertTask = new AsyncTask<Object, Object, Object>() {

            @Override
            protected Object doInBackground(Object... voids) {
                db.memoDAO().insert(memo);
                return null;
            }

            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                getAllMemos();
            }
        };

        insertTask.execute();
    }

    public void getAllMemos() {
        final AsyncTask getTask = new GetTask();
        getTask.execute();
    }

    public void setRecyclerView(List<MemoEntity> memoList) {
        recyclerView.setAdapter(new MyAdapter(this, memoList));
    }

    class GetTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected Object doInBackground(Object... voids) {
            memoList = db.memoDAO().getAll();
            return null;
        }

        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            setRecyclerView(memoList);
        }
    }


}