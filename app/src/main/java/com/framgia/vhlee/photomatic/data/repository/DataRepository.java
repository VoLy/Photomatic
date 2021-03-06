package com.framgia.vhlee.photomatic.data.repository;

import android.net.Uri;

import com.framgia.vhlee.photomatic.data.model.Post;
import com.framgia.vhlee.photomatic.data.model.User;
import com.framgia.vhlee.photomatic.data.source.DataSource;
import com.framgia.vhlee.photomatic.data.source.local.LocalCallback;
import com.framgia.vhlee.photomatic.data.source.local.LocalDataSource;
import com.framgia.vhlee.photomatic.data.source.remote.RemoteDataSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;

public class DataRepository implements DataSource.Remote, DataSource.Local {
    private static DataRepository sDataRepository;
    private DataSource.Remote mRemote;
    private DataSource.Local mLocal;

    public DataRepository() {
        mRemote = RemoteDataSource.newInstance();
        mLocal = LocalDataSource.newInstance();
    }

    public static DataRepository newInstance() {
        if (sDataRepository == null) sDataRepository = new DataRepository();
        return sDataRepository;
    }

    @Override
    public void checkSate(FirebaseAuth.AuthStateListener listener) {
        mRemote.checkSate(listener);
    }

    @Override
    public void login(String email, String password, OnCompleteListener listener) {
        mRemote.login(email, password, listener);
    }

    @Override
    public void register(String email, String password, OnCompleteListener listener) {
        mRemote.register(email, password, listener);
    }

    @Override
    public void reAuthenticate(String password, OnCompleteListener listener) {
        mRemote.reAuthenticate(password, listener);
    }

    @Override
    public void updateUserData(User user, OnCompleteListener listener) {
        mRemote.updateUserData(user, listener);
    }

    @Override
    public void getUserData(String userId, ValueEventListener listener) {
        mRemote.getUserData(userId, listener);
    }

    @Override
    public void setPassword(String newPassword, OnCompleteListener listener) {
        mRemote.setPassword(newPassword, listener);
    }

    @Override
    public void savePost(Post post, OnCompleteListener listener) {
        mRemote.savePost(post, listener);
    }

    @Override
    public void uploadPhoto(Uri uri, OnSuccessListener<UploadTask.TaskSnapshot> successListener,
                            OnFailureListener failureListener) {
        mRemote.uploadPhoto(uri, successListener, failureListener);
    }

    @Override
    public void getLocalPhotos(String path, LocalCallback<String> callback) {
        mLocal.getLocalPhotos(path, callback);
    }
}
