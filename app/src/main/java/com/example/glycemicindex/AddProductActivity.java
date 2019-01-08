package com.example.glycemicindex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.glycemicindex.fragment.GreenFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText edt_name;

    @BindView(R.id.edt_index)
    EditText edt_index;

    @BindView(R.id.img_add)
    ImageView img_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.img_add)
    public void onClickAdd(){
        int id = 19;

        if (edt_name != null && edt_index != null){

            GreenFragment greenFragment = new GreenFragment();
            Product product = new Product(id,edt_name.getText().toString().trim(),edt_index.getText().toString().trim());
            greenFragment.addProduct(product);

        }else{
            edt_index.setError("The index is empty ! Please, try again !");
            edt_name.setError("The name is empty ! Please, try again !");
        }
    }

}

//
//
//
//            Bundle bundle = new Bundle();
//            bundle.putInt("id",id);
//            bundle.putString("name",edt_name.getText().toString().trim());
//            bundle.putString("index",edt_index.getText().toString().trim());
//            greenFragment.setArguments(bundle);
//
//            startActivity(new Intent(AddProductActivity.this,MainActivity.class));
