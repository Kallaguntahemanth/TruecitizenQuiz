package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button truebutton;
    private Button falsebutton;
    private ImageButton nextbutton;
    private ImageButton previousbutton;
    private TextView text_question;
    private int currentQuestionIndex=0;

    private Question[] questionbank=new Question[]{
            new Question(R.string.question_amendments,false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.myquestion,true),
            new Question(R.string.question_independence_rights,true),
            new Question(R.string.question_religion,true),
            new Question(R.string.question_government,false),
            new Question(R.string.question_government_feds,false),
            new Question(R.string.question_government_senators,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Question question=new Question(R.string.myquestion,true);


        truebutton= findViewById(R.id.true_button);
        falsebutton=findViewById(R.id.false_button);
        text_question=findViewById(R.id.answer_text_view);
        nextbutton=findViewById(R.id.next_button);
        previousbutton=findViewById(R.id.previous_button);

        falsebutton.setOnClickListener(this);//register all buttons to click events using this
        truebutton.setOnClickListener(this);
        nextbutton.setOnClickListener(this);
        previousbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.false_button:
                //Toast.makeText(MainActivity.this,R.string.false_text,Toast.LENGTH_SHORT).show();
                checkanswer(false);
                break;
            case R.id.true_button:
                //Toast.makeText(MainActivity.this,R.string.true_text,Toast.LENGTH_SHORT).show();
                checkanswer(true);
                break;

            case R.id.next_button:
                //go to next question
                currentQuestionIndex=(currentQuestionIndex+1)%questionbank.length;
                updatequestion();
                break;

            case R.id.previous_button:
                if(currentQuestionIndex>0)
                    currentQuestionIndex=currentQuestionIndex-1;
                else
                    currentQuestionIndex=7;
                updatequestion();
                break;

                default:
        }
    }
    public void updatequestion()
    {
        text_question.setText(questionbank[currentQuestionIndex].getAnswerResid());
    }
    public void checkanswer(boolean userChooseCorrect)

    {
        boolean answerisTrue= questionbank[currentQuestionIndex].isAnswerTrue();
        int toastmessageId=0;
        if(userChooseCorrect==answerisTrue)
        {
         toastmessageId=R.string.correct_answer;
        }
        else
            {
            toastmessageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this,toastmessageId,Toast.LENGTH_SHORT).show();
    }
}
