package com.ervr.listareciclada;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> words;
    private OnWordClickListener onWordClickListener;
    private OnWordLongClickListener onWordLongClickListener;

    public interface OnWordClickListener {
        void onWordClick(int position);
    }

    public interface OnWordLongClickListener {
        void onWordLongClick(int position);
    }

    public WordAdapter(List<Word> words, OnWordClickListener onWordClickListener) {
        this.words = words;
        this.onWordClickListener = onWordClickListener;
    }

    public void setOnWordClickListener(OnWordClickListener onWordClickListener) {
        this.onWordClickListener = onWordClickListener;
    }

    public void setOnWordLongClickListener(OnWordLongClickListener onWordLongClickListener) {
        this.onWordLongClickListener = onWordLongClickListener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(position);
        holder.bind(word);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewWord;

        WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onWordClickListener.onWordClick(position);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onWordLongClickListener.onWordLongClick(position);
                        return true;
                    }
                    return false;
                }
            });
        }

        void bind(Word word) {
            textViewWord.setText(word.getWord());
        }
    }
}
