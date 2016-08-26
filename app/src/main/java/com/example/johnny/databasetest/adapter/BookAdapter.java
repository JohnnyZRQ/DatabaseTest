package com.example.johnny.databasetest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.johnny.databasetest.R;
import com.example.johnny.databasetest.model.Book;

import java.util.List;

/**
 * Created by Johnny on 2016/8/17.
 */

public class BookAdapter extends ArrayAdapter<Book>  {
    private int resourceId;
    private Context context;


    public BookAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Book book = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.tvBookId = (TextView) view.findViewById(R.id.tv_book_id);
            viewHolder.tvBookName = (TextView) view.findViewById(R.id.tv_book_name);
            viewHolder.tvBookAuthor = (TextView) view.findViewById(R.id.tv_book_author);
            viewHolder.tvBookPages = (TextView) view.findViewById(R.id.tv_book_pages);
            viewHolder.tvBookPrice = (TextView) view.findViewById(R.id.tv_book_price);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvBookId.setText(""+book.getId());
        viewHolder.tvBookName.setText(book.getName());
        viewHolder.tvBookAuthor.setText(book.getAuthor());
        viewHolder.tvBookPages.setText(""+book.getPages());
        viewHolder.tvBookPrice.setText(""+book.getPrice());

        return view;
    }

    class ViewHolder {   //对控件的实例进行缓存
        TextView tvBookId;
        TextView tvBookName;
        TextView tvBookAuthor;
        TextView tvBookPages;
        TextView tvBookPrice;
    }
}
