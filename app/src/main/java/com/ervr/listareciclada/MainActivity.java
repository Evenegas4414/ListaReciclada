package com.ervr.listareciclada;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WordAdapter.OnWordClickListener, WordAdapter.OnWordLongClickListener, EditWordFragment.EditWordListener {

    private List<Word> words;
    private WordAdapter wordAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        words = new ArrayList<>();
        wordAdapter = new WordAdapter(words, this);
        wordAdapter.setOnWordClickListener(this);
        wordAdapter.setOnWordLongClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wordAdapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWord();
            }
        });
    }

    private void addWord() {
        Word newWord = new Word("New Word");
        words.add(0, newWord);
        wordAdapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onWordClick(int position) {
        Word word = words.get(position);
        word.setWord("Clicked");
        wordAdapter.notifyItemChanged(position);
    }

    @Override
    public void onWordLongClick(int position) {
        Word word = words.get(position);
        showEditWordFragment(position, word.getWord());
    }

    private void showEditWordFragment(int position, String currentText) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditWordFragment editWordFragment = EditWordFragment.newInstance(position, currentText);
        editWordFragment.show(fragmentManager, "edit_word_fragment");
    }

    @Override
    public void onWordUpdated(int position, String newWord) {
        Word word = words.get(position);
        word.setWord(newWord);
        wordAdapter.notifyItemChanged(position);
    }
}
