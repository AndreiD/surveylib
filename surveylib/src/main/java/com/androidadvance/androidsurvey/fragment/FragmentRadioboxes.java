package com.androidadvance.androidsurvey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidadvance.androidsurvey.Answers;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.Question;

import java.util.Collections;
import java.util.List;

public class FragmentRadioboxes extends Fragment {

    private Question q_data;
    private FragmentActivity mContext;
    private Button button_continue;
    private TextView textview_q_title;
    private RadioGroup radioGroup;
    private ViewGroup rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null){
            rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_radioboxes, container, false);

            button_continue = (Button) rootView.findViewById(R.id.button_continue);
            textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
            radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
            button_continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SurveyActivity) mContext).go_to_next();
                }
            });

            button_continue.setVisibility(View.GONE);
            mContext = getActivity();
            q_data = (Question) getArguments().getSerializable("data");
            textview_q_title.setText(q_data.getQuestionTitle());

            fillRadioGroup();
        }

        return rootView;
    }

    private void collect_data(String the_choice) {

        if (!the_choice.isEmpty()) {
            Answers.getInstance().put_answer(textview_q_title.getText().toString(), the_choice);
        }

        if (q_data.getRequired()) {
            button_continue.setVisibility(View.VISIBLE);
        } else {
            button_continue.setVisibility(View.GONE);
        }

    }

    private void fillRadioGroup() {
        List<String> qq_data = q_data.getChoices();
        if (q_data.getRandomChoices()) {
            Collections.shuffle(qq_data);
        }

        for (String choice : qq_data) {
            RadioButton rb = new RadioButton(mContext);
            rb.setText(Html.fromHtml(choice));
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(rb);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();

                if (isChecked) {
                    collect_data(checkedRadioButton.getText().toString());
                }
            }

        });
    }
}