package com.example.internshiptest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView profileOptionsRecyclerView;
    private RecyclerView rewardsRecyclerView;
    private RecyclerView transactionsRecyclerView;

    private ProfileOptionsAdapter profileOptionsAdapter;
    private RewardsAdapter rewardsAdapter;
    private TransactionsAdapter transactionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile");

        TextView userName = findViewById(R.id.UserName);
        TextView userSince = findViewById(R.id.userSince);
        ImageButton editProfileBtn = findViewById(R.id.editProfileBtn);
        LinearLayout credGarage = findViewById(R.id.credGarage);

        userName.setText("andaz Kumar");
        userSince.setText("member since Dec, 2020");

        // Setup click listeners
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Edit profile clicked", Toast.LENGTH_SHORT).show();
            }
        });

        credGarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CRED garage clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize RecyclerViews
        initProfileOptionsRecyclerView();
        initRewardsRecyclerView();
        initTransactionsRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Back button pressed", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.action_support) {
            Toast.makeText(this, "Support button pressed", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initProfileOptionsRecyclerView() {
        profileOptionsRecyclerView = findViewById(R.id.rec_profile_options);
        profileOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ProfileOption> options = new ArrayList<>();
        options.add(new ProfileOption(R.drawable.ic_credit_score, "credit score", "757", true));
        options.add(new ProfileOption(R.drawable.ic_cashback, "lifetime cashback", "₹3", true));
        options.add(new ProfileOption(R.drawable.ic_bank, "bank balance", "check", true));

        profileOptionsAdapter = new ProfileOptionsAdapter(options);
        profileOptionsRecyclerView.setAdapter(profileOptionsAdapter);
    }

    private void initRewardsRecyclerView() {
        rewardsRecyclerView = findViewById(R.id.rec_reward);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rewardsRecyclerView.setLayoutManager(layoutManager);

        List<Reward> rewards = new ArrayList<>();
        rewards.add(new Reward("cashback balance", "₹0"));
        rewards.add(new Reward("coins", "26,46,583"));
        rewards.add(new Reward("win upto Rs 1000", "refer and earn"));

        rewardsAdapter = new RewardsAdapter(rewards);
        rewardsRecyclerView.setAdapter(rewardsAdapter);
    }

    private void initTransactionsRecyclerView() {
        transactionsRecyclerView = findViewById(R.id.rec_transaction);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        transactionsRecyclerView.setLayoutManager(layoutManager);

        List<String> transactions = new ArrayList<>();
        transactions.add("all transactions");
        transactions.add("support");

        transactionsAdapter = new TransactionsAdapter(transactions);
        transactionsRecyclerView.setAdapter(transactionsAdapter);
    }

    // Model classes
    public static class ProfileOption {
        private int iconRes;
        private String title;
        private String value;
        private boolean showArrow;

        public ProfileOption(int iconRes, String title, String value, boolean showArrow) {
            this.iconRes = iconRes;
            this.title = title;
            this.value = value;
            this.showArrow = showArrow;
        }

        public int getIconRes() {
            return iconRes;
        }

        public String getTitle() {
            return title;
        }

        public String getValue() {
            return value;
        }

        public boolean isShowArrow() {
            return showArrow;
        }
    }

    public static class Reward {
        private String title;
        private String value;

        public Reward(String title, String value) {
            this.title = title;
            this.value = value;
        }

        public String getTitle() {
            return title;
        }

        public String getValue() {
            return value;
        }
    }

    // Adapter classes
    public class ProfileOptionsAdapter extends RecyclerView.Adapter<ProfileOptionsAdapter.ViewHolder> {

        private List<ProfileOption> options;

        public ProfileOptionsAdapter(List<ProfileOption> options) {
            this.options = options;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_profile_options, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ProfileOption option = options.get(position);
            holder.icon.setImageResource(option.getIconRes());
            holder.title.setText(option.getTitle());
            holder.value.setText(option.getValue());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, option.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return options.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView title;
            TextView value;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.icon);
                title = itemView.findViewById(R.id.profiletitle);
                value = itemView.findViewById(R.id.profilevalue);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.ViewHolder> {

        private List<Reward> rewards;

        public RewardsAdapter(List<Reward> rewards) {
            this.rewards = rewards;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_reward_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Reward reward = rewards.get(position);
            holder.title.setText(reward.getTitle());
            holder.value.setText(reward.getValue());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, reward.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return rewards.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView value;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.rewardTitle);
                value = itemView.findViewById(R.id.rewardValue);
            }
        }
    }

    public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

        private List<String> transactions;

        public TransactionsAdapter(List<String> transactions) {
            this.transactions = transactions;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_transaction_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String transaction = transactions.get(position);
            holder.title.setText(transaction);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, transaction + " clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return transactions.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.textTitle);
            }
        }
    }
}