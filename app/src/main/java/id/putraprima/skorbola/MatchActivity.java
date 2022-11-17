package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private String hometeam,awayteam, winner;
    private int homeScore, awayScore;
    private TextView scoreHome,scoreAway,homeText,awayText;
    private ImageView homeLogo,awayLogo;
    private Button addHomeScore,addHomeScore2,addHomeScore3,addAwayScore,addAwayScore2,addAwayScore3,cekResult,resetResult;
    private String s_hometeam, s_awayteam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        scoreHome = findViewById(R.id.score_home);
        scoreAway = findViewById(R.id.score_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        addHomeScore = findViewById(R.id.btn_add_home);
        addHomeScore2 = findViewById(R.id.btn_add_home2);
        addHomeScore3 = findViewById(R.id.btn_add_home3);
        addAwayScore = findViewById(R.id.btn_add_away);
        addAwayScore2 = findViewById(R.id.btn_add_away2);
        addAwayScore3 = findViewById(R.id.btn_add_away3);
        resetResult =  findViewById(R.id.btn_reset);
        cekResult = findViewById(R.id.btn_result);

        homeScore = 0;
        awayScore = 0;
        scoreHome.setText(String.valueOf(homeScore));
        scoreAway.setText(String.valueOf(awayScore));

        s_hometeam = getIntent().getExtras().getString("home");
        s_awayteam = getIntent().getExtras().getString("away");

        Bundle bundle = getIntent().getExtras();
        homeText.setText(s_hometeam);
        awayText.setText(s_awayteam);
        homeLogo.setImageURI(Uri.parse(bundle.getString("homeimg")));
        awayLogo.setImageURI(Uri.parse(bundle.getString("awayimg")));



        addHomeScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 1;
                scoreHome.setText(String.valueOf(homeScore));
            }
        });
        addHomeScore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 2;
                scoreHome.setText(String.valueOf(homeScore));
            }
        });
        addHomeScore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore += 3;
                scoreHome.setText(String.valueOf(homeScore));
            }
        });

        addAwayScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 1;
                scoreAway.setText(String.valueOf(awayScore));
            }
        });
        addAwayScore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 2;
                scoreAway.setText(String.valueOf(awayScore));
            }
        });
        addAwayScore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore += 3;
                scoreAway.setText(String.valueOf(awayScore));
            }
        });
        resetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore =0 ;
                scoreHome.setText(String.valueOf(homeScore));

                awayScore = 0;
                scoreAway.setText(String.valueOf(awayScore));

            }
        });
        cekResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winner = "empty";
                if(homeScore > awayScore){
                    winner = s_hometeam;
                } else if (homeScore == awayScore){
                    winner = "DRAW";
                } else {
                    winner = s_awayteam;
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", winner);
                startActivity(intent);
            }
        });
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }
}
