package org.uab.dedam.todoman;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewTodoFragment extends Fragment {

    Activity activity;
    EditText editTodoTitle;
    EditText editDescription;
    TextView textDueDate;
    CheckBox checkDone;
    Button btnSave;
    Button btnClear;
    Button btnCancel;

    public interface NewTodoFragmentActionListener{
        void onSave();
        void onCancel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity) {
            activity = (Activity) context;
        }
    }


    public NewTodoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnSave = (Button) activity.findViewById(R.id.btnSave);
        btnClear = (Button) activity.findViewById(R.id.btnClear);
        btnCancel = (Button) activity.findViewById(R.id.btnCancel);
        editTodoTitle = (EditText) activity.findViewById(R.id.textTitle);
        editDescription = (EditText) activity.findViewById(R.id.textDescription);
        textDueDate = (TextView) activity.findViewById(R.id.textDueDate);
        checkDone = (CheckBox) activity.findViewById(R.id.checkDone);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewTodoFragmentActionListener) activity).onSave();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTodoTitle.setText("");
                editDescription.setText("");
                textDueDate.setText("");
                checkDone.setChecked(false);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewTodoFragmentActionListener) activity).onCancel();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_todo, container, false);
    }

}
