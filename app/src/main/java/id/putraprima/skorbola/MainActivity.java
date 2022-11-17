package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btn_next;
    private EditText et_awayteam,et_hometeam;
    private ImageView awayLogo,homeLogo;
    private Uri awayImg,homeImg;
    private  int HOME_REQUEST_CODE = 1;
    private int AWAY_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        btn_next = findViewById(R.id.btn_team);
        et_awayteam = findViewById(R.id.away_team);
        et_hometeam = findViewById(R.id.home_team);
        homeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });
        awayLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hometeam = et_hometeam.getText().toString();
                String awayteam = et_awayteam.getText().toString();

                Intent i = new Intent(MainActivity.this, MatchActivity.class);
                i.putExtra("home", hometeam);
                i.putExtra("away", awayteam);
                i.putExtra("homeimg", homeImg.toString() );
                i.putExtra("awayimg", awayImg.toString() );
                startActivity(i);
            }
        });


            //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Log.d(TAG, "Pilih gambar dicancel");
        } else if (requestCode == HOME_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    homeImg =  imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeImg);
                    homeLogo.setImageBitmap(bitmap);
                }
                catch(IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,error.getMessage());
                }
            }
        } else if (requestCode == AWAY_REQUEST_CODE){
            try {
                Uri imageUri = data.getData();
                awayImg =  imageUri;
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayImg);
                awayLogo.setImageBitmap(bitmap);
            } catch (IOException error){
                Toast.makeText(this,"Can't load image", Toast.LENGTH_SHORT).show();
                Log.e(TAG,error.getMessage());
            }
        }
    }


}
