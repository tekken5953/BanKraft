package com.example.bankraft.DoTradingPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankraft.Notification.NotificationRecyclerItem;
import com.example.bankraft.R;

import java.util.ArrayList;

public class TradingListAdapter extends RecyclerView.Adapter<TradingListAdapter.ViewHolder> {
    private ArrayList<TradingListItem> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    TradingListAdapter(ArrayList<TradingListItem> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NonNull
    @Override
    public TradingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.do_tading_list_item, parent, false);
        TradingListAdapter.ViewHolder vh = new TradingListAdapter.ViewHolder(view);

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
    public void onBindViewHolder(TradingListAdapter.ViewHolder holder, int position) {

        TradingListItem item = mData.get(position);

        holder.person.setText(item.getPerson());
        holder.account.setText(item.getAccount());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView person, account;

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
            person = itemView.findViewById(R.id.do_trading_name);
            account = itemView.findViewById(R.id.do_trading_account);

        }
    }
}
