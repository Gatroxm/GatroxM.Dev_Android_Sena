package com.example.proyectogatroxmdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcomeUser;
    private RecyclerView rvCatalog;
    private FloatingActionButton fabChat;
    private MaterialToolbar toolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Window insets handling
        View mainView = findViewById(R.id.homeLayout);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return WindowInsetsCompat.CONSUMED;
            });
        }

        mAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbarHome);
        tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
        rvCatalog = findViewById(R.id.rvCatalog);
        fabChat = findViewById(R.id.fabChat);

        // Setup Toolbar
        setSupportActionBar(toolbar);

        // Display User Email
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && currentUser.getEmail() != null) {
            String welcomeMessage = "Hola, " + currentUser.getEmail() + "\n" + getString(R.string.home_subtitle);
            tvWelcomeUser.setText(welcomeMessage);
        }

        // Setup RecyclerView
        rvCatalog.setLayoutManager(new LinearLayoutManager(this));
        List<CatalogItem> items = generateCatalogData();
        CatalogAdapter adapter = new CatalogAdapter(this, items);
        rvCatalog.setAdapter(adapter);

        // Setup Chat FloatingActionButton
        fabChat.setOnClickListener(v -> 
            Toast.makeText(HomeActivity.this, getString(R.string.msg_chat_contact), Toast.LENGTH_SHORT).show()
        );
    }

    private List<CatalogItem> generateCatalogData() {
        List<CatalogItem> items = new ArrayList<>();
        items.add(new CatalogItem(
                "Ruta del Bosque Esmeralda",
                "Ecoturismo",
                "Camino ecológico guiado a través de reservas de bosque nativo con observación de aves e hidrología.",
                R.drawable.ic_forest
        ));
        items.add(new CatalogItem(
                "Sendero de la Montaña Verde",
                "Aventura Ecológica",
                "Caminata técnica por la cordillera verde con talleres sobre conservación de suelo y flora nativa.",
                R.drawable.ic_mountain
        ));
        items.add(new CatalogItem(
                "Tours Agroecológicos",
                "Sostenibilidad",
                "Visita a huertas orgánicas comunitarias y aprendizaje práctico sobre compostaje y reciclaje.",
                R.drawable.ic_recycle
        ));
        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        mAuth.signOut();
        Toast.makeText(HomeActivity.this, getString(R.string.msg_logout), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
