package com.capacitapp.AccountManagerHelper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.capacitapp.MainActivity;

import java.io.IOException;

public class AccountManagerHelper {

    private AccountManager accountManager;

    public AccountManagerHelper(Context context) {
        accountManager = AccountManager.get(context);
    }

    public void createAccount(String useremail, String password) {
        // Comprueba si el usuario ya tiene una cuenta
        Account[] accounts = accountManager.getAccountsByType("account_type");
        if (accounts.length == 0) {
            Account account = new Account(useremail, "account_type");
            accountManager.addAccountExplicitly(account, password, null);
        }
    }

    public void getAuthToken(final Context context, final AccountManagerCallback<Bundle> callback) {
        Account[] accounts = accountManager.getAccountsByType("account_type");
        if (accounts.length > 0) {
            Account account = accounts[0];
            accountManager.getAuthToken(account, "auth_token_type", null, false, callback, null);
        } else {
            // No hay cuentas, manejar el caso
            callback.run(null);
        }
    }

    public void login(final Context context, String username, String password) {
        // Crea una nueva cuenta
        createAccount(username, password);

        // Obtén el token de autenticación
        getAuthToken(context, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Bundle result = future.getResult();
                    String token = result.getString(AccountManager.KEY_AUTHTOKEN);
                    if (token != null) {
                        // Inicia la actividad principal
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    } else {
                        // Muestra un mensaje de error
                        Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                    }
                } catch (OperationCanceledException | AuthenticatorException | IOException e) {
                    e.printStackTrace();
                    // Manejar el error adecuadamente
                    Toast.makeText(context, "Error al obtener el token de autenticación", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




