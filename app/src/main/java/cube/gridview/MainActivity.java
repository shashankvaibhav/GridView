package cube.gridview;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    GridView gridView;
    public static final int PICK_PHOTO_REQUEST = 1;
    protected Uri mMediaUri;
    Bitmap bitmap;
    int mPosition;
    DisplayMetrics mDisplayMetrics;
    int width ;
    int height ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //size of screen
        mDisplayMetrics = getResources().getDisplayMetrics();
        width= mDisplayMetrics.widthPixels;
        height = mDisplayMetrics.heightPixels;

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this,width,height));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Choose a Photo",Toast.LENGTH_LONG).show();

                Intent choosePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                choosePhotoIntent.setType("image/*");
                mPosition=position;
                startActivityForResult(choosePhotoIntent, PICK_PHOTO_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (requestCode == PICK_PHOTO_REQUEST) {
                if (data == null) {
                    Toast.makeText(this, "Error getting photo", Toast.LENGTH_LONG).show();
                }
                else {
                    mMediaUri = data.getData();
                    Log.v(TAG, String.valueOf(mMediaUri));
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),mMediaUri);
                        gridView.setAdapter(new ImageAdapter(this,bitmap,mPosition,width,height));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }
}
