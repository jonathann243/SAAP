package ca.ghost_team.sapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import ca.ghost_team.sapp.model.Annonce;

@Dao
public interface AnnonceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllAnnonces(Annonce... annonce);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnnonce(Annonce annonce);

    @Query("SELECT * FROM annonceTable ORDER BY date_annonce")
    LiveData<List<Annonce>> AllAnnonces();

    // Requête qui permet de récuperer toutes les annonces par rapport à un Utilisateur
    @Query("SELECT * FROM annonceTable WHERE utilisateurId = :utilisateurId")
    LiveData<List<Annonce>> findAnnonceByUser(final int utilisateurId);

    @Delete
    void deleteAnnonce(Annonce annonce);

    @Update
    void updateAnnonce(Annonce annonce);

    @Query("DELETE FROM annonceTable")
    void deleteAllAnnonce();

    @Query("UPDATE annonceTable SET liked_annonce = :etat WHERE idAnnonce = :id")
    void updateLiked(int id, boolean etat);



}
