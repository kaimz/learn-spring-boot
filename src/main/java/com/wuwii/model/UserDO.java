package com.wuwii.model;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/1/26 16:23</pre>
 */
public class UserDO extends BaseDO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id" + super.getId() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
