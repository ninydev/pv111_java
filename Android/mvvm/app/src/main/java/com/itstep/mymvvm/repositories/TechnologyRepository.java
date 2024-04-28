package com.itstep.mymvvm.repositories;

import com.itstep.mymvvm.models.TechnologyModel;

import java.util.ArrayList;

public class TechnologyRepository {

    private void seed(){
        technologies.add(new TechnologyModel("Java", 30));
        technologies.add(new TechnologyModel("C++", 50));
    }

    private final ArrayList<TechnologyModel> technologies;

    public ArrayList<TechnologyModel> getTechnologies() {
        return technologies;
    }

    private TechnologyRepository() {
        technologies = new ArrayList<>();
        seed();
    }

    private static TechnologyRepository instance;

    public static TechnologyRepository getInstance() {
        if(instance == null)
            instance = new TechnologyRepository();

        return instance;
    }

    public static TechnologyModel createModel () {
        return  new TechnologyModel();
    }

}
