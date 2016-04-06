package de.haohu.smartoutlet.manager;

import de.haohu.smartoutlet.model.AccessToken;
import de.haohu.smartoutlet.model.LoginEntry;

import java.io.BufferedReader;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by d058856 on 29/03/16.
 */
public class LoginManager {
    private static LoginManager ourInstance = new LoginManager();

    ConcurrentMap<AccessToken, LoginEntry> loginEntries = new ConcurrentHashMap<>();

    private LoginManager() {

    }

    public static LoginManager getInstance() {
        return ourInstance;
    }

    public boolean isValidAccessToken(AccessToken token) {
        if (token == null)
            return false;
        LoginEntry entry = loginEntries.get(token);
        if (entry == null) {
            return false;
        }
        return true;
    }


    public LoginEntry getLoginEntry(LoginEntry entry) {
        LoginEntry oldEntry = null;
        for (LoginEntry anEntry : this.loginEntries.values()) {
            if (anEntry.equals(entry)) {
                oldEntry = anEntry;
            }
        }
        return oldEntry;
    }

    public AccessToken addLoginEntry(LoginEntry entry) {

        LoginEntry oldEntry = this.getLoginEntry(entry);
        AccessToken accessToken = AccessToken.createToken();

        if (oldEntry != null) {
            //Revoke the old AccessToken.
            loginEntries.remove(oldEntry.getAccessToken());
            oldEntry.setCreationTime(new Date());
            oldEntry.setAccessToken(accessToken);
            loginEntries.put(accessToken, oldEntry);
        } else {
            entry.setCreationTime(new Date());
            entry.setAccessToken(accessToken);
            loginEntries.put(accessToken, entry);
        }

        return accessToken;
    }


    private void cleanUpTaks() {
        //The task should be schedule to clean up some old login entries.
    }
}
