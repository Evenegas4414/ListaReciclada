package com.ervr.listareciclada;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditWordFragment extends DialogFragment {

    private static final String ARG_POSITION = "position";
    private static final String ARG_CURRENT_TEXT = "current_text";

    private int position;
    private String currentText;
    private EditText editTextWord;
    private EditWordListener listener;

    public interface EditWordListener {
        void onWordUpdated(int position, String newWord);
    }

    public EditWordFragment() {
        // Required empty public constructor
    }

    public static EditWordFragment newInstance(int position, String currentText) {
        EditWordFragment fragment = new EditWordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putString(ARG_CURRENT_TEXT, currentText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditWordListener) {
            listener = (EditWordListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement EditWordListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
            currentText = getArguments().getString(ARG_CURRENT_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_word, container, false);
        editTextWord = view.findViewById(R.id.editTextWord);
        editTextWord.setText(currentText);

        Button buttonUpdate = view.findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWord();
            }
        });

        return view;
    }

    private void updateWord() {
        String newWord = editTextWord.getText().toString();
        listener.onWordUpdated(position, newWord);
        dismiss();
    }
}
