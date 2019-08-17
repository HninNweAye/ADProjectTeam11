package sa48.team11.adproject.utils;

import android.app.Application;

import sa48.team11.adproject.models.Employee;

public class App extends Application {
    private Employee user;

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
}
