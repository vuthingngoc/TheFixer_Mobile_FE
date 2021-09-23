package com.example.thefixer.ui.inbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.thefixer.databinding.FragmentActivityBinding;
import com.example.thefixer.databinding.FragmentInboxBinding;

public class InboxFragment extends Fragment {

    private InboxViewModel inboxViewModel;
    private FragmentInboxBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        inboxViewModel =
                new ViewModelProvider(this).get(InboxViewModel.class);

    binding = FragmentInboxBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textInbox;
        inboxViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}