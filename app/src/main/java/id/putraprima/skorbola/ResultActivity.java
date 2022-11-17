package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    private String s_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.win_team);
        s_result = getIntent().getExtras().getString("winner");
        result.setText(s_result);

        result.setText(getIntent().getExtras().getString("winner"));
    }
}
