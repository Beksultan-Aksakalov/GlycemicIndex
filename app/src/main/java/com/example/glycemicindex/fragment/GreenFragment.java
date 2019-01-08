package com.example.glycemicindex.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.glycemicindex.ProductDetailActivity;
import com.example.glycemicindex.ProductGreenAdapter;
import com.example.glycemicindex.R;
import com.example.glycemicindex.interf.ProductInterface;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GreenFragment extends Fragment implements ProductInterface {

    ProductGreenAdapter adapter;

    private ArrayList<Product> newsArrayList = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ButterKnife.bind((Activity) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_green, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        newsArrayList.clear();
        setUpList();
    }

    private void setUpList() {
        newsArrayList.add(new Product(1, "Абрикос", "20"));
        newsArrayList.add(new Product(2, "Ббрикос сушеный", "30"));
        newsArrayList.add(new Product(3, "Гвокадо", "10"));
        newsArrayList.add(new Product(4, "Дгава (концентрат)", "15"));
        newsArrayList.add(new Product(5, "Айва", "35"));
        newsArrayList.add(new Product(6, "Алыча", "25"));
        newsArrayList.add(new Product(7, "Абрикос", "20"));
        newsArrayList.add(new Product(8, "Абрикос сушеный", "30"));
        newsArrayList.add(new Product(9, "Евокадо", "10"));
        newsArrayList.add(new Product(10, "Агава (концентрат)", "15"));
        newsArrayList.add(new Product(11, "Айва", "35"));
        newsArrayList.add(new Product(12, "Алыча", "25"));
        newsArrayList.add(new Product(14, "Жбрикос сушеный", "30"));
        newsArrayList.add(new Product(15, "Авокадо", "10"));
        newsArrayList.add(new Product(16, "Агава (концентрат)", "15"));
        newsArrayList.add(new Product(17, "Айва", "35"));
        newsArrayList.add(new Product(18, "Алыча", "25"));
        setUpRecyclerView(newsArrayList);
    }

    private void setUpRecyclerView(ArrayList<Product> newsArrayList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductGreenAdapter(newsArrayList, getContext());
        recyclerView.setAdapter(adapter);
        adapter.setCallback(product -> startActivity(new Intent(getContext(), ProductDetailActivity.class).putExtra("green_product", product)));
    }

    public void beginSearch(String query) {
        adapter.getFilter().filter(query);
    }

    public void sortByName() {
        adapter.sortByName();
    }

    public void sortByIndex() {
        adapter.sortByIndex();
    }

    public void reverseByName() {
        adapter.reverseByName();
    }

    public void addProduct(Product product) {
        newsArrayList.add(new Product(product.getId(), product.getName(), product.getIndex()));
        setUpList();
    }

    @Override
    public void setProduct(Product product) {
        if (newsArrayList.equals(product.getId())) {
            newsArrayList.remove(product.getId());
            newsArrayList.add(new Product(product.getId(), product.getName(), product.getName()));
            setUpRecyclerView(newsArrayList);
        }
    }

}


//        if (getArguments() != null) {
//            int id = getArguments().getInt("id");
//            String name = getArguments().getString("name");
//            String index = getArguments().getString("index");
//            newsArrayList.add(new Product(id, name, index));
//
//            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            recyclerView.setLayoutManager(layoutManager);
//            adapter = new ProductGreenAdapter(newsArrayList, getContext());
//            recyclerView.setAdapter(adapter);
//            adapter.setCallback(product -> startActivity(new Intent(getContext(), ProductDetailActivity.class).putExtra("green_product", product)));
//        }
