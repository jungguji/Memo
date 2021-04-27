package com.jgji.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

@SuppressLint("StaticFieldLeak")
public class MainActivity extends AppCompatActivity {

    private MemoDatabase db = null;
    private MemoDAO memoDAO;
    private List<MemoEntity> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MemoDatabase.getInstance(this);
        memoDAO = db.memoDAO();

        final Button button = findViewById(R.id.button_add);
        final EditText editText = findViewById(R.id.edittext_memo);
        button.setOnClickListener(v -> {
            MemoEntity memo = new MemoEntity(-1, editText.getText().toString());
            memoDAO.insert(memo);
        });

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}