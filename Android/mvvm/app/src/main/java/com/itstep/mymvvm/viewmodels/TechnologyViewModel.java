package com.itstep.mymvvm.viewmodels;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itstep.mymvvm.MainActivity;
import com.itstep.mymvvm.R;
import com.itstep.mymvvm.adapters.TechnologyListViewAdapter;
import com.itstep.mymvvm.models.TechnologyModel;
import com.itstep.mymvvm.repositories.TechnologyRepository;

public class TechnologyViewModel {
    MainActivity activity;

    public TechnologyViewModel(MainActivity activity) {
        this.activity = activity;
    }

    public void fromModelToView(){

        ListView list = activity.findViewById(R.id.technologyList);

//        ArrayAdapter<TechnologyModel> adapter =  new ArrayAdapter<>(
//                activity,
//                android.R.layout.simple_list_item_1,
//                TechnologyRepository.getInstance().getTechnologies()
//        );
        ArrayAdapter<TechnologyModel> adapter =  new TechnologyListViewAdapter(
                activity,
                R.layout.technology_listview_item,
                TechnologyRepository.getInstance().getTechnologies()
        );

        list.setAdapter(adapter);
    }
}
