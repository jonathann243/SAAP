package ca.ghost_team.sapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import java.util.List;

import ca.ghost_team.sapp.dao.AnnonceDao;
import ca.ghost_team.sapp.database.sappDatabase;
import ca.ghost_team.sapp.model.Annonce;

public class AnnonceRepo {
    private final AnnonceDao dao;
    private final LiveData<List<Annonce>> AllAnnonces;


    public AnnonceRepo(Application app){
        sappDatabase database = sappDatabase.getInstance(app);
        dao= database.annonceDao();
        AllAnnonces = dao.AllAnnonces();
    }

    public LiveData<List<Annonce>> getAllAnnonces() {
        return AllAnnonces;
    }

    public void insertAnnonce(Annonce annonce){
        new InsertAnnonceAsyncTask(dao).execute(annonce);
    }

    public void insertAllAnnonce(Annonce... annonce){ new InsertAllAnnonceAsyncTask(dao).execute(annonce);}

    public void updateAnnonce(Annonce annonce){
        new UpdateAnnonceAsyncTask(dao).execute(annonce);
    }

    public void deleteAllAnnonce(){
        new DeleteAllAnnoncesAsyncTask(dao).execute();
    }

    public void deleteAnnonce(Annonce annonce){
        new DeleteAnnonceAsyncTask(dao).execute(annonce);
    }

    public void updateLiked(int id, boolean etat){
        new UpdateLikedAsyncTask(dao).execute(id, etat);
    }

    public List<Annonce> findAnnonceByUser(int idUtilisateur){
        return (List<Annonce>) new findAnnonceByUserAsyncTask(dao).execute(idUtilisateur);
    }

    private static class InsertAnnonceAsyncTask extends AsyncTask<Annonce,Void,Void>{

        private AnnonceDao uneAnnonceDao;
        private InsertAnnonceAsyncTask(AnnonceDao dao) {
            this.uneAnnonceDao= dao;
        }

        @Override
        protected Void doInBackground(Annonce... annonces) {
            uneAnnonceDao.insertAnnonce(annonces[0]);
            return null;
        }
    }

    private static class InsertAllAnnonceAsyncTask extends AsyncTask<Annonce,Void,Void>{

        private AnnonceDao uneAnnonceDao;
        private InsertAllAnnonceAsyncTask(AnnonceDao dao) {
            this.uneAnnonceDao= dao;
        }

        @Override
        protected Void doInBackground(Annonce... annonces) {
            uneAnnonceDao.insertAllAnnonces(annonces);
            return null;
        }
    }

    private static class DeleteAnnonceAsyncTask extends AsyncTask<Annonce,Void,Void>{

        private AnnonceDao uneAnnonceDao;
        private DeleteAnnonceAsyncTask(AnnonceDao dao) {
            this.uneAnnonceDao= dao;
        }

        @Override
        protected Void doInBackground(Annonce... annonces) {
            uneAnnonceDao.deleteAnnonce(annonces[0]);
            return null;
        }
    }

    private static class DeleteAllAnnoncesAsyncTask extends AsyncTask<Void,Void,Void>{

        private AnnonceDao uneAnnonceDao;
        private DeleteAllAnnoncesAsyncTask(AnnonceDao dao) {
            this.uneAnnonceDao= dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            uneAnnonceDao.deleteAllAnnonce();
            return null;
        }
    }

    private static class UpdateAnnonceAsyncTask extends AsyncTask<Annonce,Void,Void>{

        private AnnonceDao uneAnnonceDao;
        private UpdateAnnonceAsyncTask(AnnonceDao dao) {
            this.uneAnnonceDao= dao;
        }

        @Override
        protected Void doInBackground(Annonce... annonces) {
            uneAnnonceDao.updateAnnonce(annonces[0]);
            return null;
        }
    }

    private static class UpdateLikedAsyncTask extends AsyncTask<Object,Void,Void>{

        private AnnonceDao uneAnnonceDao;
        private UpdateLikedAsyncTask(AnnonceDao dao) {
            this.uneAnnonceDao= dao;
        }

        @Override
        protected Void doInBackground(Object... Objects) {
            uneAnnonceDao.updateLiked((int)Objects[0], (boolean)Objects[1]);
            return null;
        }
    }

    static class findAnnonceByUserAsyncTask extends AsyncTask<Integer,Void,List<Annonce>>{

        private AnnonceDao uneAnnonceDao;
        findAnnonceByUserAsyncTask(AnnonceDao dao) {
            this.uneAnnonceDao= dao;
        }

        @Override
        protected List<Annonce> doInBackground(Integer... id) {
            uneAnnonceDao.findAnnonceByUser(id[0]);
            return null;
        }

    }

}
