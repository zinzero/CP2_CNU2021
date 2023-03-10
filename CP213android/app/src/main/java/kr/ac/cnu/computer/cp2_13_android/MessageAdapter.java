package kr.ac.cnu.computer.cp2_13_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> list;

    public MessageAdapter(List<Message> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (Objects.requireNonNull(MessageType.of(viewType))) {
            case LEFT_CONTENTS:
                return new LeftViewHolder(inflater.inflate(R.layout.message_left_item, parent, false));
            case RIGHT_CONTENTS:
                return new RightViewHolder(inflater.inflate(R.layout.message_right_item, parent, false));
            default:
                return new CenterViewHolder(inflater.inflate(R.layout.message_center_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = list.get(position);
        if (holder instanceof LeftViewHolder) {
            ((LeftViewHolder) holder).messageText.setText(message.getMessage());
            ((LeftViewHolder) holder).datetimeText.setText(message.getRegisterDate());
        } else if (holder instanceof RightViewHolder) {
            ((RightViewHolder) holder).messageText.setText(message.getMessage());
            ((RightViewHolder) holder).datetimeText.setText(message.getRegisterDate());
        } else {
            ((CenterViewHolder) holder).messageText.setText(message.getMessage());
            ((CenterViewHolder) holder).datetimeText.setText(message.getRegisterDate());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ??? ???????????? ????????? ?????? onCreateViewHolder ???????????? ????????? ???????????? viewType ????????? ??? ???????????? ???????????? ????????????.
    @Override
    public int getItemViewType(int position) {
        // enum ??????????????? ordianl() ???????????? ????????? ?????????
        // 0: ????????????, 1: ????????? ??????, 2: ????????? ??????
        return list.get(position).getMessageType().getCode();
    }

    static class LeftViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView datetimeText;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            datetimeText = itemView.findViewById(R.id.datetimeText);
        }
    }

    static class CenterViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView datetimeText;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            datetimeText = itemView.findViewById(R.id.datetimeText);
        }
    }

    private class RightViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView datetimeText;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            datetimeText = itemView.findViewById(R.id.datetimeText);
        }
    }
}
