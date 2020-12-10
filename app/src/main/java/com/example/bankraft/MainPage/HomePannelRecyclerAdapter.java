package com.example.bankraft.MainPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankraft.Notification.NotificationRecyclerItem;
import com.example.bankraft.R;

import java.util.ArrayList;

public class HomePannelRecyclerAdapter extends RecyclerView.Adapter<HomePannelRecyclerAdapter.ViewHolder> {
    private ArrayList<HomePannelRecyclerItem> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    HomePannelRecyclerAdapter(ArrayList<HomePannelRecyclerItem> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public HomePannelRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.home_pannel_list_item, parent, false);
        HomePannelRecyclerAdapter.ViewHolder vh = new HomePannelRecyclerAdapter.ViewHolder(view);

        return vh;
    }

    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(HomePannelRecyclerAdapter.ViewHolder holder, int position) {

        HomePannelRecyclerItem item = mData.get(position);

        holder.img.setImageDrawable(item.getImg());
        holder.content.setText(item.getHome_context());

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView content;


        ViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(view1 -> {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    if (mListener != null) {
                        mListener.onItemClick(view1, position);
                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            img = itemView.findViewById(R.id.home_item_img);
            content = itemView.findViewById(R.id.home_item_content);

        }
    }
}
