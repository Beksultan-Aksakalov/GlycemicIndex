package com.example.glycemicindex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.glycemicindex.fragment.GreenFragment;
import com.example.glycemicindex.interf.ProductInterface;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends AppCompatActivity {

    @BindView(R.id.txt_name)
    TextView txt_name;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_edit)
    ImageView img_edit;

    @BindView(R.id.edt_name)
    EditText edt_name;

    @BindView(R.id.edt_index)
    EditText edt_index;

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        product = getIntent().getParcelableExtra("green_product");
        txt_name.setText(product.getName());
        edt_name.setText(product.getName());
        edt_index.setText(product.getIndex());
    }

    @OnClick(R.id.img_back)
    public void onClickBack() {
        finish();
    }

    @OnClick(R.id.img_edit)
    public void onClickEdit() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", product.getId());
        bundle.putString("edt_name", edt_name.getText().toString().trim());
        bundle.putString("edt_index", edt_index.getText().toString().trim());
        GreenFragment greenFragment = new GreenFragment();
        greenFragment.setArguments(bundle);

        Product product1 = new Product(product.getId(), edt_name.getText().toString().trim(), edt_index.getText().toString().trim());
        productInterface.setProduct(product1);
        startActivity(new Intent(ProductDetailActivity.this, MainActivity.class));
        finish();
    }

    public ProductInterface productInterface = product -> {};
}
