package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    EditText your_name;
    String user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        your_name = (EditText)findViewById(R.id.your_name);
    }

    public void startQuiz(View view){

          user_name = your_name.getText().toString();
        if (user_name.isEmpty()) {
            Toast.makeText(this, "Please enter your name "+"\n"+"You wont like being called anonymous!! :P",
                    Toast.LENGTH_LONG).show();
            return;
        } else {
            Toast.makeText(this, "Hello, " + user_name + "!" + "\n"+getString(R.string.welcome_message),
                    Toast.LENGTH_SHORT).show();
        }
        Intent quiz_window= new Intent(this,LogicQuizActivity.class);
        quiz_window.putExtra(LogicQuizActivity.USER_NAME,user_name);

        startActivity(quiz_window);

    }

  }
