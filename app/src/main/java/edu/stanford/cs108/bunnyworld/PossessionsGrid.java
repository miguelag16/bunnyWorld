package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by miguelgarcia on 3/15/17.
 */

public class PossessionsGrid extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("ADENTRO");
        super.onCreate(savedInstanceState);
        System.out.println("ADENTRO2222");
        setContentView(R.layout.possessions_view);
        System.out.println("aaaaaaaa");
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        System.out.println("bbbbbbbb");
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(PossessionsGrid.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private final int iconDimension = 200;
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
//            System.out.println(position);
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.dolan, R.drawable.dolan,
                R.drawable.dolan, R.drawable.dolan,
                R.drawable.dolan, R.drawable.dolan,
                R.drawable.dolan, R.drawable.dolan,
                R.drawable.dolan, R.drawable.dolan,
        };
    }

}
