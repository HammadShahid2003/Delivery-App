package com.example.navigation_smd_7a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    int resource;
    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null)
        {
            v = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView tvTitle = v.findViewById(R.id.tvProductTitle);
        ImageView ivEdit = v.findViewById(R.id.ivEdit);
        ImageView ivDelete = v.findViewById(R.id.ivDelete);
        TextView tvPrice=v.findViewById(R.id.tvProductPrice);

        Product p = getItem(position);

            tvTitle.setText(p.getTitle());
            tvPrice.setText(""+p.getPrice());
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductDB db = new ProductDB(context);
                    db.open();
                    db.remove(p.getId());
                    db.close();
                    remove(p);
                    notifyDataSetChanged();
                }
            });
            ImageView ivSchedule = v.findViewById(R.id.ivschedule);
            ivSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (p.getStatus().equals("new")) {
                        ProductDB db = new ProductDB(context);
                        db.open();
                        db.updateStatus(p.getId(), "schedule");
                        db.close();
                        remove(p);
                        notifyDataSetChanged();
                    }
                }
            });

        return v;



    }
}
