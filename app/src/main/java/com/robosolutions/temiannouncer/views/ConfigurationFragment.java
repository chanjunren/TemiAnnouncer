package com.robosolutions.temiannouncer.views;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.robosolutions.temiannouncer.R;

public class ConfigurationFragment extends Fragment {
    private ImageView backBtn;
    private NavController navController;
    private Button logoutBtn;
    private GoogleSignInClient mGoogleSignInClient;

    public ConfigurationFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this.getActivity(), gso);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(getParentFragment());
        backBtn = view.findViewById(R.id.confBackBtn);
        logoutBtn = view.findViewById(R.id.signOutBtn);

        logoutBtn.setOnClickListener(v -> {
            signOut();
        });

        backBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_confFragment_to_homeFragment);
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            System.out.printf("=== Details ===\n%s | %s | %s | %s | %s | %s\n", personName,
                    personGivenName, personFamilyName, personEmail, personId,
                    "supposed to be uri");
            ImageView imageView = (ImageView) view.findViewById(R.id.my_image_view);

            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
        }
    }

    private void signOut() {
        System.out.println("hi");
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), task -> {
                    System.out.println("yes i should appear");
                    navController.navigate(R.id.action_confFragment_to_signinFragment);
                    Toast.makeText(getContext(),
                            "Signed out successfully!", Toast.LENGTH_LONG).show();
                });
    }
}