package de.haohu.smartoutlet.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * AccessToken is granted to the device as a key to use API.
 */
public class AccessToken {


    public String getAccessTokenValue() {
        return accessTokenValue;
    }

    public void setAccessTokenValue(String accessTokenValue) {
        this.accessTokenValue = accessTokenValue;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    private String accessTokenValue;
    private Date creationTime;


    public static AccessToken createToken(){
        UUID uuid = UUID.randomUUID();
        AccessToken token = new AccessToken();
        token.accessTokenValue = uuid.toString();
        token.creationTime = new Date();
        return token;
    }

    public static AccessToken createToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null){
            return null;
        }

        String accessToken = null;
        for (Cookie cookie : cookies){
            if ("access_token".equals(cookie.getName())){
                accessToken = cookie.getValue();
            }
        }

        AccessToken token = null;
        if (accessToken != null){
            token = new AccessToken();
            token.accessTokenValue = accessToken;
        }
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessToken that = (AccessToken) o;

        return accessTokenValue.equals(that.accessTokenValue);

    }

    @Override
    public int hashCode() {
        return accessTokenValue.hashCode();
    }
}
