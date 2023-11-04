package algonquin.cst2335.atti0019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;

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
                theMessages.add(newMessage); // Add the new message to the end of the list
                binding.newMessage.setText("");
                myAdapter.notifyDataSetChanged();
                
            });

        binding.myRecycleView.setAdapter( myAdapter = new RecyclerView.Adapter<MyRowHolder>() {



            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                if (viewType == 0) {
                    SentRowBinding rowBinding = SentRowBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(rowBinding.getRoot());
                } else {
                    ReceiveRowBinding rowBinding = ReceiveRowBinding.inflate(getLayoutInflater() , parent, false);
                    return new MyRowHolder((rowBinding.getRoot()));
                }
            }

            @Override
            public int getItemViewType(int position) {
                if (position < 3)
                    return 0;
                else
                    return 1;
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                String forRow = theMessages.get(position);
                holder.message.setText(forRow);
                holder.time.setText("Time for row" + position);


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