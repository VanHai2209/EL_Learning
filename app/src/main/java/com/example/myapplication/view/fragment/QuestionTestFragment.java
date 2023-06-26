package com.example.myapplication.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.TestModel;

public class QuestionTestFragment extends Fragment {
    private TestModel testModel;
    private OnAnswerSelectedListener answerSelectedListener;

    public interface OnAnswerSelectedListener {
        void onAnswerSelected(boolean isCorrect, boolean isSelected);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnswerSelectedListener) {
            answerSelectedListener = (OnAnswerSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAnswerSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        answerSelectedListener = null;
    }

    private void onAnswerSelected(boolean isCorrect) {
        if (answerSelectedListener != null) {
            answerSelectedListener.onAnswerSelected(isCorrect, true);
        }
    }


    public QuestionTestFragment() {

    }

    public static QuestionTestFragment newInstance(TestModel testModel) {
        QuestionTestFragment fragment = new QuestionTestFragment();
        Bundle args = new Bundle();
        args.putParcelable("ARG_TEST_MODEL", testModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            testModel = getArguments().getParcelable("ARG_TEST_MODEL");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_test, container, false);
        TextView txtQuestion = view.findViewById(R.id.txtQuestion);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        RadioButton keyA = view.findViewById(R.id.keyA);
        RadioButton keyB = view.findViewById(R.id.keyB);
        RadioButton keyC = view.findViewById(R.id.keyC);
        RadioButton keyD = view.findViewById(R.id.keyD);
        txtQuestion.setText(testModel.getName());
        keyA.setText(testModel.getKeyA());
        keyB.setText(testModel.getKeyB());
        keyC.setText(testModel.getKeyC());
        keyD.setText(testModel.getKeyD());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = radioGroup.findViewById(i);
                String selectedKey = checkedRadioButton.getText().toString();
                boolean isCorrect = selectedKey.equals(testModel.getKeyCorrect());

                // Gọi phương thức onAnswerSelected() để truyền giá trị từ fragment sang activity
                onAnswerSelected(isCorrect);

            }
        });
        return view;
    }
}