package com.example.finaluf1;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // Vincular TabLayout con ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Inicio");
                    break;
                case 1:
                    tab.setText("Gastos");
                    break;
                case 2:
                    tab.setText("Configuración");
                    break;
            }
        }).attach();
    }

    public void cambiarPagina(int posicion) {
        viewPager.setCurrentItem(posicion, true);
    }
}
