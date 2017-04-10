package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static artist.web.logicalreasoningquiz.LogicQuizActivity.USER_NAME;

/**
 * Created by User on 4/9/2017.
 */

public class PictoQuiz extends AppCompatActivity {

    EditText ques1, ques2, ques3, ques4, ques5;
    TextView timer, user_name;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picto_quiz_start);
        user_name = (TextView)findViewById(R.id.hello_user);
        timer = (TextView)findViewById(R.id.timing);

        ques1 =(EditText)findViewById(R.id.ques1);
        ques2 =(EditText)findViewById(R.id.ques2);
        ques3 =(EditText)findViewById(R.id.ques3);
        ques4 =(EditText)findViewById(R.id.ques4);
        ques5 =(EditText)findViewById(R.id.ques5);


        Intent intent = getIntent();
        name= intent.getStringExtra(USER_NAME);
        user_name.setText(name);


        CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("Timer: " +l / 1000 + " " + "sec");
            }

            @Override
            public void onFinish() {
                timer.setText("TIME OUT");
                Toast.makeText(PictoQuiz.this,"Time's Up!!" +"\n"+" Try Again!!", Toast.LENGTH_LONG);
                restart();

            }
        };
        countDownTimer.start();
    }

    public void showScore(View view){

        int score=0;

        String firstQues = ques1.getText().toString().toLowerCase();
        String secondQues = ques2.getText().toString().toLowerCase();
        String thirdQues = ques3.getText().toString().toLowerCase();
        String fourthQues = ques4.getText().toString().toLowerCase();
        String fifthQues = ques5.getText().toString().toLowerCase();

        if(firstQues.equals("hotdog")){
            score+=5;
        }
        if(secondQues.equals("cupcake")){
            score+=5;
        }
        if(thirdQues.equals("icebox")){
            score+=5;
        }
        if(fourthQues.equals("jellyfish")){
            score+=5;
        }
        if(fifthQues.equals("handshake")){
            score+=5;
        }

        showResult(score);

    }

    private void showResult(int score){

        if(score==0){
            Toast.makeText(this,"Come on " +name+ " You scored "+ score+ " You can do better, Try Again!! ",Toast.LENGTH_LONG).show();
        }
        else if(score>=5&&score<=10){
            Toast.makeText(this, name+ " You scored "+ score+ " Better Luck Next Time ",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this," You did well " +name+ " You scored "+ score,Toast.LENGTH_LONG).show();
        }

        restart();

    }

    private void restart(){
        Intent restartQuiz = new Intent(this,LogicQuizActivity.class);
        startActivity(restartQuiz);
    }
}
