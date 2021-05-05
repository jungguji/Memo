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
public class MainActivity extends Activity implements DeleteListener{

    private MemoDatabase db;
    private List<MemoEntity> memoList;
    private RecyclerView recyclerView;
    private MemoDAO memoDAO;
    private EditText editText;

    private static long id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MemoDatabase.getInstance(this);
        memoDAO = db.memoDAO();

        final Button button = findViewById(R.id.button_add);
        editText = findViewById(R.id.edittext_memo);
        button.setOnClickListener(v -> {
            final MemoEntity memo = new MemoEntity(null, editText.getText().toString());
            insertMemo(memo);
        });

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        getAllMemos();
    }

    public void insertMemo(MemoEntity memo) {
        final AsyncTask insertTask = new AsyncTask<Object, Object, Object>() {

            @Override
            protected Object doInBackground(Object... voids) {
                db.memoDAO().insert(memo);
                editText.setText("");
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

    public void deleteMemo(MemoEntity memo) {
        final AsyncTask deleteTask = new AsyncTask<Object, Object, Object>() {

            @Override
            protected Object doInBackground(Object... voids) {
                db.memoDAO().delete(memo);
                return null;
            }

            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                getAllMemos();
            }
        };

        deleteTask.execute();
    }

    public void setRecyclerView(List<MemoEntity> memoList) {
        recyclerView.setAdapter(new MyAdapter(this, memoList, this));
    }

    public void onDeleteListener(MemoEntity memoEntity) {
        deleteMemo(memoEntity);
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