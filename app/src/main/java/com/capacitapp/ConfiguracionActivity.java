package com.capacitapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.models.Usuario;
import com.capacitapp.utils.UserPreferences;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConfiguracionActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private ImageView imgBackArrow;
    private CircleImageView fotoImageView;
    private TextInputEditText nombreEditText;
    private TextInputEditText apellidoEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText passEditText;
    private Button editarButton;
    private DBHelper dbHelper;
    private Context context;
    private int currentUserId;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_configuracion);

        context = this;
        dbHelper = new DBHelper(context);

        imgBackArrow = findViewById(R.id.btn_back);

        fotoImageView = findViewById(R.id.imageView_foto_config);


        // Obtener referencias a los TextInputEditText
        nombreEditText = findViewById(R.id.textInputLayoutNombre).findViewById(R.id.textInputEditNombre);
        apellidoEditText = findViewById(R.id.textInputLayoutApellido).findViewById(R.id.textInputEditApellido);
        emailEditText = findViewById(R.id.textInputLayoutEmail).findViewById(R.id.textInputEditEmail);
        passEditText = findViewById(R.id.textInputLayoutPass).findViewById(R.id.textInputEditPass);
        editarButton = findViewById(R.id.button_editar);


        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguracionActivity.this, PerfilActivity.class);
                startActivity(intent);

            }
        });

        loadUserData();
        loadProfileImage();

        editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editarButton.getText().toString().equals(getString(R.string.btn_editar_config))) {
                    enableEditing();
                } else {
                    saveUserData();
                }
            }
        });

    }

    private void loadUserData() {

        currentUserId = UserPreferences.getLoggedUserId(context); // Obtén el ID del usuario logueado
        Usuario userPerfil = dbHelper.getUserById(currentUserId);
        if (userPerfil != null) {
            nombreEditText.setText(userPerfil.getName());
            apellidoEditText.setText(userPerfil.getLastname());
            emailEditText.setText(userPerfil.getEmail());
            passEditText.setText(userPerfil.getPassword());

        }

        // Desactivar edición al iniciar la actividad
        setEditable(false);
    }

    private void enableEditing() {
        setEditable(true);
        editarButton.setText(R.string.btn_guardar_config);
        editinImageProfile();
        fotoImageView.setBorderWidth(10); // Activar borde cuando se edita


    }

    private void editinImageProfile(){
        fotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    private void setEditable(boolean enabled) {
        nombreEditText.setEnabled(enabled);
        apellidoEditText.setEnabled(enabled);
        emailEditText.setEnabled(enabled);
        passEditText.setEnabled(enabled);
        fotoImageView.setClickable(enabled);
        if (!enabled) {
            fotoImageView.setBorderWidth(0); // Desactivar borde cuando no se edita
        }
    }

    private void saveUserData() {
        try {

            // Obtener el ID del usuario actual
            int id = UserPreferences.getLoggedUserId(context);
            if (id == -1) {
                Toast.makeText(context, "Error al obtener el ID del usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            // Obtener los datos actuales del usuario
            Usuario userPerfil = dbHelper.getUserById(id);

            if (userPerfil != null) {
                // Obtener los datos actualizados desde los campos de texto

                String nombre = nombreEditText.getText().toString();

                String apellido = apellidoEditText.getText().toString();

                String email = emailEditText.getText().toString().trim();

                String password = passEditText.getText().toString();



                // Validar campos obligatorios

                if (nombre.isEmpty()){
                    nombreEditText.setError("Complete el campo nombre");
                    nombreEditText.requestFocus();
                    return;
                }

                if (apellido.isEmpty()) {
                    apellidoEditText.setError("Complete el campo apellido");
                    apellidoEditText.requestFocus();
                    return;
                }

                // Validar formato de email
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText.setError("Ingrese un email válido");
                    emailEditText.requestFocus();
                    return;

                }

                // Validar formato de contraseña (mínimo 6 caracteres)
                if (password.isEmpty()) {
                    passEditText.setError("Contraseña es requerida");
                    passEditText.requestFocus();
                    return;

                }

                if (password.length() < 6) {
                    passEditText.setError("La contraseña debe tener al menos 6 caracteres");
                    passEditText.requestFocus();
                    return;
                }

                //Cargamos datos booleanos, el modelo trabaja booleanos pero la base de datos enteros 0 y 1
                boolean active = userPerfil.getIs_active();
                boolean staff = userPerfil.getIs_staff();

                // Crear un objeto Usuario con los datos actualizados
                Usuario usuarioActualizado = new Usuario(id, email, nombre, apellido, password, active, staff);

                // Actualizar el usuario en la base de datos
                dbHelper.updateUser(usuarioActualizado);

                // Guardar la imagen de perfil si ha sido seleccionada
                if (imageUri != null) {
                    saveProfileImage(imageUri.toString());
                }

                // Desactivar edición y cambiar el texto al botón
                setEditable(false);
                editarButton.setText(R.string.btn_editar_config);
                fotoImageView.setClickable(false);
                Toast.makeText(context, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No se encontraron datos del usuario", Toast.LENGTH_SHORT).show();
            }
            setEditable(false);
        } catch (Exception e) {
            Toast.makeText(context, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        gallery.addCategory(Intent.CATEGORY_OPENABLE);
        gallery.setType("image/*");
        gallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null) {
            imageUri = data.getData();
            // Otorgar permisos de lectura de URI persistente
            getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fotoImageView.setImageURI(imageUri);
            saveProfileImage(imageUri.toString());
        }

    }

    private void saveProfileImage(String imageUri) {
        UserPreferences.saveUserProfileImage(this, imageUri);
    }

    private void loadProfileImage() {
        String imageUri = UserPreferences.getUserProfileImage(this);
        if (imageUri != null) {
            fotoImageView .setImageURI(Uri.parse(imageUri));
        }
    }



}