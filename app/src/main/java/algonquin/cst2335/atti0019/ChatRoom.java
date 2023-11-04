package algonquin.cst2335.atti0019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.atti0019.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.atti0019.databinding.ReceiveRowBinding;
import algonquin.cst2335.atti0019.databinding.SentRowBinding;

public class ChatRoom extends AppCompatActivity {

    ArrayList<String> theMessages = null;
    ActivityChatRoomBinding binding;
    RecyclerView.Adapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.myRecycleView.setLayoutManager(new LinearLayoutManager(this));

        MessagesViewModel chatModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        theMessages = chatModel.theMessages;

        binding.send.setOnClickListener(click -> {
            String newMessage = binding.newMessage.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(newMessage, currentDateandTime, true);
            theMessages.add(newMessage);
            binding.newMessage.setText("");
            myAdapter.notifyDataSetChanged();
            binding.myRecycleView.scrollToPosition(theMessages.size() - 1);
        });

        binding.receive.setOnClickListener(click -> {
            String newMessage = binding.newMessage.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage chatMessage = new ChatMessage(newMessage, currentDateandTime, false);
            theMessages.add(newMessage);
            binding.newMessage.setText("");
            myAdapter.notifyDataSetChanged();
            binding.myRecycleView.scrollToPosition(theMessages.size() - 1);
        });

        binding.myRecycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView;
                if (viewType == 0) {
                    SentRowBinding rowBinding = SentRowBinding.inflate(getLayoutInflater(), parent, false);
                    itemView = rowBinding.getRoot();
                } else {
                    ReceiveRowBinding rowBinding = ReceiveRowBinding.inflate(getLayoutInflater(), parent, false);
                    itemView = rowBinding.getRoot();
                }
                return new MyRowHolder(itemView);
            }

            @Override
            public int getItemViewType(int position) {
                // Use position to determine whether the message is sent or received
                if (position % 2 == 0) {
                    return 0; // Sent message
                } else {
                    return 1; // Received message
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                String forRow = theMessages.get(position);
                holder.message.setText(forRow);
                holder.time.setText("Time for row " + position);
            }

            @Override
            public int getItemCount() {
                return theMessages.size();
            }
        });
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        public TextView message;
        public TextView time;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
        }
    }
}
