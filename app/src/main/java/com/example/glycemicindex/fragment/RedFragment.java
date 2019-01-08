package com.example.glycemicindex.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.glycemicindex.Product;
import com.example.glycemicindex.ProductOrangeAdapter;
import com.example.glycemicindex.ProductRedAdapter;
import com.example.glycemicindex.R;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RedFragment extends Fragment {

    ProductRedAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Product> newsArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_red, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        newsArrayList.clear();

        newsArrayList.add(new Product(1,"Эскимо", "60"));
        newsArrayList.add(new Product(2,"Щербет (с сахаром)", "65"));
        newsArrayList.add(new Product(3,"Шоколадный батончик", "65"));
        newsArrayList.add(new Product(4,"Чай (с сахаром)", "60"));
        newsArrayList.add(new Product(5,"Хлеб черный, дрожжевой", "65"));
        newsArrayList.add(new Product(6,"Хлеб черный", "65"));
        newsArrayList.add(new Product(7,"Эскимо", "60"));
        newsArrayList.add(new Product(8,"Щербет (с сахаром)", "65"));
        newsArrayList.add(new Product(9,"Шоколадный батончик", "65"));
        newsArrayList.add(new Product(10,"Чай (с сахаром)", "60"));
        newsArrayList.add(new Product(11,"Хлеб черный, дрожжевой", "65"));
        newsArrayList.add(new Product(12,"Хлеб черный", "65"));
        newsArrayList.add(new Product(13,"Эскимо", "60"));
        newsArrayList.add(new Product(14,"Щербет (с сахаром)", "65"));
        newsArrayList.add(new Product(15,"Шоколадный батончик", "65"));
        newsArrayList.add(new Product(16,"Чай (с сахаром)", "60"));
        newsArrayList.add(new Product(17,"Хлеб черный, дрожжевой", "65"));
        newsArrayList.add(new Product(18,"Хлеб черный", "65"));

        setUpRecyclerView(newsArrayList);
    }

    private void setUpRecyclerView(ArrayList<Product> newsArrayList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductRedAdapter(newsArrayList, getContext());
        recyclerView.setAdapter(adapter);
    }

    public void beginSearch(String query) {
        adapter.getFilter().filter(query);
    }

    public void sortByName(){
        adapter.sortByName();
    }

    public void sortByIndex(){
        adapter.sortByIndex();
    }

    public void reverseByName(){
        adapter.reverseByName();
    }
}
