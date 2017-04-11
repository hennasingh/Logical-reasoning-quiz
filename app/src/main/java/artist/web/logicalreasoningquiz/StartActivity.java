package artist.web.logicalreasoningquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static artist.web.logicalreasoningquiz.LogicQuizActivity.USER_NAME;
import static artist.web.logicalreasoningquiz.NumberQuiz.MY_PREFS_FILE;

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
        quiz_window.putExtra(USER_NAME,user_name);

        startActivity(quiz_window);

    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_FILE,MODE_PRIVATE).edit();
        editor.putString(USER_NAME, your_name.getText().toString());
        editor.commit();
    }

  }
