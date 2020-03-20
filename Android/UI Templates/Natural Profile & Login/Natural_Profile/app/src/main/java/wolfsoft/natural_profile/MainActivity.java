package wolfsoft.natural_profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.ausgirl);
//
//        ImageView imgv = (ImageView) findViewById(R.id.banar1);
//
//        //  Bitmap bitmap = StringToBitMap(imgv);
//        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//
//        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Paint paint = new Paint();
//        paint.setShader(shader);
//        paint.setAntiAlias(true);
//        Canvas c = new Canvas(circleBitmap);
//        c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
//
//        imgv.setImageBitmap(circleBitmap);

    }
}
