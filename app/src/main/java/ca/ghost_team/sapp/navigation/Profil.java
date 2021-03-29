package ca.ghost_team.sapp.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import ca.ghost_team.sapp.BaseApplication;
import ca.ghost_team.sapp.MainActivity;
import ca.ghost_team.sapp.R;
import ca.ghost_team.sapp.activity.AnnonceVendue;
import ca.ghost_team.sapp.activity.Login;
import ca.ghost_team.sapp.database.SappDatabase;
import ca.ghost_team.sapp.databinding.LayoutProfilBinding;
import ca.ghost_team.sapp.model.Utilisateur;

import static ca.ghost_team.sapp.BaseApplication.ID_USER_CURRENT;

public class Profil extends Fragment {
    private LayoutProfilBinding binding;
    private TextView infoNameUser;
    private TextView infoUsername;
    private TextView infoEmailUser;
    private SappDatabase db;
    private RelativeLayout logOutContainer;
    private RelativeLayout displayAnnonceVendueContainer;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_profil, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.db = Room.databaseBuilder(context, SappDatabase.class, BaseApplication.NAME_DB)
                .allowMainThreadQueries().build();
        ((MainActivity)context).setTitle(R.string.profil);

        mainActivity = (MainActivity) mainActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Binding Fields
        infoNameUser = binding.infoNameUser;
        infoUsername = binding.infoUsernameUser;
        infoEmailUser = binding.infoEmailUser;
        logOutContainer = binding.logoutContainer;
        displayAnnonceVendueContainer = binding.annonceVendueContainer;

        Utilisateur currentUser = db.utilisateurDao().getInfoUtilisateur(ID_USER_CURRENT);

        // Setter les Infos
        infoNameUser.setText(currentUser.getUtilisateurNom());
        infoUsername.setText(currentUser.getUsername());
        infoEmailUser.setText(currentUser.getEmail());

        //Log Out
        logOutContainer.setOnClickListener(this::logOut);

        // display my Annonce
        displayAnnonceVendueContainer.setOnClickListener(this::displayAnnonceVendue);

    }

    private void displayAnnonceVendue(View view) {
        Intent intentAnnonceVendue = new Intent(getContext(), AnnonceVendue.class);
        startActivity(intentAnnonceVendue);
    }

    private void logOut(View view) {
        ID_USER_CURRENT = 0;
        Intent intent = new Intent(getContext(), Login.class);

        startActivity(intent);
        Toast.makeText(getContext(),infoUsername.getText().toString() + " logged out...", Toast.LENGTH_SHORT).show();

        //on veut eviter que le back button nous ramene dans l'application
        mainActivity.finish();

    }


}
