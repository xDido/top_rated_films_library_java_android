package com.example.recyclerdemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class AdvanceAdapter extends ArrayAdapter {
    ArrayList<Movie> models = new ArrayList<>();
    Context context;
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";


    public AdvanceAdapter(Context context, ArrayList<Movie> data) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1);
        this.context = context;
        models = data;
    }

    public AdvanceAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1);
        this.context = context;
    }

    @Override
    public int getCount() {
        int size = models == null ? 0 : models.size();
        return size;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        MyViewHolder myViewHolder;
        if (rowView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = layoutInflater.inflate(R.layout.list_item, parent, false);
            myViewHolder = new MyViewHolder(rowView);
            rowView.setTag(myViewHolder);
        } else
            myViewHolder = (MyViewHolder) rowView.getTag();

        TextView headerText = myViewHolder.getHeader();
        TextView subText = myViewHolder.getSub();
        ImageView image = myViewHolder.getImage();
        Movie movie=models.get(position);
        headerText.setText(movie.getTitle());
        subText.setText(movie.getRelease_date());
        Picasso.with(context).load(IMAGE_BASE_URL+movie.getPoster_path()).into(image);
        return rowView;
    }
    class MyViewHolder {
        private View view;
        private ImageView image;
        private TextView header;
        private TextView sub;

        public MyViewHolder(View view) {
            this.view = view;
        }

        public ImageView getImage() {
            if (image == null)
                image = (ImageView) view.findViewById(R.id.imageView);
            return image;
        }

        public TextView getHeader() {
            if (header == null)
                header = (TextView) view.findViewById(R.id.header);
            return header;
        }

        public TextView getSub() {
            if (sub == null)
                sub = (TextView) view.findViewById(R.id.sub);
            return sub;
        }
    }

    public void setData(ArrayList<Movie> data) {
        if (models != null) {
            models.clear();
            models.addAll(data);
            notifyDataSetChanged();
        }
    }

    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
