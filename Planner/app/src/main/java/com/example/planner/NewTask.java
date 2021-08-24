package com.example.planner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.*;

public class NewTask extends BottomSheetDialogFragment {
    private TextView setDueDate;
    private EditText mTaskEdit;
    private Button mSaveBtn;
    private FirebaseFirestore firestore;
    private Context context;
    private String dueDate="";
    public static final String TAG="AddNewTask";
    public static NewTask newInstance(){
        return new NewTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_new_task,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDueDate=view.findViewById(R.id.set_due_tv);
        mTaskEdit=view.findViewById(R.id.task_edittext);
        mSaveBtn=view.findViewById(R.id.Savebtn);
        firestore=FirebaseFirestore.getInstance();
        mTaskEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    mSaveBtn.setEnabled(false);
                    mSaveBtn.setBackgroundColor(Color.GRAY);
                }
                else{
                    mSaveBtn.setEnabled(true);
                    mSaveBtn.setBackgroundColor(getResources().getColor(R.color.aqua));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setDueDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int month=calendar.get(Calendar.MONTH);
                int year=calendar.get(Calendar.YEAR);
                int day=calendar.get(Calendar.DATE);
                DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        setDueDate.setText(dayOfMonth+"/"+month+"/"+year);
                        dueDate=dayOfMonth+"/"+month+"/"+year;
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task=mTaskEdit.getText().toString();
                if(task.isEmpty()){
                    Toast.makeText(context,"No task here",Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String,Object> taskMap=new HashMap<>();
                    taskMap.put("task",task);
                    taskMap.put("task",dueDate);
                    taskMap.put("status",0);
                    firestore.collection("task").add(taskMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(context,"task saved",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity=getActivity();
        if(activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }
    }
}
