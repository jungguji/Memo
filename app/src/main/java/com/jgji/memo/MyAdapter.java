package com.jgji.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Context context;
    private final List<MemoEntity> list;
    private final DeleteListener deleteListener;

    public MyAdapter(Context context, List<MemoEntity> memoEntities, DeleteListener deleteListener) {
        this.context = context;
        this.list = memoEntities;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_memo, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MemoEntity memo = list.get(position);

        holder.memo.setText(memo.getMemo());
        holder.root.setOnLongClickListener(v -> {
            deleteListener.onDeleteListener(memo);
            return true;
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView memo;
        private ConstraintLayout root;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.memo = itemView.findViewById(R.id.textview_memo);
            this.root = itemView.findViewById(R.id.root);
        }
    }

}
