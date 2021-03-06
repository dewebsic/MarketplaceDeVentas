package cl.dewebsic.marketplacedeventas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cl.dewebsic.marketplacedeventas.interfaces.IComunationFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements IComunationFragments {

    //declaramos los componentes
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    Fragment fragment = null;
    TextView nameHeader;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        nameHeader = findViewById(R.id.nameUser);



        fragment = new dashboard();
        loadFragment(fragment);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();

                //evaluamos las opciones del menu
                switch (id){
                    case R.id.perfil:
                        fragment = new PerfilFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.basket:
                        fragment = new BasketFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.favorite:
                        fragment = new FavoriteFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.code:
                        fragment = new PromoCodeFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.orders:
                        fragment = new OrderFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.setting:
                        fragment = new SettingFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.support:
                        fragment = new SupportFragment();
                        loadFragment(fragment);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    //método para cargar un fragment
    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }


    @Override
    public void iniciarSesion() {
            Intent next = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(next);
    }

    @Override
    public void supermercados()
    {
        Intent next = new Intent(MainActivity.this, SuperMarketActivity.class);
        startActivity(next);
    }
    @Override
    public void librerias()
    {
        Intent next = new Intent(MainActivity.this, LibreriaActivity.class);
        startActivity(next);
    }

    @Override
    public void support(){

        Intent next = new Intent(MainActivity.this, SuppportActivity.class);
        startActivity(next);
    }

    @Override
    protected void onStart(){
        super.onStart();


    }

}