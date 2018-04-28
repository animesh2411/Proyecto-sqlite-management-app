package com.example.shubham.proyecto;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * Created by SHUBHAM on 15-07-2017.
 */

//public class MyAdapter extends BaseAdapter
//
//{
//    private List<StudentBean> mylist;
//    //String name;
//
//    private LayoutInflater lf = null;
//    Context ctx = null;
//
//    public MyAdapter(Activity activity, List<Fruit> mylist) {
//        ctx = activity.getApplicationContext();
//        this.mylist = mylist;
//        lf = LayoutInflater.from(activity);
//    }
//
//    @Override
//    public int getCount() {
//        return mylist.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mylist.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Toast.makeText(ctx, "view created", Toast.LENGTH_LONG).show();
//        if (convertView == null) {
//            convertView = lf.inflate(R.layout.mycustom, parent, false);
//        }
//        TextView tv = (TextView) convertView.findViewById(R.id.tv);
//        ImageView im = (ImageView) convertView.findViewById(R.id.img1);
//        Fruit f = mylist.get(position);
//        tv.setText(f.getName());
//
//
//                            /* byte[]arr=   f.getHodPic();
//                            ByteArrayInputStream bs=new ByteArrayInputStream(arr);
//
//                            Bitmap bm=BitmapFactory.decodeStream(bs);
//                            im.setImageBitmap(bm);*/
//        im.setImageResource(f.getId());
//
//
//        return convertView;
//
//
//    }
//}
