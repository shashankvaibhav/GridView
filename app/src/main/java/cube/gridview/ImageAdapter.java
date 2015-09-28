package cube.gridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ImageAdapter extends BaseAdapter{

    public static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    Bitmap mBitmap;
    int mPosition;
    int width;
    int height;

    public ImageAdapter(Context context,int w,int h){

        mContext = context;
        width = w;
        height = h;
    }
    public ImageAdapter(Context context,Bitmap bitMap,int position,int w,int h){
        mContext = context;
        mBitmap = bitMap;
        mPosition = position;
        width= w;
        height = h;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null ){
            //Brand New
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_item , null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.imageView);
            if(position != 4) {
                holder.iconImageView.setLayoutParams(new LinearLayout.LayoutParams(width / 2, height / 3));
            }
            else{
                holder.iconImageView.setLayoutParams(new LinearLayout.LayoutParams(width, height / 3));
            }
            holder.iconImageView.setScaleType(ImageView.ScaleType.CENTER);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        if(mPosition == position) {
            Log.v(TAG,"Position Clicked ="+mPosition);
            holder.iconImageView.setImageBitmap(mBitmap);
        }
        return convertView;


    }
    private static class ViewHolder{
        ImageView iconImageView;
    }
}
